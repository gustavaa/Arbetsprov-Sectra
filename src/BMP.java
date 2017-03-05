/**
 * Created by gustavaaro on 2017-03-03.
 */
public class BMP extends Image {


    BMP(int width, int height){
        super(width, height);
    }

    /**
     * Calculates the BMP image size, also taking account for pyramid level images.
     *
     * @return the size of the BMP file.
     */
    @Override
    public long getSize() {
        long size = 0;
        int tempWidth = getWidth();
        int tempHeight = getHeight();
        while(Math.min(tempWidth,tempHeight) >= 128){
            size += tempWidth * tempHeight;
            tempHeight *= 0.5;
            tempWidth *= 0.5;
        }
        return size;
    }
}
