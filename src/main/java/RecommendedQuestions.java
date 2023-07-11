import javax.swing.*;
import java.awt.*;

/**
 * A class that displays a pop-up window with recommended questions based on the progress in different categories.
 */
public class RecommendedQuestions {
    /**
     * Displays a pop-up window with recommended questions based on the progress in different categories.
     * The recommended questions are displayed in a grid layout with labels indicating the difficulty level and question numbers.
     * The progress is obtained from the Repository object.
     */
    public void displayPopUpWindow() {
        Repository repo = Repository.getR();
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JLabel loopsQuestions, conditionalQuestions, metricsQuestions, diagramQuestions;

        if (repo.getLoopsProgress() < 33){
            loopsQuestions = new JLabel("Loops Questions - Easy - 1, 2, 3, 4");
        } else if (repo.getLoopsProgress() >= 33 && repo.getLoopsProgress() < 66) {
            loopsQuestions = new JLabel("Loops Questions - Medium - 5, 6, 7");
        } else{
            loopsQuestions = new JLabel("Loop Questions - Hard - 8, 9, 10");
        }

        if (repo.getConditionalProgress() < 33){
            conditionalQuestions = new JLabel("Conditional Questions - Easy - 1, 2, 3, 4");
        } else if (repo.getConditionalProgress() >= 33 && repo.getConditionalProgress() < 66) {
            conditionalQuestions = new JLabel("Conditional Questions - Medium - 5, 6, 7");
        } else{
            conditionalQuestions = new JLabel("Conditional Questions - Hard - 8, 9, 10");
        }

        if (repo.getMetricsProgress() < 33){
            metricsQuestions = new JLabel("Metric Questions - Easy - 1, 2, 3, 4");
        } else if (repo.getMetricsProgress() >= 33 && repo.getMetricsProgress() < 66) {
            metricsQuestions = new JLabel("Metric Questions - Medium - 5, 6, 7");
        } else{
            metricsQuestions = new JLabel("Metric Questions - Hard - 8, 9, 10");
        }

        if (repo.getDiagramsProgress() < 33){
            diagramQuestions = new JLabel("Diagram Questions - Easy - 1, 2, 3, 4");
        } else if (repo.getDiagramsProgress() >= 33 && repo.getConditionalProgress() < 66) {
            diagramQuestions = new JLabel("Diagram Questions - Medium - 5, 6, 7");
        } else{
            diagramQuestions = new JLabel("Diagram Questions - Hard - 8, 9, 10");
        }

        panel.add(loopsQuestions);
        panel.add(conditionalQuestions);
        panel.add(metricsQuestions);
        panel.add(diagramQuestions);

        JOptionPane.showMessageDialog(null, panel, "Recommended Questions", JOptionPane.PLAIN_MESSAGE);
    }
}