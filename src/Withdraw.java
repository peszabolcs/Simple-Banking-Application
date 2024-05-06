import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;

public class Withdraw extends Account{
    public Withdraw(String name, String password, int id, int balance) {
        super(name, password, id, balance);
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
                Statement stmt = Interface.getConnection().createStatement();
                String sql = "UPDATE users SET balance = " + getBalance() + " WHERE idusers = " + getId();
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("Withdrew " + withdrawAmount + " from account " + getName());
            }
            catch (InputMismatchException e) {
                System.out.println("Cannot withdraw negative amount");
            }
            catch (IllegalArgumentException e) {
                System.out.println("Insufficient funds");
            }

        }
}
