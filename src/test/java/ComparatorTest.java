import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComparatorTest {

    @Test
    public void userSubmitMessageIncorrectAnswers() {
        Comparator userMessage = new Comparator();
        List<String> userAnswers = new ArrayList<>();

        userAnswers.add("int");
        userAnswers.add("5");

//        assertEquals("You answered the following incorrectly:\n" +
//                "- int\n" +
//                "- 5\n", userMessage.userCodeSubmitMessage(userAnswers));
    }

    @Test
    public void userSubmitMessageCorrectAnswers() {
        Comparator userMessage = new Comparator();
        List<String> userAnswers = new ArrayList<>();

        assertEquals("Congratulations! You answered all questions correctly.",
                userMessage.userCodeSubmitMessage(userAnswers));
    }

    @Test
    public void userCodeComparator() {
        Comparator comp = new Comparator();
        List<String> userAnswers = new ArrayList<>();
        List<String> correctAnswer = new ArrayList<>();

        userAnswers.add("int");
        userAnswers.add("5");
        correctAnswer.add("int");
        correctAnswer.add("5");

        assertEquals(comp.compareCodeAnswers(userAnswers, correctAnswer),
                new ArrayList<>());
    }
}