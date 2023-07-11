import javax.swing.*;
import java.awt.*;
import java.util.*;


public class QAPanel2 extends JPanel implements Observer {
    //    private final JTextArea questionTextArea;
    RepositoryInterface repo = Repository.getR();
    JTextArea questionBox;
    Map<?, ?> questionsMap;

    /**
     * Constructs a QAPanel2 object.
     * Initializes the layout and components of the panel.
     */
    public QAPanel2() {//code to flowchart
        setLayout(new BorderLayout());

        //left panel
        JPanel leftpanel = new JPanel();

        //large text area
        questionBox = new JTextArea(5, 20);
        questionBox.setEditable(false);
        questionBox.setBounds(120, 250, 500, 200);
        questionBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //first set of buttons
        JButton previous = new JButton("Previous");
        JButton next = new JButton("Next");
        previous.setBounds(270, 480, 90, 25);
        next.setBounds(380, 480, 90, 25);

        //second set of buttons
        JButton submit = new JButton("Submit");
        JButton hint = new JButton("Hint");
        submit.setBounds(270, 515, 90, 25);
        hint.setBounds(380, 515, 90, 25);
        leftpanel.setLayout(null);

        //mascot
        ImageIcon toppuff1 = new ImageIcon("src/images/puff1.png");
        JLabel toppuff = new JLabel(toppuff1);
        toppuff.setBounds(250, 0, toppuff1.getIconWidth(), toppuff1.getIconHeight());
        leftpanel.add(toppuff);

        ImageIcon bottomleft = new ImageIcon("src/images/puff2.png");
        JLabel bottomleftpuff = new JLabel(   bottomleft);
        bottomleftpuff.setBounds(20, 550,    bottomleft.getIconWidth(),    bottomleft.getIconHeight());
        leftpanel.add(bottomleftpuff);

        ImageIcon bottomright = new ImageIcon("src/images/puff3.png");
        JLabel bottomrightpuff = new JLabel(   bottomright);
        bottomrightpuff.setBounds(500, 550,    bottomright.getIconWidth(),    bottomright.getIconHeight());
        leftpanel.add(bottomrightpuff);

        leftpanel.add(questionBox);
        leftpanel.add(previous);
        leftpanel.add(next);
        leftpanel.add(submit);
        leftpanel.add(hint);

        // Add the panel to the content pane of the JFrame
        add(leftpanel);

        AnswerController ac = new AnswerController();
        ac.addObserver(this);

        submit.addActionListener(ac);
        hint.addActionListener(ac);
        previous.addActionListener(ac);
        next.addActionListener(ac);


        ReadJson readJson = new ReadJson();
        questionsMap = readJson.getJsonCodeQuestions();
        if (questionsMap != null && !questionsMap.isEmpty())
        {
                    String prompt = "Use the code below to create a flowchart diagram:\n\n";
            String firstQuestion = questionsMap.values().iterator().next().toString();
            questionBox.setText(prompt + firstQuestion);
        }
    }

    /**
     * The update method is called when the observed object notifies its observers of a change.
     * This method is responsible for handling the updates and performing the necessary actions.
     *
     * @param o   The Observable object that triggered the update.
     * @param arg The argument passed by the Observable object.
     */
    @Override
    public void update(Observable o, Object arg) {
        String prompt = "Use the code below to create a flowchart diagram:\n\n";
        if (arg.equals("Submit"))
        {

        }
        else if (arg.equals("next"))
        {
            repo.incQuestion();
            if (repo.getQuestion() < 6)
                questionBox.setText((String)prompt + questionsMap.get("q"+repo.getQuestion()));
            else
                questionBox.setText((String)prompt + questionsMap.get("bonus"));
        }
        else if (arg.equals("previous"))
        {
            repo.decQuestion();
            questionBox.setText((String)prompt + questionsMap.get("q"+repo.getQuestion()));
        }
        else if (arg instanceof String) {
            String question = (String) arg;
            questionBox.setText(question);
        }
    }
}