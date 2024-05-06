import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interface {
    static private int choice;
    private static Connection con;
    public static Interface instance;
    static boolean isLoggedIn = false;
    Account account = null;

    public static synchronized Interface getInstance() {
        if (instance == null) {
            instance = new Interface();
            instance.createConnection();
        }
        return instance;
    }

    Interface() {
        createConnection();
    }
    public static void main(String[] args) throws SQLException {
        Interface interfaceInstance = Interface.getInstance();
        System.out.println("Welcome to our banking application! What can I help you with today?");
        while (!isLoggedIn) {
            interfaceInstance.loginScreen();
        }

        while (isLoggedIn) {
            interfaceInstance.loggedInMenu();
        }


    }

    public void loginScreen() throws SQLException {
        System.out.println("1. Create an account");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        choice = scanner.nextInt();
        scanner.nextLine();
        try {
            if (choice < 1 || choice > 3) {
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
            return;
        }
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
                CreateAccount createAccount = new CreateAccount();
                this.account = createAccount.createAccount(name, password, confirmPassword);
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
                //TODO valamiért a login után nem állítódik be az account egyenlege
                LogIn login = new LogIn();
                account = login.login(loginName, loginPassword);
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
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
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
                Scanner withdrawal = new Scanner(System.in);
                int withdrawAmount;
                try {
                    withdrawAmount = withdrawal.nextInt();
                    if (withdrawAmount < 0) {
                        throw new InputMismatchException();
                    }
                    Withdraw withdraw = new Withdraw(account.getName(), account.getPassword(), account.getId(),
                            account.getBalance());
                    withdraw.withdraw(withdrawAmount);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 0 and " + Integer.MAX_VALUE + ".");
                    break;
                }
                break;
            case 3:
                System.out.println("Checking balance...");
                System.out.println("Balance: " + account.getBalance());
                break;
            case 4:
                System.out.println("Logging out...");
                isLoggedIn = false;
                Interface.getInstance().loginScreen();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }


    //SQL database connection
    public void createConnection() {
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

    public Connection getConnection() {
        return con;
    }
}
