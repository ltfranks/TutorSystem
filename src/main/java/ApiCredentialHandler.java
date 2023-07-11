import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * The ApiCredentialHandler class handles the event of submitting API credentials.
 * It implements the ActionListener interface.
 */
public class ApiCredentialHandler implements ActionListener {

    private JTextField gptField, mapsField, weatherField, youtubeField;
    private Repository repo = Repository.getR();
    private JFrame frame;
    /**
     * Constructs an ApiCredentialHandler object.
     * @param gptField The JTextField for GPT API key input.
     * @param mapsField The JTextField for Google Maps API key input.
     * @param weatherField The JTextField for Weather API key input.
     * @param youtubeField The JTextField for YouTube API key input.
     * @param frame The JFrame object.
     */
    ApiCredentialHandler(JTextField gptField, JTextField mapsField, JTextField weatherField, JTextField youtubeField, JFrame frame){
        this.gptField = gptField;
        this.mapsField = mapsField;
        this.weatherField = weatherField;
        this.youtubeField = youtubeField;
        this.frame = frame;
    }
    /**
     * Handles the action event when the submit button is clicked.
     * It sets the API keys, creates the environment file, disposes of the current frame,
     * and opens the main application window with a welcome message.
     * @param e The action event to be handled.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        WriteApiKeys wr = new WriteApiKeys();
        if (e.getActionCommand().equals("Submit")){
            repo.setGptkey(gptField.getText());
            repo.setMapsKey(mapsField.getText());
            repo.setWeatherKey(weatherField.getText());
            repo.setYoutubeKey(youtubeField.getText());
            try {
                wr.createEnvFile();
                frame.dispose();
                Main main = new Main();
                main.setVisible(true);
                main.setSize(1500, 1500);
                main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                String label = "Hello Student!, Welcome to the Intelligent Tutor System!\n\n" +
                        "In this application, you'll be able to learn how to code.\n\n" +
                        "You'll be able to turn code into a flowchart diagram and vice-versa!\n\n" +
                        "Here are some features provided to you to help your learning experience.\n\n" +
                        "- ChatGPT\n" +
                        "- Youtube\n" +
                        "- Hints\n" +
                        "- Maps to find nearest Starbucks\n\n" +
                        "Good luck and have fun learning!\n\n" +
                        "Choose a question type above to begin";
                JOptionPane.showMessageDialog(null, label, "Message", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
