import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
/**
 * The ControllerGPT class handles actions for the chat input and output.
 * It implements the ActionListener interface.
 */

public class ControllerGPT implements ActionListener {
    private final ChatGPT gpt = new ChatGPT();
    private final Repository repo = Repository.getR();

    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField messageTextField = repo.getMessageTextField();
        JTextPane chatTextArea = repo.getChatTextArea();

        if (e.getActionCommand().equals("Send")){
            String input = messageTextField.getText();
            System.out.println(input);

            String output = "Sorry, something went wrong!";
            try {
                output = gpt.getChatGPTResponse(input);
                System.out.println(output);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            StyledDocument doc = chatTextArea.getStyledDocument();
            Style styleGreen = chatTextArea.addStyle("LightGreen", null);
            StyleConstants.setForeground(styleGreen, Color.decode("#76D7C4"));
            Style styleLightGreen = chatTextArea.addStyle("Green", null);
            StyleConstants.setForeground(styleLightGreen, Color.decode("#D1F2EB"));


            try {
                doc.insertString(doc.getLength(), "You: " + input + "\n", styleGreen);
                doc.insertString(doc.getLength(), "ChatGPT: " + output + "\n", styleLightGreen);


            } catch (BadLocationException ex) {
                ex.printStackTrace();
            }
            messageTextField.setText("");
        }
    }

}
