package ie.stephen.model;

public interface Account {

    int getAccountId();
    double getBalance();
    double getOverdraftLimit();

    void setAccountId(int accountId);
    void setBalance(double balance);
    void setOverdraftLimit(double overdraftLimit);

    String toString();
}
