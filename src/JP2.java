/**
 * Created by gustavaaro on 2017-03-03.
 */
public class JP2 extends Image {


    JP2(int width, int height){
        super(width, height);
    }


    /**
     * Calculates the JPEG 2000 image size.
     *
     * @return the size of the JPEG 2000 file.
     */
    @Override
    public long getSize() {
        return(long) Math.floor((getWidth() * getHeight() * 0.4) /Math.log(Math.log(getWidth() * getHeight() + 16)));
    }
}
