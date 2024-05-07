import java.sql.SQLException;
import java.sql.Statement;

public class Deposit extends Account{
    Interface interface2;
    Statement stmt;
    public Deposit(String name, String password, int id, int balance, Interface interface2) throws SQLException {
        super(name, password, id, balance);
        this.interface2 = interface2;
        stmt = interface2.getConnection().createStatement();
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
            System.out.println("Deposit failed");
            return false;
        }

    }
}
