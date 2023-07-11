import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 * The ChatArea class represents a panel for displaying chat messages and inputting new messages.
 * It extends the JPanel class.
 */
public class ChatArea extends JPanel{

    RepositoryInterface repo = Repository.getR();
    /**
     * Constructs a ChatArea object.
     * It sets up the layout, chat text area, message input field, and send button.
     */
    public ChatArea() {
        setLayout(new BorderLayout());
        ControllerGPT cpt = new ControllerGPT();


        JPanel chatPanel = new JPanel();
        chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.PAGE_AXIS));


        repo.setChatTextArea();
        JTextPane chatTextArea = repo.getChatTextArea();
        chatTextArea.setEditable(false);
        chatTextArea.setContentType("text/html");
        chatTextArea.setFont(new Font("Monospaced", Font.BOLD,14));
        chatTextArea.setBackground(Color.decode("#343541"));

        JScrollPane scrollPane = new JScrollPane(chatTextArea);
        scrollPane.add(chatPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(10, 10, 10, 10), 
                BorderFactory.createLineBorder(Color.GRAY)));

        repo.setMessageTextField();
        JTextField messageTextField = repo.getMessageTextField();
        messageTextField.setPreferredSize(new Dimension(350, 30));
        messagePanel.add(messageTextField, BorderLayout.CENTER);

        JButton sendButton = new JButton("Send");
        messagePanel.add(sendButton, BorderLayout.EAST);
        sendButton.addActionListener(cpt);
        
        add(messagePanel, BorderLayout.SOUTH);
        JLabel helpLabel = new JLabel("Need Help? Ask Professor GPT!");
        helpLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(helpLabel, BorderLayout.NORTH);
    }
}
