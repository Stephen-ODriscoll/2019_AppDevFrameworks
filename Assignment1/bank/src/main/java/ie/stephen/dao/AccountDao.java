package ie.stephen.dao;

import ie.stephen.model.Account;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public interface AccountDao {

    int newAccount(double overdraftLimit) throws NullPointerException;
    void newCustomerAccount(int customerId, int accountId);
    Account findAccount(int accountId) throws EmptyResultDataAccessException;
    List<Account> findAccounts(int customerId);
    void updateBalance(Account account);
    void deleteCustomerAccount(int accountId);
    int deleteAccount(int accountId);

    double totalDeposited();
    int numAboveLimit(int limit);
}
