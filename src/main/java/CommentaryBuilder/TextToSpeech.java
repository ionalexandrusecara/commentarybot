package CommentaryBuilder;

// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

import java.io.*;
import java.net.URI;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TextToSpeech
{
    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace the subscriptionKey string value with your valid subscription key.
    public static final String subscriptionKey = "27085e3f517e49cb9d5bd9f441825a5a";

    private String auth = "";
    public void issueToken() {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            // NOTE: You must use the same location in your REST call as you used to obtain your subscription keys.
            //   For example, if you obtained your subscription keys from westus, replace "westcentralus" in the
            //   URL below with "westus".
            URIBuilder uriBuilder = new URIBuilder("https://api.cognitive.microsoft.com/sts/v1.0/issueToken");

            // Request parameters.
            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Execute the REST API call and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                System.out.println("REST Response:\n");
                System.out.println(jsonString);
                auth = jsonString;
            }
        }
        catch (Exception e)
        {
            // Display error message.
            System.out.println(e.getMessage());
        }
    }

    public void setSpeech(long timeStamp, String pitch, String message)
    {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            // NOTE: You must use the same location in your REST call as you used to obtain your subscription keys.
            //   For example, if you obtained your subscription keys from westus, replace "westcentralus" in the
            //   URL below with "westus".
            URIBuilder uriBuilder = new URIBuilder("https://speech.platform.bing.com/synthesize");

            // Request parameters.
            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/ssml+xml");
            request.setHeader("X-Microsoft-OutputFormat", "audio-16khz-32kbitrate-mono-mp3");
            request.setHeader("Authorization", auth);

            StringEntity requestEntity =
                    new StringEntity("<speak version='1.0' xml:lang='en-US'><voice xml:lang='en-US' xml:gender='Male' name='Microsoft Server Speech Text to Speech Voice (en-US, ZiraRUS)'><prosody pitch=\"" + pitch + "\">" + message + "</prosody></voice></speak>");
            request.setEntity(requestEntity);

            // Execute the REST API call and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                // Format and display the JSON response.
                byte[] audio = EntityUtils.toByteArray(entity);
                System.out.println("REST Response:\n");
                System.out.println(audio);
                System.out.println(response.getStatusLine());
                FileOutputStream fos = new FileOutputStream(timeStamp + ".mp3");
                fos.write(audio);
                fos.close();
                try {
                    final JFXPanel fxPanel = new JFXPanel();
                    File f = new File(timeStamp + ".mp3");
                    Media hit = new Media(f.toURI().toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(hit);
                    mediaPlayer.play();

                } catch(Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Exception: " + ex.getMessage());
                }

            }
        }
        catch (Exception e)
        {
            // Display error message.
            System.out.println(e.getMessage());
        }
    }
}