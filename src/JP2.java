/**
 * Created by gustavaaro on 2017-03-03.
 */
public class JP2 extends Image {


    JP2(int width, int height){
        super(width, height);
    }


    @Override
    public long getSize() {
        return Math.round(width * height * 0.4 /Math.log(Math.log(width * height + 16)));
    }
}