import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accName;
    private double balance;

    static List<BankAccount> allAccounts = new ArrayList<>(); // list to store all BankAccount objects

    public BankAccount(String accName, double balance) {
        this.accName = accName;
        this.balance = balance;

        allAccounts.add(this);
    }

    public void deposit(double depositAmount) {
        balance += depositAmount;
    }

    public void withdraw(double withdrawAmount) {
        if (withdrawAmount > balance) {
            System.out.println("Cannot withdraw more than balance.");
        } else {
            balance -= withdrawAmount;
        }
    }

    public static boolean accountExists(String accountName) {
        for (BankAccount account : allAccounts) {
            if (account.getAccName().equals(accountName)) {
                return true;
            }
        }
        return false;
    }

    public static String allAccountsToString() {
        String accounts = "";
        for (BankAccount account : allAccounts) {
            accounts += ("Name: " + account.getAccName() + ", Balance: " + account.getBalance());
        }

        return accounts;
    }

    public static String accountToString(String accountName) {
        for (BankAccount account : allAccounts) {
            if ((account.getAccName()).equals(accountName)) {
                return "Name: " + account.getAccName() + ", Balance: " + account.getBalance();
            }
        }

        return "Account Not Found";
    }

    public double getBalance() {
        return (double) Math.round(balance * 100) / 100; // will round balance to two decimals
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }
}
