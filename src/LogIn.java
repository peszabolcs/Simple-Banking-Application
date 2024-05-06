import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn extends Interface {
    Statement stmt = (Statement) getConnection().createStatement();

    public LogIn() throws SQLException {
    }


    public boolean login(String username, String password) throws SQLException {
        ResultSet rs =
                stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'");
        return rs.next();
    }
}
