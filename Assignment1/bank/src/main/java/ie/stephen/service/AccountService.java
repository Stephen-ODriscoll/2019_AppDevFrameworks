package ie.stephen.service;

public interface AccountService {

    void createAccount(int customerId, double overdraftLimit);
    void addPersonToAccount(int accountId, int customerId);
    void viewAccounts(int customerId);
    void withdraw(int accountId, double amount);
    void deposit(int accountId, double amount);
    void transfer(int account1Id, int account2Id, double amount);
    void closeAccount(int accountId);

    void totalDeposited();
    void numAboveLimit(int limit);
}
