import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ImageStorageEstimator {

    private static ArrayList<Image> images;
    private static ArrayList<ImageGroup> imageGroups;


    /**
     * The ImageStorageEstimator is a tool for estimating the total storage size for
     * images specified by the user. The program can also account for stacks of
     * images, which will allow for more aggressive compression.
     *
     * @author  Gustav Aaro
     *
     **/

    public static void main(String[] args){
        System.out.println("Storage calculator by Gustav Aaro. " +
                        "Enter one line for each image/group on the format " +
                        "\"type width height\", or \"G i, i, ...\". Exit with \"Q\".");

        images = new ArrayList<>();
        imageGroups = new ArrayList<>();

        DecimalFormat formatter = new DecimalFormat("###,###");
        System.out.println("Total size: " + formatter.format(estimateStorage()) + " bytes");
    }

    /**
     * Takes in user input and estimates storage size.
     * @return The estimated storage for the specified images.
     */
    public static long estimateStorage(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long totalSize = 0;

        try {
            String line = reader.readLine();
            while (!line.equals("q")){
                String[] args = line.split(" ");

                try {
                    performCommand(args);
                }catch (InvalidInputException | IllegalOperationException e){

                    //The program will not exit for these exceptions, allowing the user to correct their invalid input.
                    System.err.println(e.getMessage());
                }

                line = reader.readLine();
            }

            for(ImageGroup group : imageGroups){
                totalSize += group.getGroupSize();
            }

            for (Image image : images){
                //If image belongs to a group, the size has already been accounted for
                if(!image.belongsToGroup()){
                    totalSize += image.getSize();
                }
            }

            return totalSize;

        } catch (IOException e) {
            //Should never happen, but for other exceptions not directly related to user input, print stacktrace and exit program.
            e.printStackTrace();
            System.exit(0);
        }
        return totalSize;
    }

    /**
     * Interprets the user's input and performs the command.
     *
     * @param   args the user input, split up into a string array.
     * @throws  InvalidInputException on malformed user input.
     * @throws  IllegalOperationException on illegal operations, such as trying to
     *          group images that does not exist or already are members of existing groups.
     */
    private static void performCommand(String[] args) throws InvalidInputException, IllegalOperationException{
        validateInputValues(args);
        args[0] = args[0].toUpperCase();
        String operation = args[0];

        switch (operation){
            case "G":
                imageGroups.add(groupImages(args));
                break;
            default:
               images.add(getImage(args));
        }
    }

    /**
     * Validates that the user command is followed by at least 2 positive integers.
     *
     * @param args the user input.
     * @throws InvalidInputException on malformed user input.
     */
    private static void validateInputValues(String[] args) throws InvalidInputException{
        try {
            if(args.length < 3) throw new IndexOutOfBoundsException();
            for(int i = 1; i<args.length; ++i) {
                int temp = Integer.parseInt(args[i]);
                if(temp < 0) throw new NumberFormatException();
            }
        } catch (NumberFormatException e){
            throw new InvalidInputException("Invalid input. All values following the first argument must be positive integers.");
        } catch (IndexOutOfBoundsException e1){
            throw new InvalidInputException("Invalid input. Input need to have format \"type width height\" or \"g i1 i2 ... in\"");
        }

    }


    /**
     * Performs the group command. Takes in a set of indices and creates an
     * ImageGroup with the images corresponding to these indices.
     *
     * @param   args integer-validated user input.
     * @return  an ImageGroup with the chosen images.
     * @throws  IllegalOperationException when the user tries to group images that
     *          does not exist or already are members of existing groups.
     */
    private static ImageGroup groupImages(String[] args) throws IllegalOperationException{

        ArrayList<Image> groupedImages = new ArrayList<>();

        //Checks that the chosen images exists and do not already belong to other groups.
        for (int i = 1; i <args.length; i++) {
            try {
                Image currentImage = images.get(Integer.parseInt(args[i]) - 1);
                if (currentImage.belongsToGroup())
                    throw new IllegalOperationException("One or more of the images are already a member of another group.");

            } catch (IndexOutOfBoundsException e) {
                throw new IllegalOperationException("Invalid indices for images. Please add the images before grouping them.");
            }
        }

        for (int i = 1; i < args.length; i++) {
            Image currentImage = images.get(Integer.parseInt(args[i]) - 1);
            groupedImages.add(currentImage);
            currentImage.setBelongsToGroup(true);
        }

        return new ImageGroup(groupedImages);

    }

    /**
     * Performs the image-command, which creates an image of specific type and dimensions.
     *
     * @param args integer-validated user input.
     * @return an image of the user specified type.
     * @throws InvalidInputException on unknown image type.
     */
    private static Image getImage(String[] args) throws InvalidInputException {
        String type = args[0];
        int width = Integer.parseInt(args[1]);
        int height = Integer.parseInt(args[2]);


        switch (type){
            case "J":
            case "JPG":
                return  new JPEG(width,height);
            case "JP2":
            case "JPEG2000":
                return new JP2(width,height);
            case "BMP":
                return new BMP(width,height);
            default:
                throw new InvalidInputException("Invalid command.");
        }

    }






}
