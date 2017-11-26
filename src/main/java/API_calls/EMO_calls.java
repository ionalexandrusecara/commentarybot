package API_calls;
// // This sample uses the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.apache.axis.utils.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;

public class EMO_calls {

    public static void main(String[] args) throws IOException, ParseException {
        BufferedImage image = ImageIO.read(new File("./frames/frame0Crop0.jpg"));
        System.out.println(getEmotion(image));
        //getText(image);
    }

    public static String getEmotion(BufferedImage image) throws ParseException {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject json = (JSONObject) ((JSONArray) jsonParser.parse(emo_call(image))).get(0);
            JSONObject emotions = (JSONObject) json.get("scores");


            String highestKey = "";
            Double highestValue = 0.8;

            for (Object key : emotions.keySet()) {
                Double value = Double.parseDouble(emotions.get(key).toString());
                if (value > highestValue) {
                    highestKey = key.toString();
                    highestValue = value;
                }
            }
            if (highestKey.equals("")) return "ERROR";
            return highestKey.toString();
        } catch (IndexOutOfBoundsException e) {
            return "ERROR";
        }
    }


    public static String emo_call(BufferedImage image) {
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            // NOTE: You must use the same region in your REST call as you used to obtain your subscription keys.
            //   For example, if you obtained your subscription keys from westcentralus, replace "westus" in the
            //   URL below with "westcentralus".
            URIBuilder uriBuilder = new URIBuilder("https://westus.api.cognitive.microsoft.com/emotion/v1.0/recognize");

            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers. Replace the example key below with your valid subscription key.
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", "ceed2569f9e44ff0aaef4df148a83722");

            // Request body. Replace the example URL below with the URL of the image you want to analyze.
            //StringEntity reqEntity = new StringEntity("{ \"url\": \"https://image.noelshack.com/fichiers/2017/47/7/1511660184-frame3078.png\" }");
            //request.setEntity(reqEntity);

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", b);
            byte[] jpgByteArray = b.toByteArray();
            request.setEntity(new ByteArrayEntity(jpgByteArray));

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                //System.out.println(EntityUtils.toString(entity));
                return EntityUtils.toString(entity);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return "IGNORE";
    }
}