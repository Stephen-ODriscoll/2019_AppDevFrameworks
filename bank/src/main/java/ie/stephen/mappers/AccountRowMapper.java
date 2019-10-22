package ie.stephen.mappers;

import ie.stephen.model.Account;
import ie.stephen.model.AccountImplementation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AccountImplementation(rs.getInt("accountId"),
                rs.getDouble("balance"), rs.getDouble("overdraftLimit"));
    }
}
