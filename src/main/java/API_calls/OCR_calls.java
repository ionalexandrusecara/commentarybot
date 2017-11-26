package API_calls;

// This sample uses the Apache HTTP client library(org.apache.httpcomponents:httpclient:4.2.4)
// and the org.json library (org.json:json:20170516).

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

public class OCR_calls {
    public static long endTime;

    // **********************************************
    // *** Update or verify the following values. ***
    // **********************************************

    // Replace the subscriptionKey string value with your valid subscription key.
    public static final String subscriptionKey = "9923477b2bc74b2896012b8bbe8d75f4";

    // Replace or verify the region.
    //
    // You must use the same region in your REST API call as you used to obtain your subscription keys.
    // For example, if you obtained your subscription keys from the westus region, replace
    // "westcentralus" in the URI below with "westus".
    //
    // NOTE: Free trial subscription keys are generated in the westcentralus region, so if you are using
    // a free trial subscription key, you should not need to change this region.
    //
    // Also, if you want to use the celebrities model, change "landmarks" to "celebrities" here and in
    // uriBuilder.setParameter to use the Celebrities model.
    public static final String uriBase = "https://westeurope.api.cognitive.microsoft.com/vision/v1.0/ocr";

    public static void main(String[] args) throws IOException, ParseException {
        BufferedImage image = ImageIO.read(new File("./frames/Sample1/frame450.png"));
        System.out.println(getText(image));
    }

    public static String getText(BufferedImage image) throws ParseException {
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject json = (JSONObject) jsonParser.parse(OCR_call(image));
            JSONObject region = (JSONObject) ((JSONArray) json.get("regions")).get(0);
            JSONObject line = (JSONObject) ((JSONArray) region.get("lines")).get(0);
            JSONObject word = (JSONObject) ((JSONArray) line.get("words")).get(0);
            String text = (String) word.get("text");
            System.out.println(text);
            return text;
        } catch (IndexOutOfBoundsException e) {
            return "ERROR";
        }
    }

    public static String OCR_call(BufferedImage image)
    {
        long startTime = System.currentTimeMillis();
        HttpClient httpClient = new DefaultHttpClient();

        try
        {
            // NOTE: You must use the same location in your REST call as you used to obtain your subscription keys.
            //   For example, if you obtained your subscription keys from westus, replace "westcentralus" in the
            //   URL below with "westus".
            URIBuilder uriBuilder = new URIBuilder(uriBase);

            uriBuilder.setParameter("language", "unk");
            uriBuilder.setParameter("detectOrientation ", "true");

            // Request parameters.
            URI uri = uriBuilder.build();
            HttpPost request = new HttpPost(uri);

            // Request headers.
            request.setHeader("Content-Type", "application/octet-stream");
            request.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Request body.
            //StringEntity requestEntity =
              //      new StringEntity("{\"url\":\"" +
                //            "https://image.noelshack.com/fichiers/2017/47/7/1511666113-frame0crop8.jpg" +
                  //          "\"}");
            //request.setEntity(requestEntity);

            ByteArrayOutputStream b = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", b);
            byte[] jpgByteArray = b.toByteArray();
            request.setEntity(new ByteArrayEntity(jpgByteArray));

            // Execute the REST API call and get the response entity.
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                // Format and display the JSON response.
                String jsonString = EntityUtils.toString(entity);
                System.out.println("REST Response:\n");
                System.out.println(jsonString);
                endTime = System.currentTimeMillis() - startTime;
                return jsonString;
            }
        }
        catch (Exception e)
        {
            // Display error message.
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
        return null;
    }
}