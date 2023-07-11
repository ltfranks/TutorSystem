import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlowchartToCodeHints {
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

    private List<String> processFlowchartQuestionHints(ChatGPT gpt, String question) throws Exception {
        List<String> hints = new ArrayList<>();
        String output = gpt.getChatGPTResponse("Provide three short hints in the format 1.,2.,3. for the " +
                "following Java Programming Question:" + question + "provide hints for this question in terms " +
                "of how the code would look like when drawing flowcharts.");
        String[] hintArray = output.split("\\d+\\.\\s");

        for (int i = 1; i < hintArray.length; i++) {
            hints.add(hintArray[i].trim());
        }
        return hints;
    }

    public HashMap<String, List<String>> getCodeHints(ArrayList<String> quesArray){
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
