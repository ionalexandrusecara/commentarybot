package ImagePreProcess;
import java.awt.image.BufferedImage;

public class AreaToCrop {
    //Coordinates for cropping
    int x;
    int y;
    int width;
    int height;

    //CONSTRUCTOR
    //Specifies the area to crop
    public AreaToCrop(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    //Returns the cropped image accordingly to the previously specified coordinates
    public BufferedImage crop(BufferedImage toCrop) {
        return toCrop.getSubimage(x, y, width, height);
    }
}