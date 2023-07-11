import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 The ReadJson class provides methods to read JSON files and retrieve their contents.
 It utilizes the Jackson ObjectMapper for JSON parsing and mapping.
 */
public class ReadJson {
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Retrieves the contents of the codeQuestions.json file.
     *
     * @return A Map representing the code questions.
     */
    public Map<?, ?> getJsonCodeQuestions() {
        try {
            return objectMapper.readValue(new File("data/codeQuestions.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the contents of the codeAnswers.json file.
     *
     * @return A Map representing the code answers.
     */
    public Map<String, List<String>> getJsonCodeAnswers() {

        try {
            return objectMapper.readValue(new File("data/codeAnswers.json"),
                    new TypeReference<Map<String, List<String>>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the contents of the flowchartAnswers.json file.
     *
     * @return A Map representing the flowchart answers.
     */
    public Map<String, Map<String, Integer>> getJsonFlowchartAnswers() {

        try {
            return objectMapper.readValue(new File("data/flowchartAnswers.json"),
                    new TypeReference<Map<String, Map<String, Integer>>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the contents of the userFlowchartAnswers.json file.
     *
     * @return A Map representing the user flowchart answers.
     */
    public Map<String, Map<String, Integer>> getJsonUserFlowchartAnswers() {

        try {
            return objectMapper.readValue(new File("data/userFlowchartAnswers.json"),
                    new TypeReference<Map<String, Map<String, Integer>>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the user answers for the given username from the userCodeAnswers.json file.
     *
     * @param username The username for which to retrieve the answers.
     * @return A Map representing the user answers.
     */
    public Map<String, List<String>> getJsonUserAnswers(String username) {
        try {
            Map<String, Map<String, List<String>>> userAnswers = objectMapper.readValue(new File("data/userCodeAnswers.json"),
                    new TypeReference<Map<String, Map<String, List<String>>>>() {
                    });
            return userAnswers.get(username);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves the contents of the codeHints.json file.
     *
     * @return A Map representing the code hints.
     */
    public Map<String, List<String>> getJsonCodeHints() {
        try {
            return objectMapper.readValue(new File("data/codeHints.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the contents of the codeToFlowchartHints.json file.
     *
     * @return A Map representing the hints for code to flowchart conversion.
     */
    public Map<String, List<String>> getJsonCodeToFlowchartHints() {
        try {
            return objectMapper.readValue(new File("data/codeToFlowchartHints.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves the contents of the FlowchartToCodeHints.json file.
     * @return A Map representing the hints for flowchart to code conversion.
     */
    public Map<String, List<String>> getJsonFlowchartToCodeHints() {
        try {
            return objectMapper.readValue(new File("data/FlowchartToCodeHints.json"), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
