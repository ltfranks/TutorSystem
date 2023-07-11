import java.util.ArrayList;
import java.util.List;
/**
 * The Comparator class provides methods for comparing user code answers with correct answers
 * and generating a message based on the comparison results.
 */
public class Comparator {
    /**
     * Compares the user code answers with the correct answers and returns the list of wrong answers.
     * @param userAnswers The list of user code answers.
     * @param correctAnswers The list of correct code answers.
     * @return The list of wrong user code answers.
     */
    public List<String> compareCodeAnswers(List<String> userAnswers, List<String> correctAnswers) {
        List<String> wrongUserAnswers = new ArrayList<>();

        for (int i = 0; i < correctAnswers.size(); i++) {
            if (!userAnswers.get(i).equals(correctAnswers.get(i))) {
                wrongUserAnswers.add(userAnswers.get(i));
            }
        }
        return wrongUserAnswers;
    }


    /**
     * Generates a submission message based on the list of wrong user code answers.
     * @param wrongUserAnswers The list of wrong user code answers.
     * @return The submission message.
     */
    public String userCodeSubmitMessage(List<String> wrongUserAnswers) {
        if (wrongUserAnswers.isEmpty()) {
            return "Congratulations! You answered all questions correctly.";
        } else {
            StringBuilder message = new StringBuilder("You answered the following questions incorrectly:\n");
            for (String answer : wrongUserAnswers) {
                message.append("- ").append(answer).append("\n");
            }
            return message.toString();
        }
    }
}
