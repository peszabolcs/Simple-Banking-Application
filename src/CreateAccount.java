public class CreateAccount {
    private String name;
    private String password;
    private int id;

    public CreateAccount(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public boolean createAccount(String name, String password, String confirmPassword) {
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
        this.name = name;
        this.password = password;
        return true;
    }
}
