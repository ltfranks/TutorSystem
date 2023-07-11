import javax.swing.*;
import java.awt.*;
import java.util.*;

public class QAPanel extends JPanel implements Observer {
//    private final JTextArea questionTextArea;
    RepositoryInterface repo = Repository.getR();
    JTextField textField1,textField2,textField3,textField4,textField5,textField6;

    /**
     * Constructs a QAPanel object.
     * Initializes the layout and components of the panel.
     */
    public QAPanel() {//flowchart to code
        setLayout(new BorderLayout());

        //left panel
        JPanel leftpanel = new JPanel();
        leftpanel.setLayout(null);

        //large text area
        JTextArea questionBox = new JTextArea(5, 20);
        questionBox.setEditable(false);
        questionBox.setBounds(120, 70, 495, 50);
        questionBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftpanel.add(questionBox);

        //first set of buttons
        JButton previous = new JButton("Previous");
        JButton next = new JButton("Next");
        previous.setBounds(270, 130, 90, 25);
        next.setBounds(370, 130, 90, 25);
        leftpanel.add(previous);
        leftpanel.add(next);

        //second set of buttons
        JButton submit = new JButton("Submit");
        JButton hint = new JButton("Hint");
        submit.setBounds(270, 605, 90, 25);
        hint.setBounds(370, 605, 90, 25);
        leftpanel.add(submit);
        leftpanel.add(hint);

        textField1 = new JTextField();
        textField1.setBounds(120, 200, 500, 25);
        textField1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftpanel.add(textField1);

        textField2 = new JTextField();
        textField2.setBounds(120, 230, 500, 25);
        textField2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftpanel.add(textField2);

        textField3 = new JTextField();
        textField3.setBounds(120, 260, 500, 25);
        textField3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftpanel.add(textField3);

        textField4 = new JTextField();
        textField4.setBounds(120, 290, 500, 25);
        textField4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftpanel.add(textField4);

        textField5 = new JTextField();
        textField5.setBounds(120, 320, 500, 25);
        textField5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftpanel.add(textField5);

        textField6 = new JTextField();
        textField6.setBounds(120, 350, 500, 25);
        textField6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        leftpanel.add(textField6);

        // mascot
        ImageIcon imageIcon = new ImageIcon("src/images/puff.png"); // Replace with the actual path to your image
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(250, 400, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        leftpanel.add(imageLabel);

        // Add the panel to the content pane of the JFrame
        add(leftpanel);

        AnswerController ac = new AnswerController();
        ac.addObserver(this);

        submit.addActionListener(ac);
        hint.addActionListener(ac);
        previous.addActionListener(ac);
        next.addActionListener(ac);
        textField1.addActionListener(ac);
        textField2.addActionListener(ac);
        textField3.addActionListener(ac);
        textField4.addActionListener(ac);
        textField5.addActionListener(ac);
        textField6.addActionListener(ac);

        questionBox.setText("Fill in the boxes below to write the code that matches the flowchart diagram\n" +
                "Loops represented as For loops");
    }

/**
 * The update method is called when the observed object notifies its observers of a change.
 * This method is responsible for handling the updates and performing the necessary actions.
 * @param o The Observable object that triggered the update.
 * @param arg The argument passed by the Observable object.
 */
    @Override
    public void update(Observable o, Object arg) {
        WriteJson write = new WriteJson();
        if (arg.equals("Submit"))
        {
            ArrayList<String> ans = new ArrayList<>(Arrays.asList(
                    textField1.getText(), textField2.getText(),textField3.getText(),
                    textField4.getText(),textField5.getText(),textField6.getText()));
            write.updateJsonUserAnswers("username","q"+repo.getQuestion(),ans);
        }
        else if (arg.equals("next"))
        {
            repo.incQuestion();
        }
        else if (arg.equals("previous"))
        {
            repo.decQuestion();
        }
        else if (arg instanceof String) {
            String question = (String) arg;
//            questionTextArea.setText(question);
        }
    }
}