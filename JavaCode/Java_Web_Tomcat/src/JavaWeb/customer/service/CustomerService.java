package JavaWeb.customer.service;

import JavaWeb.customer.vo.Customer;
import java.sql.Connection;
import java.util.List;

public interface CustomerService {

    int insert(Connection conn, Customer cust);

    int deleteById(Connection conn, int id);

    int update(Connection conn, Customer cust);

    Customer getCustomerById(Connection conn, int id);

    List<Customer> getFive(Connection conn, Integer startIndex);

    List<Customer> getFive(Connection conn, String keyword, Integer startIndex);

    List<Customer> getAll(Connection conn);

    Long getCount(Connection conn);

    Long getCount(Connection conn, String keyword);

    Integer getPageCount(Connection conn, String keyword);
}
