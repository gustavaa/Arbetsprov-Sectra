/**
 * Created by gustavaaro on 2017-03-03.
 */
public abstract class Image {

    private int width;
    private int height;
    private boolean belongsToGroup;


    Image(int width, int height){
        this.width = width;
        this.height = height;
        belongsToGroup = false;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setBelongsToGroup(boolean belongsToGroup) {
        this.belongsToGroup = belongsToGroup;
    }

    public boolean belongsToGroup() {
        return belongsToGroup;
    }

    public abstract long getSize();
}
