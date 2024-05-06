public class Account {
    private String name;
    private String password;
    private final int id;
    private int balance;

    public Account(String name, String password, int id, int balance) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
