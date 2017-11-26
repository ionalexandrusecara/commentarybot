package CommentaryBuilder;

import API_calls.OCR_calls;
import ImagePreProcess.ImagePreProcess;
import ImageProcessing.ImageProcessing;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Saad Moussadek on 25/11/2017.
 */
public class MAIN {


    public static void main(String[] args) throws IOException, InterruptedException, ParseException{
        //run0();
        //run1();
        //run2();
        run3();
    }

    public static void textToSpeech0(String commentary, long offset) throws IOException, InterruptedException {
        Desktop.getDesktop().open(new File("videos/seq0.mp4"));
        TextToSpeech tts = new TextToSpeech();
        tts.issueToken();
        // _ , 18750, 13000, 9500
        Thread.sleep(18750 + offset);
        tts.setSpeech(1000, "high", commentary);
    }

    public static void textToSpeech1(String commentary, long offset) throws IOException, InterruptedException {
        Desktop.getDesktop().open(new File("videos/seq1.mp4"));
        TextToSpeech tts = new TextToSpeech();
        tts.issueToken();
        // _ , 13000, 9500
        Thread.sleep(13000 + offset);
        tts.setSpeech(1000, "high", commentary);
    }

    public static void run0() throws IOException, ParseException, InterruptedException {
        ImageProcessing ip = new ImageProcessing(30, 1100, "./frames/Sample1/frame");
        Venue venue = new Venue(" Australia ", " Melbourne Open ", " finals ", " prestigious ");
        GameScore gameScore = new GameScore(ip.getPlayer1Set(), ip.getPlayer2Set(), ip.getPlayer1Set3(), ip.getPlayer2Set3(), ip.getPlayer1Score(), ip.getPlayer2Score());
        CommentaryBuilder commentaryBuilder = new CommentaryBuilder("Serena Williams", "Angelique Kerber", gameScore, venue);
        commentaryBuilder.updateDataSets(gameScore, ip.getHours(), ip.getMinutes(), "Angelique Kerber", "focus");
        textToSpeech0(commentaryBuilder.makeCommentary(), OCR_calls.endTime);
    }

    public static void run1() throws IOException, ParseException, InterruptedException {
        ImageProcessing ip = new ImageProcessing(30, 1100, "./frames/Sample2/frame");
        Venue venue = new Venue(" Australia ", " Melbourne Open ", " finals ", " prestigious ");
        GameScore gameScore = new GameScore(ip.getPlayer1Set(), ip.getPlayer2Set(), ip.getPlayer1Set3(), ip.getPlayer2Set3(), ip.getPlayer1Score(), ip.getPlayer2Score());
        CommentaryBuilder commentaryBuilder = new CommentaryBuilder("Serena Williams", "Angelique Kerber", gameScore, venue);
        commentaryBuilder.updateDataSets(gameScore, ip.getHours(), ip.getMinutes(), "Serena Williams", "focus");
        textToSpeech1(commentaryBuilder.makeCommentary(), OCR_calls.endTime);
    }

    public static void textToSpeech2(String commentary, long offset) throws IOException, InterruptedException {
        Desktop.getDesktop().open(new File("videos/seq2.mp4"));
        TextToSpeech tts = new TextToSpeech();
        tts.issueToken();
        // _ , 13000, 9500
        Thread.sleep(9500 + offset);
        tts.setSpeech(1000, "high", commentary);
    }

    public static void run2() throws IOException, ParseException, InterruptedException {
        ImageProcessing ip = new ImageProcessing(30, 1100, "./frames/Sample3/frame");
        Venue venue = new Venue(" Australia ", " Melbourne Open ", " finals ", " prestigious ");
        GameScore gameScore = new GameScore(ip.getPlayer1Set(), ip.getPlayer2Set(), ip.getPlayer1Set3(), ip.getPlayer2Set3(), ip.getPlayer1Score(), ip.getPlayer2Score());
        CommentaryBuilder commentaryBuilder = new CommentaryBuilder("Serena Williams", "Angelique Kerber", gameScore, venue);
        commentaryBuilder.updateDataSets(gameScore, ip.getHours(), ip.getMinutes(), "Serena Williams", "focus");
        textToSpeech2(commentaryBuilder.makeCommentary(), OCR_calls.endTime);
    }

    public static void textToSpeech3(String commentary, long offset) throws IOException, InterruptedException {
        Desktop.getDesktop().open(new File("videos/seq3.mp4"));
        TextToSpeech tts = new TextToSpeech();
        tts.issueToken();
        // _ , 13000, 9500
        Thread.sleep(9500 + offset);
        tts.setSpeech(1000, "high", commentary);
    }

    public static void run3() throws IOException, ParseException, InterruptedException {
        ImageProcessing ip = new ImageProcessing(30, 1100, "./frames/Sample4/frame");
        Venue venue = new Venue(" Australia ", " Melbourne Open ", " finals ", " prestigious ");
        GameScore gameScore = new GameScore(ip.getPlayer1Set(), ip.getPlayer2Set(), ip.getPlayer1Set3(), ip.getPlayer2Set3(), ip.getPlayer1Score(), ip.getPlayer2Score());
        CommentaryBuilder commentaryBuilder = new CommentaryBuilder("Serena Williams", "Angelique Kerber", gameScore, venue);
        commentaryBuilder.updateDataSets(gameScore, ip.getHours(), ip.getMinutes(), "Angelique Kerber", "despair");
        textToSpeech3(commentaryBuilder.makeCommentary(), OCR_calls.endTime);
    }

}
