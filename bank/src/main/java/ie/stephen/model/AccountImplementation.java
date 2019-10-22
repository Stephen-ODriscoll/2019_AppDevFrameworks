package ie.stephen.model;

public class AccountImplementation implements Account {

    private int accountId;
    private double balance;
    private double overdraftLimit;

    public AccountImplementation() { }

    public AccountImplementation(int accountId, double balance, double overdraftLimit) {
        this.accountId = accountId;
        this.balance = balance;
        this.overdraftLimit = overdraftLimit;
    }

    public int getAccountId() { return accountId; }
    public double getBalance() { return balance; }
    public double getOverdraftLimit() { return overdraftLimit; }

    public void setAccountId(int accountId) { this.accountId = accountId; }
    public void setBalance(double balance) { this.balance = balance; }
    public void setOverdraftLimit(double overdraftLimit) { this.overdraftLimit = overdraftLimit; }

    public String toString() {
        return "Account id: " + accountId + ", balance: €" + balance + ", overdraft limit: €" + overdraftLimit;
    }
}

