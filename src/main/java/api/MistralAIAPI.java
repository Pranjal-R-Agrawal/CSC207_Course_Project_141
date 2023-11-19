package api;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import entity.ConcreteIdea;
import entity.Idea;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;

public class MistralAIAPI implements GenerativeAIAPI {
    private String API_TOKEN;

    @Override
    public String generateBusinessModel(Idea idea) throws Exception {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/java/api/API_TOKEN"));
        API_TOKEN = bufferedReader.readLine();
        JSONObject requestBodyJson = new JSONObject();

        requestBodyJson.put("inputs", idea.getIdea()+"Write a Business model for this idea?");

        RequestBody body = RequestBody.create(mediaType, requestBodyJson.toString());
        Request request = new Request.Builder()
                .url("https://api-inference.huggingface.co/models/mistralai/Mistral-7B-Instruct-v0.1")
                .post(body)
                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONArray responseArray = new JSONArray(responseBody);

                if (!responseArray.isEmpty()) {
                    JSONObject generatedText = responseArray.getJSONObject(0);

                    return generatedText.getString("generated_text");
                } else {
                    return "Empty response array.";
                }
            } else {
                return "Request failed with code: " + response.code();
            }

        } catch (IOException | NullPointerException | JSONException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new Exception("Couldn't generate business model");
        }
    }


    public static void main(String[] args)
    {
        Idea idea = new ConcreteIdea("A 3D printing platform that helps business owners create 3D-printed products.");

        GenerativeAIAPI obj = new MistralAIAPI();
        try {
            System.out.println(obj.generateBusinessModel(idea));
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }

    }
}
