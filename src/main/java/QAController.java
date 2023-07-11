import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * The QAController class implements the ItemListener interface to handle item state change events.
 * It is responsible for handling question selection events in the question dropdown menu.
 */
public class QAController implements ItemListener {
    private AnswerController ac;

    /**
     Constructs a QAController object with the specified AnswerController.
     @param ac the AnswerController object
     */
    public QAController(AnswerController ac){
        this.ac = ac;
    }

    /**
     Handles the itemStateChanged event triggered by the question selection in the dropdown menu.
     Retrieves the selected question option and performs corresponding actions based on the selection.
     @param e the ItemEvent object representing the item state change event
     */
    @Override
    public void itemStateChanged (ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedOption = (String) comboBox.getSelectedItem();
            int questionIndex = ac.getCurrentQuestionIndex();
            switch (selectedOption) {
                case "Code Question 1" ->{
                    ac.setCurrentQuestionIndex(1);
                    ac.displayQuestion();
                }
                case "Code Question 2" ->{
                    ac.setCurrentQuestionIndex(2);
                    ac.displayQuestion();
                }
                case "Code Question 3" ->{
                    ac.setCurrentQuestionIndex(3);
                    ac.displayQuestion();
                }
                case "Code Question 4" ->{
                    ac.setCurrentQuestionIndex(4);
                    ac.displayQuestion();
                }
                case "Code Question 5" ->{
                    ac.setCurrentQuestionIndex(5);
                    ac.displayQuestion();
                }
                case "Flowchart Question 1" ->{
                    ac.setCurrentQuestionIndex(1);
                    try {
                        LoadOrSave.loadObjects("data/diagramQ1.bin");
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case "Flowchart Question 2" ->{
                    ac.setCurrentQuestionIndex(2);
                    try {
                        LoadOrSave.loadObjects("data/diagramQ1.bin");
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                case "Flowchart Question 3" ->{
                    ac.setCurrentQuestionIndex(3);
                    try {
                        LoadOrSave.loadObjects("data/diagramQ1.bin");
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }
    }
}