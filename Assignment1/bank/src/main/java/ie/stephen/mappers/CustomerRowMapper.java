package ie.stephen.mappers;

import ie.stephen.model.Customer;
import ie.stephen.model.CustomerImplementation;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CustomerImplementation(rs.getInt("customerId"),
                rs.getString("firstName"), rs.getString("lastName"));
    }
}
