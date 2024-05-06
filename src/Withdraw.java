import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

public class Withdraw extends Account{
    public Withdraw(String name, String password, int id, int balance) {
        super(name, password, id, balance);
    }

        public boolean withdraw(int withdrawAmount) {
            try {
                setBalance(getBalance() - withdrawAmount);
                Statement stmt = (Statement) Interface.getConnection().createStatement();
                String sql = "UPDATE users SET balance = " + getBalance() + " WHERE idusers = " + getId();
                return true;
            } catch (SQLException | InputMismatchException e) {
                return false;
            }

        }
}
