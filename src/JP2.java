/**
 * Created by gustavaaro on 2017-03-03.
 */
public class JP2 extends Image {


    JP2(int width, int height){
        super(width, height);
    }


    @Override
    public long getSize() {
        return Math.round((getWidth() * getHeight() * 0.4) /Math.log(Math.log(getWidth() * getHeight() + 16)));
    }
}
