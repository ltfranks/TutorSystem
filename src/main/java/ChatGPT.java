import org.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import io.github.cdimascio.dotenv.Dotenv;

/**
 * The ChatGPT class handles interactions with the ChatGPT API.
 * It provides a method to get a response from the ChatGPT model.
 */
public class ChatGPT {
    private String conversationHistory = "";
    Dotenv dotenv = Dotenv.configure().load();

    /**
     * Gets a response from the ChatGPT model based on the user input.
     * @param userInput The user's input.
     * @return The response from the ChatGPT model.
     * @throws Exception if an error occurs during the API call.
     */

    public String getChatGPTResponse(String userInput) throws Exception {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        String API_KEY = "Bearer " + dotenv.get("GPT_KEY");

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", API_KEY);

        conversationHistory += userInput + "\n";

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", conversationHistory);
        data.put("max_tokens", 2048);
//        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream()))
                .lines().reduce((a, b) -> a + b).get();

        // Extract the model's response and append it to the conversation history
        String modelResponse = new JSONObject(output).getJSONArray("choices")
                .getJSONObject(0).getString("text");
        conversationHistory += modelResponse + "\n";

        return modelResponse;
    }
}
