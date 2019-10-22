package ie.stephen.dao;

import ie.stephen.mappers.CustomerRowMapper;
import ie.stephen.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CustomerDaoImplementation implements CustomerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int newCustomer(final String firstName, final String lastName) throws NullPointerException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        final String sql = "INSERT INTO customer(firstName, lastName) VALUES (?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {
                                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                                    PreparedStatement ps = connection.prepareStatement(sql, new String[] {"customerId"});
                                    ps.setString(1, firstName);
                                    ps.setString(2, lastName);
                                    return ps;
                                }
                            },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Customer findById(int customerId) {
        String sql = "SELECT * FROM customer WHERE customerId = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), customerId);
    }
}
