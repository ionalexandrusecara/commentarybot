package FrameIterator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import ImagePreProcess.ImagePreProcess;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.AndroidUtil;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;

public class FrameIterator{

    public static void main(String []args) throws IOException, Exception, JCodecException
    {
        File input = new File("videos/seq3.mp4");
        File output = new File("C:\\CommentaryBot\\CommentaryBot\\commentarybot\\frames\\frame");
        if(input.exists()){
            System.out.println("___");
        }

        ImagePreProcess ipp = new ImagePreProcess("cropCoordinates.csv");

        int frameNumber;

        Picture picture;
        BufferedImage bufferedImage;

        for(frameNumber = 0; frameNumber <= 1100; frameNumber=frameNumber+30){
            picture = FrameGrab.getFrameFromFile(input, frameNumber);

            //for JDK (jcodec-javase)
            bufferedImage = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bufferedImage, "png", new File(output + "" + frameNumber + ".png"));

            ImagePreProcess.storeCrops(frameNumber, ipp.getSubImage(bufferedImage));

        }
    }
}