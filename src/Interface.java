import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Interface {
    static private int choice;
    private static Connection con;
    static boolean isLoggedIn = false;
    private Account account = null;
    static Interface loginInterface = new Interface();
    public static void main(String[] args) throws SQLException {

        CreateConnection();
        System.out.println("Welcome to our banking application! What can I help you with today?");
        while (!isLoggedIn) {
            loginInterface = new Interface();
            loginInterface.loginScreen();
        }

        while (isLoggedIn) {
            loginInterface.loggedInMenu();
        }


    }

    public void loginScreen() throws SQLException {
        System.out.println("1. Create an account");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Creating an account...");
                System.out.println("===================================");
                System.out.println("Enter your name:");
                String name = scanner.nextLine();
                System.out.println("Enter your password:");
                String password = scanner.nextLine();
                System.out.println("Confirm password:");
                String confirmPassword = scanner.nextLine();
                this.account = CreateAccount.createAccount(name, password, confirmPassword);
                if (this.account != null) {
                    System.out.println("Account created successfully!");
                    isLoggedIn = true;
                } else {
                    System.out.println("Account creation failed");
                }
                break;
            case 2:
                System.out.println("Logging in...");
                System.out.println("Enter your name:");
                String loginName = scanner.nextLine();
                System.out.println("Enter your password:");
                String loginPassword = scanner.nextLine();
                LogIn login = new LogIn();
                this.account = login.login(loginName, loginPassword);
                if (login.login(loginName, loginPassword) != null) {
                    System.out.println("Login successful!");
                    isLoggedIn = true;
                } else {
                    System.out.println("Login failed");
                }
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice");
        }
    }

    public void loggedInMenu() throws SQLException {
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Check balance");
        System.out.println("4. Log out");
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                System.out.println("Enter the amount you would like to deposit:");
                int depositAmount = scanner.nextInt();
                scanner.nextLine();
                Deposit deposit = new Deposit(account.getName(), account.getPassword(), account.getId(),
                        account.getBalance());
                if (deposit.deposit(depositAmount)) {
                    System.out.println("Deposited " + depositAmount + " into account " + deposit.getName());
                } else {
                    System.out.println("Deposit failed");
                }
                break;
            case 2:
                System.out.println("Enter the amount you would like to withdraw:");
                int withdrawAmount = scanner.nextInt();
                scanner.nextLine();
                break;
            case 3:
                System.out.println("Checking balance...");
                break;
            case 4:
                System.out.println("Logging out...");
                isLoggedIn = false;
                loginInterface.loginScreen();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }


    //SQL database connection
    public static void CreateConnection() {
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsystem", dbUser,
                    dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
