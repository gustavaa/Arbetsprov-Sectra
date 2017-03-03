/**
 * Created by gustavaaro on 2017-03-03.
 */
public class BMP extends Image {


    BMP(int width, int height){
        super(width, height);
    }


    @Override
    public long getSize() {
        long size = 0;
        while(Math.min(width,height) > 128){
            size += width * height;
            width *= 0.5;
            height *= 0.5;
        }
        return size;
    }
}
