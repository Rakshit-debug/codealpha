import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleChatbot {

    private Map<String, String> responses;
    private List<String> greetings;
    private List<String> farewells;

    public SimpleChatbot() {
        responses = new HashMap<>();
        greetings = Arrays.asList("hello", "hi", "hey", "greetings");
        farewells = Arrays.asList("bye", "goodbye", "see you", "farewell");

        // Simple response mapping
        responses.put("how are you", "I'm doing well, thank you!");
        responses.put("what is your name", "I'm a simple chatbot.");
        responses.put("tell me a joke", "Why don't scientists trust atoms? Because they make up everything!");
        responses.put("default", "I'm not sure I understand.");
    }

    public String processInput(String input) {
        input = input.toLowerCase();

        // Greeting check
        if (greetings.contains(input)) {
            return "Hello there!";
        }

        // Farewell check
        if (farewells.contains(input)) {
            return "Goodbye!";
        }

        // Simple pattern matching for more complex questions.
        if (input.contains("weather")) {
            return getWeather(input);
        }

        // Basic keyword matching
        if (responses.containsKey(input)) {
            return responses.get(input);
        } else {
            // Very basic ML-ish approach using word matching
            String bestMatch = findBestMatch(input);
            return responses.getOrDefault(bestMatch, responses.get("default"));
        }
    }

    private String findBestMatch(String input) {
        String[] inputWords = input.split("\\s+");
        int bestMatchCount = 0;
        String bestMatchKey = "default";

        for (String key : responses.keySet()) {
            if (key.equals("default")) continue; // Skip default.
            String[] keyWords = key.split("\\s+");
            int matchCount = 0;
            for (String inputWord : inputWords) {
                for (String keyWord : keyWords) {
                    if (inputWord.equals(keyWord)) {
                        matchCount++;
                    }
                }
            }
            if (matchCount > bestMatchCount) {
                bestMatchCount = matchCount;
                bestMatchKey = key;
            }
        }
        return bestMatchKey;
    }

    private String getWeather(String input) {
        // Very basic weather extraction. More robust would require external API.
        Pattern pattern = Pattern.compile("weather in (\\w+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String city = matcher.group(1);
            return "The weather in " + city + " is currently sunny (simulated).";
        } else {
            return "Please specify a city for the weather.";
        }
    }

    public static void main(String[] args) {
        SimpleChatbot chatbot = new SimpleChatbot();
        String input = "Hello";
        System.out.println("User: " + input);
        System.out.println("Chatbot: " + chatbot.processInput(input));

        input = "how are you";
        System.out.println("User: " + input);
        System.out.println("Chatbot: " + chatbot.processInput(input));

        input = "tell me a joke";
        System.out.println("User: " + input);
        System.out.println("Chatbot: " + chatbot.processInput(input));

        input = "weather in London";
        System.out.println("User: " + input);
        System.out.println("Chatbot: " + chatbot.processInput(input));

        input = "random question";
        System.out.println("User: " + input);
        System.out.println("Chatbot: " + chatbot.processInput(input));

        input = "goodbye";
        System.out.println("User: " + input);
        System.out.println("Chatbot: " + chatbot.processInput(input));
    }
}