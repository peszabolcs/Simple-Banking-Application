import java.sql.SQLException;
import java.sql.Statement;

public class Deposit extends Account{
    Interface interface2 = new Interface();
    Statement stmt = interface2.getConnection().createStatement();
    public Deposit(String name, String password, int id, int balance) throws SQLException {
        super(name, password, id, balance);
    }

    public boolean deposit(int depositAmount) {
        try {
            setBalance(getBalance() + depositAmount);
            String sql = "UPDATE users SET balance = " + getBalance() + " WHERE idusers = " + getId();
            stmt.executeUpdate(sql);
            interface2.account.setBalance(getBalance());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
