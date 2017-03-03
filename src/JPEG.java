/**
 * Created by gustavaaro on 2017-03-03.
 */
public class JPEG extends Image {


    JPEG(int width, int height){
        super(width, height);
    }


    @Override
    public long getSize() {
        long size = 0;
        while(Math.min(width,height) > 128){
            size += width * height * 0.2;
            width *= 0.5;
            height *= 0.5;
        }
        return size;
    }
}
