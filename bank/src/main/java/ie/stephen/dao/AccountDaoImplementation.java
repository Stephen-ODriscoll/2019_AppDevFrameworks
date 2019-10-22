package ie.stephen.dao;

import ie.stephen.mappers.AccountRowMapper;
import ie.stephen.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountDaoImplementation implements AccountDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int newAccount(final double overdraftLimit) throws NullPointerException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO account(balance, overdraftLimit) VALUES (?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
                                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                                    PreparedStatement ps = connection.prepareStatement(sql, new String[] {"accountId"});
                                    ps.setDouble(1, 0);
                                    ps.setDouble(2, overdraftLimit);
                                    return ps;
                                }
                            },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void newCustomerAccount(int customerId, int accountId) {
        String sql = "INSERT INTO customerAccount(customerId, accountId) VALUES (?, ?)";
        jdbcTemplate.update(sql, customerId, accountId);
    }

    public Account findAccount(int accountId) {
        String sql = "SELECT * FROM account WHERE accountId = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountId);
    }

    public List<Account> findAccounts(int customerId) throws EmptyResultDataAccessException {
        String sql = "SELECT * FROM customerAccount JOIN account ON customerAccount.accountId = account.accountId " +
                "AND customerAccount.customerId = ?";
        return jdbcTemplate.query(sql, new AccountRowMapper(), customerId);
    }

    public void updateBalance(Account account) {
        String sql = "UPDATE account SET balance = ? WHERE accountId = ?";
        jdbcTemplate.update(sql, account.getBalance(), account.getAccountId());
    }

    public void deleteCustomerAccount(int accountId) {
        String sql = "DELETE FROM customerAccount WHERE accountId = ?";
        jdbcTemplate.update(sql, accountId);
    }

    public int deleteAccount(int accountId) {
        String sql = "DELETE FROM account WHERE accountId = ?";
        return jdbcTemplate.update(sql, accountId);
    }

    public double totalDeposited() {
        String sql = "SELECT SUM(balance) FROM account";
        return jdbcTemplate.queryForObject(sql, Double.class);
    }

    public int numAboveLimit(int limit) {
        String sql = "SELECT COUNT(balance) FROM account WHERE balance > ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, limit);
    }
}
