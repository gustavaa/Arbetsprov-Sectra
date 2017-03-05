/**
 * Created by gustavaaro on 2017-03-03.
 */
public class JPEG extends Image {


    JPEG(int width, int height){
        super(width, height);
    }


    /**
     * Calculates the JPEG image size, also taking account for pyramid level images.
     *
     * @return the size of the JPEG file.
    */
    @Override
    public long getSize() {
        long size = 0;
        int tempWidth = getWidth();
        int tempHeight = getHeight();
        while(Math.min(tempWidth,tempHeight) >= 128){
            size += tempWidth * tempHeight * 0.2;
            tempHeight *= 0.5;
            tempWidth *= 0.5;
        }
        return size;
    }
}
