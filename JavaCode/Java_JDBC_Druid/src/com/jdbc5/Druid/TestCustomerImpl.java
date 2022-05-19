package com.jdbc5.Druid;

import com.jdbc5.Druid.Util.JDBCUtils;
import com.jdbc5.Druid.bean.Customer;
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
        int i = dao.deleteById(conn, 9);
        System.out.println(i);
        JDBCUtils.closeResource(conn);
    }

    @Test
    public void update() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        Customer cust = new Customer(2, "testDAO1", "test@gmail.com", new Date(146546465L));
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
