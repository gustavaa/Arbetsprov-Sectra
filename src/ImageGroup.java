import java.util.ArrayList;
import java.util.Stack;


public class ImageGroup {

    private ArrayList<Image> images;

    ImageGroup(ArrayList<Image> groupedImages){
        images = groupedImages;
    }

    public long getGroupSize(){
        long totalStorageSize = 0;

        for(Image image : images) {
            totalStorageSize += image.getSize();
        }

        return Math.round(totalStorageSize/Math.log(images.size() + 3));
    }

}
