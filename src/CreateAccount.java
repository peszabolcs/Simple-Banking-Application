import javax.swing.plaf.nimbus.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccount extends Interface {

    public static boolean createAccount(String name, String password, String confirmPassword) throws SQLException {
        if (name.length() < 8) {
            System.out.println("Name must be at least 8 characters long");
            return false;
        }
        if (password.length() < 8 || confirmPassword.length() < 8) {
            System.out.println("Password must be at least 8 characters long");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match");
            return false;
        }

        Statement stmt = (Statement) getConnection().createStatement();
        int setID;

        ResultSet rs = stmt.executeQuery("SELECT MAX(idusers) FROM users");
        if (rs.next()) {
            setID = rs.getInt(1) + 1;
        } else {
            setID = 1; // default value if no users exist
        }
        String sql = "INSERT INTO users (username, password, balance, idusers) VALUES ('" + name + "', '" + password + "', 0, '" + setID + "')";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        Account account = new Account(name, password, setID, 0);
        return true;
    }
}
