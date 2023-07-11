import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConn class establishes the connection between this project and the MySql database. Through this class, other
 * classes have the ability to connect to the database. The credentials used to connect to the database are securely
 * stored in a local environment.

 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */

public class DBConn {
    String DB = System.getenv("DB");
    String USER = System.getenv("USERNAME");
    String HOST = System.getenv("HOST");
    String PASS = System.getenv("PASSWORD");

    Connection conn = DriverManager.getConnection(HOST, USER, PASS);

    public DBConn() throws SQLException {
    }
}
