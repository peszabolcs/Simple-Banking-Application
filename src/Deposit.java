import java.sql.SQLException;
import java.sql.Statement;

public class Deposit extends Account{
    public Deposit(String name, String password, int id, int balance) {
        super(name, password, id, balance);
    }

    public boolean deposit(int depositAmount) {
        try {
            setBalance(getBalance() + depositAmount);
            Statement stmt = Interface.getConnection().createStatement();
            String sql = "UPDATE users SET balance = " + getBalance() + " WHERE idusers = " + getId();
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
