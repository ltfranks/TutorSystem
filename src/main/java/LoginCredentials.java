import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginCredentials extends DBConn {
    String credentialQuery = " SELECT * FROM Users WHERE username = ? and pass = ? ";

    public LoginCredentials() throws SQLException {
    }

    // need to test
    public boolean userLogin(String username, String password) throws NoSuchAlgorithmException, SQLException {
        String sql = " SELECT * FROM Users WHERE username = ? and pass = ? ";
        String hashedPass = hashPasswords(password);
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, username);
        statement.setString(2, hashedPass);

        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return true;
        }
        else {
            System.out.println("Either Username or Password is Incorrect. Please Try Again.");
            return false;
        }

    }

    // need to test
    public void createUser(String username, String password, boolean admin) throws SQLException, NoSuchAlgorithmException {
        String sql = "INSERT INTO Users (username, pass) VALUES (?, ?)";
        String hashedPass = hashPasswords(password);
        PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        int userId = 0;

        statement.setString(1, username);
        statement.setString(2, hashedPass);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }
        }

        setAdminLevel(String.valueOf(userId), admin);
    }


    public void setAdminLevel(String usernameId, boolean admin) throws SQLException {
        String sql_role_name = " INSERT INTO Roles (role_name) VALUES (?) ";
        PreparedStatement role_name = conn.prepareStatement(sql_role_name, Statement.RETURN_GENERATED_KEYS);
        int insertedRoleId = 0;

        if (admin) {
            role_name.setString(1, "Admin");
        } else {
            role_name.setString(1, "User");
        }

        int rowsInserted = role_name.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Role was inserted successfully!");

            ResultSet generatedKeys = role_name.getGeneratedKeys();
            if (generatedKeys.next()) {
                insertedRoleId = generatedKeys.getInt(1);
            }
        }

        connectAdminUser(usernameId, String.valueOf(insertedRoleId));
    }


    public void connectAdminUser(String userId, String roleId) throws SQLException {
        String sql = " INSERT INTO User_Roles (user_id, role_id) VALUES (?, ?) ";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, userId);
        statement.setString(2, roleId);

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("User roles inserted correctly!");
        }
        else {
            System.out.println("Error inserting user roles!");
        }

    }

    // need to test
    public void changePassword(String username, String oldPass, String newPass) throws NoSuchAlgorithmException, SQLException {
        String hashedPass = hashPasswords(oldPass);
        PreparedStatement statement = conn.prepareStatement(credentialQuery);

        statement.setString(1, username);
        statement.setString(2, hashedPass);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String sqlPassChange = " UPDATE Users SET pass = ? WHERE username = ? ";
            String updatedHashPass = hashPasswords(newPass);
            PreparedStatement newStatement = conn.prepareStatement(sqlPassChange);

            newStatement.setString(1, updatedHashPass);
            newStatement.setString(2, username);

            int rowsUpdated = newStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Password changed correctly!");
            } else {
                System.out.println("Password update failed.");
            }
        }
        else {
            System.out.println("Your given credentials are incorrect. Please try again.");
        }
    }

    public void changeUsername(String username, String updatedUsername, String pass) throws NoSuchAlgorithmException, SQLException {
        String hashedPass = hashPasswords(pass);
        PreparedStatement statement = conn.prepareStatement(credentialQuery);

        statement.setString(1, username);
        statement.setString(2, hashedPass);

        ResultSet result = statement.executeQuery();

        if (result.next()) {
            String sqlPassChange = " UPDATE Users SET username = ? WHERE username = ? ";
            PreparedStatement newStatement = conn.prepareStatement(sqlPassChange);

            newStatement.setString(1, updatedUsername);
            newStatement.setString(2, username);

            int rowsUpdated = newStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Username changed correctly!");
            } else {
                System.out.println("Username update failed.");
            }
        }
        else {
            System.out.println("Your given credentials are incorrect. Please try again.");
        }
    }

    private static String hashPasswords(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
