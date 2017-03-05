import java.util.ArrayList;

public class ImageGroup {

    private ArrayList<Image> images;

    ImageGroup(ArrayList<Image> groupedImages){
        images = groupedImages;
    }

    /**
     * Calculates the total size of the image group.
     *
     * @return the size of the image group.
     */
    public long getGroupSize(){
        long totalStorageSize = 0;

        for(Image image : images) {
            totalStorageSize += image.getSize();
        }

        return (long) Math.floor(totalStorageSize/Math.log(images.size() + 3));
    }

}
