package FrameIterator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
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
        File input = new File("D:/University of St. Andrews/Hackathons/OxfordHack/commentarybot/videos/anim.mp4");
        File output = new File("D:/University of St. Andrews/Hackathons/OxfordHack/commentarybot/frames/frame");
        if(input.exists()){
            System.out.println("___");
        }

        int frameNumber;

        Picture picture;
        BufferedImage bufferedImage;

        for(frameNumber = 2000; frameNumber <= 2050; frameNumber=frameNumber+10){
            picture = FrameGrab.getFrameFromFile(input, frameNumber);

            //for JDK (jcodec-javase)
            bufferedImage = AWTUtil.toBufferedImage(picture);
            ImageIO.write(bufferedImage, "png", new File(output + "" + frameNumber + ".png"));
        }
    }
}