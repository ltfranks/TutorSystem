import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * This class provides methods for creating and updating JSON files with different data.
 * It utilizes the Jackson library for JSON processing.
 */
public class WriteJson {
    private final RepositoryInterface repo = Repository.getR();

    /**
     * Creates a JSON object and writes it to a file.
     *
     * @param filePath   The file path where the JSON file will be created.
     * @param hashValue  The map representing the JSON object.
     */
    public void createJsonObject(String filePath, Map<String, ?> hashValue) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("data/" + filePath), hashValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Creates a JSON file containing code questions and writes it to "codeQuestions.json".
     */
    public void createJsonCodeQuestions() {
        Map<String, String> codeQuestions = new HashMap<>();

        codeQuestions.put("q1", "Fill in the blanks to print the numbers from 1 to 10 using a for loop: \n" +
                "for (int i = _; i <= _; i++) { System.out.println(_); }");
        codeQuestions.put("q2", "Fill in the blanks to declare a variable x and assign it to the value 5: \n" +
                "_ x = _;");
        codeQuestions.put("q3", "Fill in the blanks to complete the if statement, checking if a number x is positive and printing: \n" +
                "if (x > _) { _(\"Number is positive\"); }");
        codeQuestions.put("q4", "Fill in the blanks to complete the code, finding the maximum value between two numbers a and b, using the correct library: \n" +
                "_ max = _.max(a, b);");
        codeQuestions.put("q5", "Fill in the blanks to complete the code, calculating the sum of all numbers from 1 to n using a loop: \n" +
                "_ sum = 0; for (int i = _; i _ n; i++) { sum += _; }");
        codeQuestions.put("bonus", "Utilizing everything you learned from the previous questions, fill in the blanks to complete the code: \n" +
                "_ n = 10;\n" +
                "_ = excludeNum = 3;\n" +
                "_ sum = 0;\n" +
                "\n" +
                "for (_  i = 1; i <=  n; i++) {\n" +
                "    if (i % 2 == 0 _  i != _) {\n" +
                "        sum _ i;\n" +
                "    }\n" +
                "}\n" +
                "\n" +
                "_(\"The sum of even numbers, excluding \" + excludeNum + \", is: \" + sum);");

        createJsonObject("codeQuestions.json", codeQuestions);
    }

    /**
     * Creates a JSON file containing code answers and writes it to "codeAnswers.json".
     */
    public void createJsonCodeAnswers() {
        Map<String, List<String>> codeAnswers = new HashMap<>();

        codeAnswers.put("q1", Arrays.asList("1", "10", "i"));
        codeAnswers.put("q2", Arrays.asList("int", "5"));
        codeAnswers.put("q3", Arrays.asList("0", "System.out.println"));
        codeAnswers.put("q4", Arrays.asList("int", "Math", "a", "b"));
        codeAnswers.put("q5", Arrays.asList("int", "1", "<=", "i"));
        codeAnswers.put("bonus", Arrays.asList("int", "int", "int", "int", "&&", "excludeNum", "+=", "System.out.println"));

        createJsonObject("codeAnswers.json", codeAnswers);
    }

    /**
     * Updates the JSON file containing user answers.
     *
     * @param username      The username associated with the answers.
     * @param question      The question for which the answers are being updated.
     * @param userAnswers   The list of user answers.
     */
    public void updateJsonUserAnswers(String username, String question, ArrayList<String> userAnswers) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("data/userCodeAnswers.json");

        try {
            Map<String, Map<String, List<String>>> existingAnswers;

            if (jsonFile.exists() && jsonFile.length() > 0) {
                existingAnswers = objectMapper.readValue(jsonFile,
                        new TypeReference<Map<String, Map<String, List<String>>>>() {
                        });
            } else {
                existingAnswers = new HashMap<>();
            }

            existingAnswers.computeIfAbsent(username, k -> new HashMap<>());
            existingAnswers.get(username).put(question, userAnswers);

            createJsonObject("userCodeAnswers.json", existingAnswers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a JSON file containing code hints and writes it to "codeHints.json".
     *
     * @throws Exception If an error occurs while retrieving the hints.
     */
    public void createJsonCodeHints() throws Exception {
        GettingHints parser = new GettingHints();
        ArrayList<String> quesArray = parser.getQuestions();
        HashMap<String, List<String>> hints = parser.getHints(quesArray);
        createJsonObject("codeHints.json", hints);
    }

    /**
     * Creates a JSON file containing code-to-flowchart hints and writes it to "codeToFlowchartHints.json".
     *
     * @throws Exception If an error occurs while retrieving the hints.
     */
    public void createJsonCodeToFlowchartHints() throws Exception {
        CodetoFlowchartHints parser = new CodetoFlowchartHints();
        ArrayList<String> quesArray = parser.getQuestions();
        HashMap<String, List<String>> hints = parser.getFlowchartHints(quesArray);
        createJsonObject("codeToFlowchartHints.json", hints);
    }

    /**
     * Creates a JSON file containing flowchart-to-code hints and writes it to "FlowchartToCodeHints.json".
     *
     * @throws Exception If an error occurs while retrieving the hints.
     */
    public void createJsonFlowchartToCodeHints() throws Exception {
        FlowchartToCodeHints parser = new FlowchartToCodeHints();
        ArrayList<String> quesArray = parser.getQuestions();
        HashMap<String, List<String>> hints = parser.getCodeHints(quesArray);
        createJsonObject("FlowchartToCodeHints.json", hints);
    }

    /**
     * Creates a JSON file containing flowchart answers and writes it to "flowchartAnswers.json".
     */
    public void createJsonFlowchartAnswers() {
        Map<String, Map<String, Integer>> outerMap = new HashMap<>();

        List<String> keys = Arrays.asList("start", "variable declaration", "condition", "output", "instruction", "end", "Lines");

        List<Integer> q1 = Arrays.asList(1, 1, 1, 1, 1, 1, 6);
        List<Integer> q2 = Arrays.asList(1, 1, 0, 0, 0, 1, 3);
        List<Integer> q3 = Arrays.asList(1, 1, 1, 1, 0, 1, 5);
        List<Integer> q4 = Arrays.asList(1, 1, 0, 0, 0, 1, 4);
        List<Integer> q5 = Arrays.asList(1, 1, 0, 1, 2, 1, 7);
        List<List<Integer>> questionValues = Arrays.asList(q1, q2, q3, q4, q5);
        List<String> questions = Arrays.asList("q1", "q2", "q3", "q4", "q5");

        for (int i = 0; i < questions.size(); i++) {
            Map<String, Integer> innerMap = new HashMap<>();

            for (int j = 0; j < keys.size(); j++) {
                innerMap.put(keys.get(j), questionValues.get(i).get(j));
            }

            outerMap.put(questions.get(i), innerMap);
        }

        createJsonObject("flowchartAnswers.json", outerMap);

    }

    /**
     * Creates a JSON file containing user flowchart answers.
     * If the file already exists, merges the new answers with the existing ones.
     */
    public void createJsonFlowchartUserAnswers() {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("data/userFlowchartAnswers.json");

        try {
            Map<String, Map<String, Integer>> newAnswers = repo.getFlowchartAnswers();

            Map<String, Map<String, Integer>> existingAnswers;

            if (jsonFile.exists() && jsonFile.length() > 0) {
                existingAnswers = objectMapper.readValue(jsonFile,
                        new TypeReference<Map<String, Map<String, Integer>>>() {});
            } else {
                existingAnswers = new HashMap<>();
            }

            for (Map.Entry<String, Map<String, Integer>> entry : newAnswers.entrySet()) {
                existingAnswers.merge(entry.getKey(), entry.getValue(), (v1, v2) -> {
                    v1.putAll(v2);
                    return v1;
                });
            }

            createJsonObject("userFlowchartAnswers.json", existingAnswers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
