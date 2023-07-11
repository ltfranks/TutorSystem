import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
/**
 * The LoginHandler class implements the ActionListener interface to handle login events.
 * It is responsible for validating user credentials and performing appropriate actions based on the login result.
 */

public class LoginHandler implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame loginWindow;
    private RepositoryInterface repo = Repository.getR();

    /**
     Constructs a LoginHandler object with the specified username field, password field, and login window.
     @param usernameField the JTextField object for entering the username
     @param passwordField the JPasswordField object for entering the password
     @param loginWindow the JFrame object representing the login window
     */
    public LoginHandler(JTextField usernameField, JPasswordField passwordField, JFrame loginWindow) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.loginWindow = loginWindow;
    }

    /**
     Handles the actionPerformed event triggered by the login button.
     Retrieves the entered username and password, validates them, and performs appropriate actions.
     @param e the ActionEvent object representing the login event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        try {
            LoginCredentials credentials = new LoginCredentials();
            boolean loggedIn = credentials.userLogin(username, password);
            System.out.println(loggedIn);
            if (loggedIn) {
                System.out.println("Login successful!");
                repo.setUsername(username);
                loginWindow.setVisible(false);
                new ApiCredentialWindow();

            } else {
                ImageIcon icon = new ImageIcon("src/images/pepeSize.png");
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error",
                        JOptionPane.ERROR_MESSAGE, icon);
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}

