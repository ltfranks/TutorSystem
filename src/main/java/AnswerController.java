import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
/**
 * The AnswerController class handles user interactions and events in the application.
 * It implements the ActionListener interface.
 */
public class AnswerController extends Observable implements ActionListener{
    private RepositoryInterface repo = Repository.getR();
    private int currentQuestionIndex = 1;

    private final Map<?, ?> questionsMap;
    private final JLabel questionLabel;

    private final Map<String, List<String>> hintsMap;
    private int currentHintIndex = 0;
    private final JLabel hintLabel;
    private String typeOfQuestions;
//    private String typeOfQuestions = "Code to Flowchart";
  
/**
     * Constructs an AnswerController object.
     * It initializes the questionsMap, hintsMap, questionLabel, and hintLabel.
     */

    public AnswerController() {
        ReadJson readJson = new ReadJson();
        questionsMap = readJson.getJsonCodeQuestions();
        hintsMap = readJson.getJsonCodeHints();
        questionLabel = new JLabel();
        hintLabel = new JLabel();
    }
    /**
     * Handles action events.
     * @param e The action event to be handled.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        notifyObservers("write");
        String action = e.getActionCommand();
        if (action.equals("comboBoxChanged"))
        {
            action = (String) repo.getCodeOrFlow().getSelectedItem();
            System.out.println(action);
        }
        switch (action) {
            case "Submit":
                setChanged();
                notifyObservers("submit");
                submitFunction();
                break;
            case "Hint":
                displayHint();
                break;
            case "Next":
                setChanged();
                notifyObservers("next");
                break;
            case "Previous":
                setChanged();
                notifyObservers("previous");
                break;
            case "Flowchart to Code":
                typeOfQuestions = "Flowchart to Code";
                repo.changePanel("Flowchart 2 Code");
                repo.changeQuestionBox(action);
                break;
            case "Code to Flowchart":
                typeOfQuestions = "Code to Flowchart";
                repo.changePanel("Code 2 Flowchart");
                repo.changeQuestionBox(action);
                break;
            }
        }
    /**
     * Displays the current question.
     */
    public void displayQuestion() {
        System.out.println("["+typeOfQuestions+"]");
        System.out.println("["+questionLabel+"]");
        String questionKey = "q" + currentQuestionIndex;
        if (questionsMap.containsKey(questionKey)) {
            String question = questionsMap.get(questionKey).toString();
            questionLabel.setText(question);
            resetHint();
            setChanged();
            notifyObservers(question);
        } else {
            if (currentQuestionIndex > 1) {
                currentQuestionIndex--;
            } else {
                JOptionPane.showMessageDialog(null, "No previous questions available.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            if (currentQuestionIndex > questionsMap.size()) {
                JOptionPane.showMessageDialog(null, "End of questions reached.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

  


  /**
     * Performs the submission of user answers and displays the result.
     * It compares the user answers with the correct answers and generates a message accordingly.
     * The message is displayed in a dialog box.
     */


    private void submitFunction() {
        Set<String> qs = repo.getUserAns().keySet();
        WriteJson write = new WriteJson();
        ReadJson read = new ReadJson();
        Comparator compare = new Comparator();
        for (String x : qs) {
            if(repo.getUserAns().get(x) != null)
                write.updateJsonUserAnswers(repo.getUsername(), x, (ArrayList) repo.getUserAns().get(x));
        }
        Map<String, List<String>> user = read.getJsonUserAnswers(repo.getUsername());//user answers
        Map<String, List<String>> ans = read.getJsonCodeAnswers();                   //correct answers
        List<String> ans2 = new ArrayList<>();                                      //answer holder
        qs = user.keySet();                                             //questions
        for (String x : qs)//finds which questions are wrong
        {
            if (user.get(x).size() > 0)
            {
                if (!compare.compareCodeAnswers(user.get(x), ans.get(x)).isEmpty())//if the question was right
                    ans2.add(x);
            }
        }
        String ans3 = compare.userCodeSubmitMessage(ans2);//message to print
        JOptionPane.showMessageDialog(null, ans3, "Message", JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Displays a hint for the current question, if available.
     * If hints are available, it displays them one by one.
     * If no more hints are available, it shows a message indicating that.
     */
    private void displayHint() {
        if (hintsMap != null && !hintsMap.isEmpty()) {
            List<String> hints = hintsMap.get("q" + currentQuestionIndex);
            if (hints != null && !hints.isEmpty()) {
                if (currentHintIndex < hints.size()) {
                    String hint = hints.get(currentHintIndex);
                    ImageIcon hintIcon = new ImageIcon("src/images/hintImage.png");
                    JOptionPane.showMessageDialog(null, hint, "Hint", JOptionPane.INFORMATION_MESSAGE, hintIcon);
                    currentHintIndex++;
                } else {
                    ImageIcon noMoreIcon = new ImageIcon("src/images/pepeSize.png");
                    JOptionPane.showMessageDialog(null, "No more hints available for this question.", "Out of Hints", JOptionPane.INFORMATION_MESSAGE, noMoreIcon);
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hints available for this question.", "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    /**
     * Returns the index of the current question.
     * @return The current question index.
     */
    public int getCurrentQuestionIndex() {
        return this.currentQuestionIndex;
    }
    /**
     * Sets the index of the current question.
     * @param newIndex The new index to be set.
     */
    public void setCurrentQuestionIndex(int newIndex) {
        this.currentQuestionIndex = newIndex;
    }

    private void resetHint() {
        currentHintIndex = 0;
        hintLabel.setText("");
    }

}

//Json is storing user answers right now. Next sprint we add completed function
