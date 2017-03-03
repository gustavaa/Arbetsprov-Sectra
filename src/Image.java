/**
 * Created by gustavaaro on 2017-03-03.
 */
public abstract class Image {

    int width;
    int height;


    Image(int width, int height){
        this.width = width;
        this.height = height;

    }

    public abstract long getSize();
}
