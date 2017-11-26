package ImageProcessing;

import API_calls.EMO_calls;
import API_calls.OCR_calls;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;


/**
 * Created by Saad Moussadek on 26/11/2017.
 */
public class ImageProcessing {
    int frame = 0;
    int increment = 30;
    final int maxFrame;
    final String basePath;

    public ImageProcessing(int increment, int maxFrame, String basePath) {
        this.maxFrame = maxFrame;
        this.basePath = basePath;
        this.increment = increment;
    }

    public BufferedImage getImage(String filePath) throws IOException {
        return ImageIO.read(new File(filePath));
    }

    public boolean hasNext() {
        return frame + increment <= maxFrame;
    }

    public void nextFrame() {
        frame += increment;
    }

    public String[] returnAverages() throws IOException, ParseException, InterruptedException {
        String[] returns = new String[10];
        while(this.hasNext()) {
            System.out.println(getEmotion());
            nextFrame();
        }
        return returns;
    }

    public String getPlayer1Name() throws IOException, ParseException {
        return OCR_calls.getText(getImage(basePath + "" + frame + "Crop0.jpg"));
    }

    public String getPlayer2Name() throws IOException, ParseException {
        return OCR_calls.getText(getImage(basePath + "" + frame + "Crop1.jpg"));
    }

    public String getPlayer1Set() throws IOException, ParseException {
         return OCR_calls.getText(getImage(basePath + "" + frame + "Crop2.jpg"));
    }

    public String getPlayer2Set() throws IOException, ParseException {
        return OCR_calls.getText(getImage(basePath + "" + frame + "Crop3.jpg"));
    }

    public String getPlayer1Set3() throws IOException, ParseException {
        return OCR_calls.getText(getImage(basePath + "" + frame + "Crop4.jpg"));
    }

    public String getPlayer2Set3() throws IOException, ParseException {
        return OCR_calls.getText(getImage(basePath + "" + frame + "Crop5.jpg"));
    }

    public String getPlayer1Score() throws IOException, ParseException {
        return OCR_calls.getText(getImage(basePath + "" + frame + "Crop6.jpg"));
    }

    public String getPlayer2Score() throws IOException, ParseException {
        return OCR_calls.getText(getImage(basePath + "" + frame + "Crop7.jpg"));
    }

    public String getHours() throws IOException, ParseException {
        String output = OCR_calls.getText(getImage(basePath + "" + frame + "Crop8.jpg"));
        if (output.split(":").length != 2) return "ERROR";
        else return output.split(":")[0];
    }

    public String getMinutes() throws IOException, ParseException {
        String output = OCR_calls.getText(getImage(basePath + "" + frame + "Crop8.jpg"));
        if (output.split(":").length != 2) return "ERROR";
        else return output.split(":")[1];
    }

    public String getEmotion() throws IOException, ParseException {
        return EMO_calls.getEmotion(getImage(basePath + "" + frame + ".png"));
    }



}
