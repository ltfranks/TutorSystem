import java.awt.*;

import javax.swing.*;

/**
 * The Main class extends a built-in library, JFrame, to create a main window
 * for the Final Project program. The program is a simple flow-chart diagram.
 *
 * @author Anthony Colin, Shiv Panchal, Luke Fanguna, Nathan Choi, Reza Mousakhani, Luke Franks.
 */
public class Main extends JFrame {
    /**
     * Constructor for the Main class. Sets the title of the window and initializes the
     * Repository, Menu, StatusArea, and WorkingArea classes. The StatusArea and WorkingArea
     * classes are added as observers to the Repository. The Menu, StatusArea, and WorkingArea
     * classes are added to the main window in BorderLayout.
     */
    private String[] typeOfQuestion = {"Code to Flowchart", "Flowchart to Code"};
    RepositoryInterface repo = Repository.getR();
    public Main() {
        super("Final Project");

        //menu bar
        Menu menu = new Menu();
        setLayout(new BorderLayout());
        menu.setBorder(BorderFactory.createEtchedBorder());

        //status bar
        StatusArea status = new StatusArea();
        status.setBackground(Color.WHITE);
        status.setForeground(Color.BLACK);
        status.setBorder(BorderFactory.createEtchedBorder());

        //flowchart usage
        WorkingArea work = new WorkingArea();

        //left panel
        repo.setCardLayout(new CardLayout());
        JPanel mainPanel = new JPanel(repo.getCardLayout());
        repo.setMainPanel(mainPanel);

        QAPanel2 qaPanel2 = new QAPanel2();
        repo.getMainPanel().add(qaPanel2, "Code 2 Flowchart");

        QAPanel qaPanel1 = new QAPanel();
        repo.getMainPanel().add(qaPanel1, "Flowchart 2 Code");



        //xp bar
        JProgressBar xpBar = new JProgressBar();
        xpBar.setString("Overall Progress: " +repo.getOverallProgress()+"%");
        xpBar.setValue(repo.getOverallProgress());
        xpBar.setStringPainted(true);
        xpBar.setBackground(Color.WHITE);
        xpBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(xpBar, BorderLayout.NORTH);


        //first drop down menu
        JComboBox<String> codeToFlowchart = new JComboBox<>(typeOfQuestion);//Code or Flowchart
        repo.setCodeOrFlow(codeToFlowchart);

        //second drop down menu
        String[] codeQuestions = {
                "Code Question 1", "Code Question 2", "Code Question 3",
                "Code Question 4", "Code Question 5"};
        JComboBox<String> questions = new JComboBox<>(codeQuestions);//Select Difficulty
        repo.setQuestionBox(questions);




        //formats the menu bar and progress bar
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.add(menu);
        northPanel.add(codeToFlowchart);
        northPanel.add(questions);
        northPanel.add(Box.createVerticalStrut(10)); // Add some spacing between components
        northPanel.add(xpBar);

        //main panel
        JPanel contentPanel = new JPanel(new GridLayout(1,2));
        contentPanel.add(repo.getMainPanel(), BorderLayout.WEST);
        contentPanel.add(work, BorderLayout.CENTER);
        contentPanel.add(status, BorderLayout.SOUTH);
        add(northPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        AnswerController ac = new AnswerController();
        codeToFlowchart.addActionListener(ac);
    }

    /**
     * The main method is the for the Final Project application.
     * It sets the size of the window, sets the default close operation, and
     * makes the window visible.
     */
    public static void main(String[] args) throws Exception {
        LoginWindow lw = new LoginWindow();
        lw.setVisible(true);
    }
}