import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn extends Interface {
    private String name;
    private String password;
    Statement stmt = (Statement) getConnection().createStatement();

    public LogIn(String name, String password) throws SQLException {
        this.name = name;
        this.password = password;
        login();
    }

    public boolean login() throws SQLException {
        ResultSet rs =
                stmt.executeQuery("SELECT * FROM users WHERE username = '" + this.name + "' AND password = '" + this.password + "'");
        if (rs.next()) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Login failed");
            return false;
        }
    }
}
