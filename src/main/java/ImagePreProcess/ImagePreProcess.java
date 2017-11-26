package ImagePreProcess;

import javax.imageio.ImageIO;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImagePreProcess {
    //CSV file from where the coordinates for each crop are taken from
    final String cropCoordinatesFilePath;
    //Our targeted areas to crop. Constructor arguments : int (x, y, width, height)
    AreaToCrop player1Name;
    AreaToCrop player2Name;
    AreaToCrop player1set1;
    AreaToCrop player2set1;
    AreaToCrop player1set2;
    AreaToCrop player2set2;
    AreaToCrop player1score;
    AreaToCrop player2score;
    AreaToCrop gameTime;


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
        player1Name = setupAreaToCrop(reader.readLine());
        player2Name = setupAreaToCrop(reader.readLine());
        player1set1 = setupAreaToCrop(reader.readLine());
        player2set1 = setupAreaToCrop(reader.readLine());
        player1set2 = setupAreaToCrop(reader.readLine());
        player2set2 = setupAreaToCrop(reader.readLine());
        player1score = setupAreaToCrop(reader.readLine());
        player2score = setupAreaToCrop(reader.readLine());
        gameTime = setupAreaToCrop(reader.readLine());
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
    // ADDRESS 0 ::: game score
    //...
    // ADDRESS 8 ::: game time
    // IF CHANGES ARE MADE, MAKE SURE TO MODIFY ARRAY LENGTH AND ADD THE NEW CROPPED IMAGE TO THE ARRAY
    public BufferedImage[] getSubImage(BufferedImage imageToPreProcess) {
        BufferedImage[] croppedImages = new BufferedImage[9];
        croppedImages[0] = player1Name.crop(imageToPreProcess);
        croppedImages[1] = player2Name.crop(imageToPreProcess);
        croppedImages[2] = player1set1.crop(imageToPreProcess);
        croppedImages[3] = player2set1.crop(imageToPreProcess);
        croppedImages[4] = player1set2.crop(imageToPreProcess);
        croppedImages[5] = player2set2.crop(imageToPreProcess);
        croppedImages[6] = player1score.crop(imageToPreProcess);
        croppedImages[7] = player2score.crop(imageToPreProcess);
        croppedImages[8] = gameTime.crop(imageToPreProcess);
        return croppedImages;
    }

    public static void storeCrops(int frameNumber, BufferedImage[] preProcessedImages) throws IOException {
        for (int counter = 0; counter < preProcessedImages.length; counter++) {
            ImageIO.write(preProcessedImages[counter], "jpg", new File("./frames/frame" + frameNumber + "Crop" + counter + ".jpg"));
        }
    }
}
