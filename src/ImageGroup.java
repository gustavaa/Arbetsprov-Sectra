import java.util.Stack;

/**
 * Created by gustavaaro on 2017-03-03.
 */
public class ImageGroup {

    private Stack<Image> images;

    ImageGroup(Image[] groupedImages){
        images = new Stack<Image>();

        for (Image image: groupedImages) {
            this.images.push(image);
        }
    }

    public long getGroupSize(){
        long totalStorageSize = 0;
        int groupSize = images.size();
        while (!images.empty()){
            totalStorageSize += images.pop().getSize();
        }

        return Math.round(totalStorageSize/Math.log(groupSize + 3));
    }

}
