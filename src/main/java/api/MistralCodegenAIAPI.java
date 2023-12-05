package api;

import entity.ConcreteIdea;
import entity.Idea;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * Concrete implementation of the GenerativeAIAPI interface.
 * AI Models used:
 *  <a href="https://huggingface.co/Salesforce/codegen-350M-mono">Salesforce/codegen-350M-mono</a>
 *  <a href="https://huggingface.co/mistralai/Mistral-7B-Instruct-v0.1">mistralai/Mistral-7B-Instruct-v0.1</a>
 * @author Sidharth Sawhney
 */
public class MistralCodegenAIAPI implements GenerativeAIAPI {
    private String API_TOKEN;

    /**
     * Generates business model for a given idea prompt with the <a href="https://huggingface.co/Salesforce/codegen-350M-mono">Salesforce/codegen-350M-mono</a> and <a href="https://huggingface.co/mistralai/Mistral-7B-Instruct-v0.1">mistralai/Mistral-7B-Instruct-v0.1</a> AI models
     * @param idea the idea for which the business model must be generated
     * @param testForException a flag variable useful for testing purposes (set to false for normal execution)
     * @return the business model for the idea prompt
     * @throws Exception IOException or NullPointerException or JSONException
     */
    @Override
    public String generateBusinessModel(Idea idea, boolean testForException) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Random random = new Random();
        MediaType mediaType = MediaType.parse("application/json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/api/API_TOKEN"));
        API_TOKEN = bufferedReader.readLine();
        int i = 0;
        JSONObject generatedText = null;
        JSONObject requestBodyJson = new JSONObject();

        requestBodyJson.put("inputs", idea.getIdea() + "Write a Business model for this idea?");

        while (i < 10) { //TODO: set to i < 50 during final submission, we are not setting it now so that our code runs quickly as we develop
            int randomKeyIndex = random.nextInt(2); // chooses 0 for Mistral, 1 for Codegen

            RequestBody body = RequestBody.create(mediaType, requestBodyJson.toString());
            Request request;
            if (i == 0) { // Mistral return format generally ensures the desired format for the output therefore we force to begin the iterations with Mistral.
                request = new Request.Builder()
                        .url("https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.1")
                        .post(body)
                        .addHeader("Authorization", API_TOKEN)
                        .addHeader("Content-Type", "application/json")
                        .build();
                }
            else {
                if (randomKeyIndex == 0) {
                    request = new Request.Builder()
                            .url("https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.1")
                            .post(body)
                            .addHeader("Authorization", API_TOKEN)
                            .addHeader("Content-Type", "application/json")
                            .build();
                } else {
                    request = new Request.Builder()
                            .url("https://api-inference.huggingface.co/models/Salesforce/codegen-350M-mono")
                            .post(body)
                            .addHeader("Authorization", API_TOKEN)
                            .addHeader("Content-Type", "application/json")
                            .build();
                }
            }
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    JSONArray responseArray = new JSONArray(responseBody);

                    if (testForException) // to test that the exception is caught and handled
                        responseArray = null;

                    if (!responseArray.isEmpty()) {
                        generatedText = responseArray.getJSONObject(0);

                        if (generatedText.getString("generated_text").equals(requestBodyJson.getString("inputs"))) {
                            return idea.getIdea();
                        }
                    } else {
                        return "Empty response array.";
                    }
                } else {
                    return idea.getIdea();
                }

            } catch (IOException | NullPointerException | JSONException e) {
                throw new Exception("Unable to produce Business Model");
            }

            requestBodyJson.remove("inputs");
            requestBodyJson.put("inputs", generatedText.getString("generated_text"));
            i++;
        }
        return generatedText.getString("generated_text");
    }


    public static void main(String[] args) {
        Idea idea = new ConcreteIdea("A 3D printing platform that helps business owners create 3D-printed products");

        GenerativeAIAPI obj = new MistralCodegenAIAPI();
        try {
            System.out.println(obj.generateBusinessModel(idea, false));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
}
