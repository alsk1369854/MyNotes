package JavaWeb.dao;

import JavaWeb.bean.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public int insert(Connection conn, Customer cust) {
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public int deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        return update(conn, sql, id);
    }

    @Override
    public int update(Connection conn, Customer cust) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        return getInstance(conn, sql, id);
    }

    @Override
    public List<Customer> getFive(Connection conn, Integer startIndex) {
        String sql = "SELECT id, name, email, birth FROM customers LIMIT ?, 5";
        return super.getForList(conn, sql,  startIndex);
    }
    @Override
    public List<Customer> getFive(Connection conn, String keyword, Integer startIndex) {
        String sql = "SELECT id, name, email, birth FROM customers where name like ? LIMIT ?, 5";
        return super.getForList(conn, sql, "%" + keyword + "%", startIndex);
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id, name, email, birth from customers";
        return getForList(conn, sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Long getCount(Connection conn, String keyword) {
        String sql = "select count(*) from customers where name like ?";
        return getValue(conn, sql, "%" + keyword + "%");
    }
}
