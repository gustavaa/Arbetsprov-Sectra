import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ImageStorageEstimator {

    private static ArrayList<Image> images;
    private static ArrayList<ImageGroup> imageGroups;



    public static void main(String[] args){
        images = new ArrayList<>();
        imageGroups = new ArrayList<>();

        System.out.println("Total size: " + estimateStorage());

    }

    public static long estimateStorage(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long totalSize = 0;

        try {
            String line = reader.readLine();
            while (!line.equals("q")){
                String[] args = line.split(" ");

                try {
                    performOperation(args);
                }catch (InvalidInputException | IllegalOperationException e){
                    System.err.println(e.getMessage());
                }

                line = reader.readLine();
            }

            for(ImageGroup group : imageGroups){
                long groupSize = group.getGroupSize();
                System.out.println("Groupsize: " + groupSize);
                totalSize += groupSize;
            }

            for (Image image : images){
                System.out.println("Imagesize: " + image.getSize());
                if(!image.belongsToGroup()) totalSize += image.getSize();
            }

            return totalSize;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return totalSize;
    }

    private static void performOperation(String[] args) throws InvalidInputException, IllegalOperationException{
        validateInputValues(args);

        args[0] = args[0].toUpperCase();
        String command = args[0];

        switch (command){
            case "G":
                imageGroups.add(groupImages(args));
                break;
            default:
               images.add(getImage(args));
        }
    }

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
            throw new InvalidInputException("Invalid input. Input need to have format \"type width height\". or \"g i1 i2 ... in\"");
        }

    }


    private static ImageGroup groupImages(String[] args) throws IllegalOperationException{

        ArrayList<Image> groupedImages = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            Image currentImage = images.get(Integer.parseInt(args[i]) - 1);
            if(currentImage.belongsToGroup()) throw new IllegalOperationException("This image is already a member of another group.");
            groupedImages.add(currentImage);
            currentImage.setBelongsToGroup(true);
        }

        return new ImageGroup(groupedImages);

    }

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
                throw new InvalidInputException("Invalid image type.");
        }

    }






}
