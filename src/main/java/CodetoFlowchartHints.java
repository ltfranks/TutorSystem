import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * The CodetoFlowchartHints class handles processing flowchart hints for code-to-flowchart questions.
 */
public class CodetoFlowchartHints {

    /**
     * Retrieves the code-to-flowchart questions.
     * @return The list of code-to-flowchart questions.
     */
    public ArrayList<String> getQuestions() {
        ArrayList<String> questions = new ArrayList<>();
        ReadJson r = new ReadJson();
        for (Object value : r.getJsonFlowchartAnswers().values()) {
            String question = (String) value;
            int colonIndex = question.indexOf(":");
            if (colonIndex != -1) {
                String questionText = question.substring(0, colonIndex + 1);
                questions.add(questionText);
            }
        }
        return questions;
    }
    /**
     * Processes the flowchart question hints using ChatGPT.
     * @param gpt The ChatGPT instance.
     * @param question The code-to-flowchart question.
     * @return The list of flowchart hints.
     * @throws Exception if an error occurs during the ChatGPT interaction.
     */
    private List<String> processFlowchartQuestionHints(ChatGPT gpt, String question) throws Exception {
        List<String> hints = new ArrayList<>();
        String output = gpt.getChatGPTResponse("Provide three short hints in the format 1.,2.,3. for the " +
                        "following Java Programming Question:" + question + "provide hints for this question in terms " +
                        "of drawing flowcharts. Flowcharts shapes can be represented as: Suitcase shape as Call a " +
                        "Method, Rectangle as Instruction, Paralellogram as Input or Output, Rectangle with lines as " +
                        "Variable declaration, diamond as Condition and Circles as Begina and End.");
        String[] hintArray = output.split("\\d+\\.\\s");

        for (int i = 1; i < hintArray.length; i++) {
            hints.add(hintArray[i].trim());
        }
        return hints;
    }
    /**
     * Retrieves the flowchart hints for the given code-to-flowchart questions.
     * @param quesArray The list of code-to-flowchart questions.
     * @return The map of flowchart hints, where the key is the question ID and the value is the list of hints.
     */
    public HashMap<String, List<String>> getFlowchartHints(ArrayList<String> quesArray){
        HashMap<String, List<String>> flowchartHintsMap = new HashMap<>();
        ChatGPT gpt = new ChatGPT();
        int counter = 1;

        for (String val : quesArray) {
            try {
                List<String> hints = processFlowchartQuestionHints(gpt, val);
                String key = "q" + counter;
                if (counter < 6) {
                    flowchartHintsMap.put(key, hints);
                } else {
                    flowchartHintsMap.put("bonus", hints);
                }
                counter++;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return flowchartHintsMap;
    }
}
