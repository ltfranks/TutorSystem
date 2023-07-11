import javax.swing.*;
import java.awt.*;

/**
 * The LoginWindow class represents a login window GUI.
 * It provides a user interface for entering login credentials and performing login actions.
 */
public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    /**
     * Constructs a LoginWindow object.
     * Sets up the window title, size, and layout, and initializes the components.
     * Also sets up event listeners for the sign-in and create account buttons.
     */
    public LoginWindow() {
        setTitle("Login Window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon("src/images/professor.png");
        Image image = imageIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton signInButton = new JButton("Sign In");
        JButton createAccountButton = new JButton("Create Account");

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(imageLabel, BorderLayout.NORTH);

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new GridLayout(2, 2, 5, 5));

        fieldsPanel.add(usernameLabel);
        fieldsPanel.add(usernameField);
        fieldsPanel.add(passwordLabel);
        fieldsPanel.add(passwordField);

        panel.add(fieldsPanel, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(signInButton);
        buttonsPanel.add(createAccountButton);

        panel.add(buttonsPanel, BorderLayout.SOUTH);

        add(panel);

        LoginHandler loginHandler = new LoginHandler(usernameField, passwordField, this);
        signInButton.addActionListener(loginHandler);
        createAccountButton.addActionListener(loginHandler);
    }
}