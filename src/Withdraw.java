import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

public class Withdraw extends Account {
    Interface interface1;
    Statement stmt;
    public Withdraw(String name, String password, int id, int balance, Interface interface1) throws SQLException {
        super(name, password, id, balance);
        this.interface1 = interface1;
        stmt = interface1.getConnection().createStatement();
    }

        public void withdraw(int withdrawAmount) {
            try {
                System.out.println("Balance: " + getBalance());
                System.out.println("Withdraw amount: " + withdrawAmount);

                if (withdrawAmount > getBalance() || withdrawAmount < 0) {
                    if (withdrawAmount < 0) {
                        throw new InputMismatchException();
                    }
                    if (withdrawAmount > getBalance()) {
                        throw new IllegalArgumentException();
                    }
                }
                setBalance(getBalance() - withdrawAmount);

                String sql = "UPDATE users SET balance = " + getBalance() + " WHERE idusers = " + getId();
                stmt.executeUpdate(sql);
                interface1.account.setBalance(getBalance());
            } catch (SQLException e) {
                System.out.println("Withdrew failed");
            }
            catch (InputMismatchException e) {
                System.out.println("Cannot withdraw negative amount");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Insufficient funds");
            }

        }
}
