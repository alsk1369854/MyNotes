package com.jdbc3.DAO;

import com.jdbc3.DAO.Util.JDBCUtils;
import com.jdbc3.DAO.bean.Customer;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class TestCustomerImpl {

    CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void insert() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Customer cust = new Customer(1, "testDAO", "test@gmail.com", new Date(146546465L));
        dao.insert(conn, cust);
        JDBCUtils.closeResource(conn);
    }

    @Test
    public void deleteById() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        int i = dao.deleteById(conn, 8);
        System.out.println(i);
        JDBCUtils.closeResource(conn);
    }

    @Test
    public void update() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Customer cust = new Customer(2, "testDAO", "test@gmail.com", new Date(146546465L));
        dao.update(conn, cust);
        JDBCUtils.closeResource(conn);
    }

    @Test
    public void getCustomerById() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Customer cust = dao.getCustomerById(conn, 1);
        System.out.println(cust);
        JDBCUtils.closeResource(conn);
    }

    @Test
    public void getAll() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        List<Customer> list = dao.getAll(conn);
        list.forEach(System.out::println);
        JDBCUtils.closeResource(conn);
    }

    @Test
    public void getCount() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Long count = dao.getCount(conn);
        System.out.println(count);
        JDBCUtils.closeResource(conn);
    }
}
