package ImagePreProcess;

import javax.imageio.ImageIO;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImagePreProcess {
    //CSV file from where the coordinates for each crop are taken from
    final String cropCoordinatesFilePath;
    //Our targeted areas to crop. Constructor arguments : int (x, y, width, height)
    AreaToCrop serveSpeed;
    AreaToCrop gameTime;
    AreaToCrop gameScore;

    //CONSTRUCTOR
    //Construct this object once and then keep on reusing it using getSubImage method.
    public ImagePreProcess(String cropCoordinatesFilePath) throws IOException {
        this.cropCoordinatesFilePath = cropCoordinatesFilePath;
        setupCropCoordinates();
    }

    //Constructor helper
    //Reads the coordinates from a file to increase modularity
    private void setupCropCoordinates() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(cropCoordinatesFilePath));
        if (! reader.readLine().equals("x,y,width,height"))
            throw new IOException();
        serveSpeed = setupAreaToCrop(reader.readLine());
        gameTime = setupAreaToCrop(reader.readLine());
        gameScore = setupAreaToCrop(reader.readLine());
    }

    //Helper method
    private AreaToCrop setupAreaToCrop(String line) {
        String[] splitNextLine = line.split(",");
        int x = Integer.parseInt(splitNextLine[0]);
        int y = Integer.parseInt(splitNextLine[1]);
        int width = Integer.parseInt(splitNextLine[2]);
        int height = Integer.parseInt(splitNextLine[3]);
        return new AreaToCrop(x, y, width, height);
    }

    //Core method
    //Returns an array of images containing our targets cropped areas
    // ADDRESS 0 ::: serve speed
    // ADDRESS 1 ::: game time
    // ADDRESS 2 ::: game score
    // IF CHANGES ARE MADE, MAKE SURE TO MODIFY ARRAY LENGTH AND ADD THE NEW CROPPED IMAGE TO THE ARRAY
    public BufferedImage[] getSubImage(BufferedImage imageToPreProcess) {
        BufferedImage[] croppedImages = new BufferedImage[3];
        croppedImages[0] = serveSpeed.crop(imageToPreProcess);
        croppedImages[1] = gameTime.crop(imageToPreProcess);
        croppedImages[2] = gameScore.crop(imageToPreProcess);
        return croppedImages;
    }

    //Used for testing purposes
    public static void storeCrops(int frameNumber, BufferedImage[] preProcessedImages) throws IOException {
        for (int counter = 0; counter < preProcessedImages.length; counter++) {
            ImageIO.write(preProcessedImages[counter], "jpg", new File("./frames/frame" + frameNumber + "Crop" + counter + ".jpg"));
        }
    }
}
