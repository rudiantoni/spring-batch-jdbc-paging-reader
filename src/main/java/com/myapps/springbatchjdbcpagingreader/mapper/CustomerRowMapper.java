package com.myapps.springbatchjdbcpagingreader.mapper;

import com.myapps.springbatchjdbcpagingreader.domain.Customer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setAge(rs.getString("age"));
        customer.setEmail(rs.getString("email"));
        return customer;
    }
}
