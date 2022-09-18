package JavaWeb.customer.service.Impl;

import JavaWeb.customer.dao.CustomerDAO;
import JavaWeb.customer.service.CustomerService;
import JavaWeb.customer.vo.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDAO customerDAO = null;

    @Override
    public int insert(Connection conn, Customer cust) {
        return customerDAO.insert(conn, cust);
    }

    @Override
    public int deleteById(Connection conn, int id) {
        return customerDAO.deleteById(conn, id);
    }

    @Override
    public int update(Connection conn, Customer cust) {
        return customerDAO.update(conn,cust);
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        return customerDAO.getCustomerById(conn, id);
    }

    @Override
    public List<Customer> getFive(Connection conn, Integer startIndex) {
        return customerDAO.getFive(conn, startIndex);
    }
    @Override
    public List<Customer> getFive(Connection conn, String keyword, Integer startIndex) {
        return customerDAO.getFive(conn, keyword, (startIndex - 1) * 5);
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        return customerDAO.getAll(conn);
    }

    @Override
    public Long getCount(Connection conn) {
        return customerDAO.getCount(conn);
    }

    @Override
    public Long getCount(Connection conn, String keyword) {
        return customerDAO.getCount(conn, keyword);
    }

    @Override
    public Integer getPageCount(Connection conn, String keyword) {
        return (int) Math.ceil(customerDAO.getCount(conn, keyword).doubleValue() / 5);
    }
}
