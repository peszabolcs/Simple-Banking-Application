import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Interface {
    private static int choice;
    private static Connection con;
    public static void main(String[] args) throws SQLException {
        CreateConnection();
        System.out.println("Welcome to our banking application! What can I help you with today?");
        while (choice != 3) {
            displayMenu();
        }

    }

    public static void displayMenu() throws SQLException {
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
                //CreateAccount account = new CreateAccount(name, password);
                System.out.println("Confirm password:");
                String confirmPassword = scanner.nextLine();
                if (CreateAccount.createAccount(name, password, confirmPassword)) {
                    System.out.println("Account created successfully!");
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
                if (login.login(loginName, loginPassword)) {
                    System.out.println("Login successful!");
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
