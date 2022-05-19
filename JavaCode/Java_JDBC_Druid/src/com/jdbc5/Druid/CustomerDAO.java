package com.jdbc5.Druid;

import com.jdbc5.Druid.bean.Customer;

import java.sql.Connection;
import java.util.List;

public interface CustomerDAO {
    int insert(Connection conn, Customer cust);

    int deleteById(Connection conn, int id);

    int update(Connection conn, Customer cust);

    Customer getCustomerById(Connection conn, int id);

    List<Customer> getAll(Connection conn);

    Long getCount(Connection conn);
}
