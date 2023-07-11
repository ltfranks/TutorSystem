import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A JPanel class representing a results window that displays progress bars and provides a button to access recommended questions.
 */
public class ResultsWindow extends JPanel{

    private JProgressBar loopsProgressBar, conditionalStatementsProgressBar, metricsProgressBar, diagramsProgressBar;

    /**
     * Constructs a ResultsWindow object.
     * Initializes the layout and components of the panel, including progress bars and a button for recommended questions.
     */
    public ResultsWindow(){
        Repository repo = Repository.getR();

        setLayout(new GridLayout(6, 1));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel headerLabel = new JLabel("Progress towards goal:");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        add(headerPanel);

        loopsProgressBar = new JProgressBar(0, 100);
        conditionalStatementsProgressBar = new JProgressBar(0, 100);
        metricsProgressBar = new JProgressBar(0, 100);
        diagramsProgressBar = new JProgressBar(0, 100);

        loopsProgressBar.setValue(repo.getLoopsProgress());
        conditionalStatementsProgressBar.setValue(repo.getConditionalProgress());
        metricsProgressBar.setValue(repo.getMetricsProgress());
        diagramsProgressBar.setValue(repo.getMetricsProgress());

        add(loopsProgressBar);
        add(conditionalStatementsProgressBar);
        add(metricsProgressBar);
        add(diagramsProgressBar);

        loopsProgressBar.setString("Loops: " + repo.getLoopsProgress() + "%");
        conditionalStatementsProgressBar.setString("Conditional Statements: " + repo.getConditionalProgress() + "%");
        metricsProgressBar.setString("Metrics: " + repo.getMetricsProgress() + "%");
        diagramsProgressBar.setString("Diagrams: " + repo.getDiagramsProgress() + "%");

        loopsProgressBar.setStringPainted(true);
        conditionalStatementsProgressBar.setStringPainted(true);
        metricsProgressBar.setStringPainted(true);
        diagramsProgressBar.setStringPainted(true);

        JButton recQuestionsButton = new JButton("Recommended Questions");
        JPanel recQuestionsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        recQuestionsPanel.setBorder(BorderFactory.createEmptyBorder());
        recQuestionsPanel.add(recQuestionsButton);
        add(recQuestionsPanel);



        recQuestionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RecommendedQuestions recommendedQuestions = new RecommendedQuestions();
                recommendedQuestions.displayPopUpWindow();
            }
        });
    }


}
