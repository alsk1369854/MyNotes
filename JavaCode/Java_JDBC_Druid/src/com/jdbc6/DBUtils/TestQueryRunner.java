package com.jdbc6.DBUtils;

import com.jdbc3.DAO.Util.JDBCUtils;
import com.jdbc6.DBUtils.bean.Customer;
import com.mysql.cj.jdbc.Blob;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class TestQueryRunner {

    // 插入數據
    @Test
    public void test1() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        int changeCount = runner.update(conn, sql, "testDBUtils", "dbutils@123.com", "1997-1-1");
        System.out.println(changeCount);
    }

    // 獲取單條紀錄 BeanHandler<Customer>(Customer.class): 是ResultSetHandler接口的實現類
    @Test
    public void test2() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
//        String sql = "select (id,name,email,birth) from customers where id = ?";
        String sql = "select * from customers where id = ?";
        BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
        Customer cust = runner.query(conn, sql, handler, 7);
        System.out.println(cust);
    }

    // 獲取多條紀錄 BeanListHandler<Customer>(Customer.class);: 是ResultSetHandler接口的實現類
    @Test
    public void test3() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
//        String sql = "select (id,name,email,birth) from customers where id = ?";
        String sql = "select * from customers where id < ?";
        BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
        List<Customer> custList = runner.query(conn, sql, handler, 7);
        custList.forEach(System.out::println);
    }

    // 獲取單條紀錄 MapHandler(): 是ResultSetHandler接口的實現類
    @Test
    public void test4() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
//        String sql = "select (id,name,email,birth) from customers where id = ?";
        String sql = "select * from customers where id = ?";
        MapHandler handler = new MapHandler();
        Map<String, Object> cust = runner.query(conn, sql, handler, 7);
        System.out.println(cust);
    }

    // 獲取多條紀錄 MapListHandler();: 是ResultSetHandler接口的實現類
    @Test
    public void test5() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
//        String sql = "select (id,name,email,birth) from customers where id = ?";
        String sql = "select * from customers where id < ?";
        MapListHandler handler = new MapListHandler();
        List<Map<String, Object>> custList = runner.query(conn, sql, handler, 7);
        custList.forEach(System.out::println);
    }

    // 獲取特定單筆紀錄 ScalarHandler(): 是ResultSetHandler接口的實現類
    @Test
    public void test6() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select count(id) from customers";
        ScalarHandler handler = new ScalarHandler();
        Long count = (long) runner.query(conn, sql, handler);
        System.out.println(count);
    }
    @Test
    public void test7() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select max(birth) from customers";
        ScalarHandler handler = new ScalarHandler();
        Date date = (Date) runner.query(conn, sql, handler);
        System.out.println(date);
    }

    // 自定義ResultSetHandler
    @Test
    public void test8() throws Exception {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from customers where id = ?";
        ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
            @Override
            public Customer handle(ResultSet rs) throws SQLException {
                if(rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    Date birth = rs.getDate("birth");
                    return new Customer(id,name,email,birth);
                }
                return null;
            }
        };
        Customer date = runner.query(conn, sql, handler, 7);
        System.out.println(date);
    }


}
