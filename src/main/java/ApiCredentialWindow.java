import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The ApiCredentialWindow class represents a window for entering API credentials.
 * It extends the JFrame class.
 */
public class ApiCredentialWindow extends JFrame{
    
    private JTextField gptField;
    private JTextField mapsField;

    private JTextField weatherField;
    private JTextField youtubeField;

    /**
     * Constructs an ApiCredentialWindow object.
     * It sets up the window and initializes the text fields.
     */
    public ApiCredentialWindow() {
        super("API Credentials");
        setVisible(true);
        setSize(400, 250);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel ap = new JPanel();
        ap.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel topLabel = new JLabel("API Keys");
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topPanel.add(topLabel, BorderLayout.CENTER);

        ap.add(topPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10); // Add some spacing

        JLabel label1 = new JLabel("ChatGPT:");
        JLabel label2 = new JLabel("Bing Maps:");
        JLabel label3 = new JLabel("Weather: ");
        JLabel label4 = new JLabel("YouTube: ");

        gptField = new JTextField();
        mapsField = new JTextField();
        weatherField = new JTextField();
        youtubeField = new JTextField();

        gptField.setPreferredSize(new Dimension(150, 20));
        mapsField.setPreferredSize(new Dimension(150, 20));
        weatherField.setPreferredSize(new Dimension(150,20));
        youtubeField.setPreferredSize(new Dimension(150,20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(label1, gbc);

        gbc.gridx = 1;
        contentPanel.add(gptField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(label2, gbc);

        gbc.gridx = 1;
        contentPanel.add(mapsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(label3, gbc);

        gbc.gridx = 1;
        contentPanel.add(weatherField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPanel.add(label4, gbc);

        gbc.gridx = 1;
        contentPanel.add(youtubeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JButton submitButton = new JButton("Submit");
        contentPanel.add(submitButton, gbc);

        submitButton.addActionListener(new ApiCredentialHandler(gptField, mapsField, weatherField, youtubeField, this));

        ap.add(contentPanel, BorderLayout.CENTER);
        add(ap);
    }
}
