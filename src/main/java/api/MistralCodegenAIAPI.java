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

public class MistralCodegenAIAPI implements GenerativeAIAPI {
    private String API_TOKEN;

    @Override
    public String generateBusinessModel(Idea idea) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Random random = new Random();
        MediaType mediaType = MediaType.parse("application/json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/api/API_TOKEN"));
        API_TOKEN = bufferedReader.readLine();
        int i = 0;
        JSONObject generatedText = null;
        JSONObject requestBodyJson = new JSONObject();
        try {
            requestBodyJson.put("inputs", idea.getIdea() + "Write a Business model for this idea?");
        } catch (NullPointerException e) // Helpful for the test case in GenerateIdeaUseCaseTest.java
        {
            throw new Exception("Unable to produce Business Model");
        }
        while (i < 10) { //TODO: set to i < 50 during final submission
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
                JOptionPane.showMessageDialog(null, e.getMessage());
                throw new Exception("Unable to produce Business Model");
            }

            requestBodyJson.remove("inputs");
            requestBodyJson.put("inputs", generatedText.getString("generated_text"));
            i++;
        }
        return generatedText.getString("generated_text");
    }


    public static void main(String[] args) {
        Idea idea = new ConcreteIdea("A “social” or “dynamic” sales and marketing platform for small business owners. The startup offers an online platform for users to create customized sales and marketing campaigns.");


        GenerativeAIAPI obj = new MistralCodegenAIAPI();
        try {
            System.out.println(obj.generateBusinessModel(idea));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
}
