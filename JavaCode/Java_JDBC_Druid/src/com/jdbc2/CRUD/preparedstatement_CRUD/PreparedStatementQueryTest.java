package com.jdbc2.CRUD.preparedstatement_CRUD;

import com.jdbc2.CRUD.Util.JDBCUtils;
import com.jdbc2.CRUD.bean.Customer;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;


// 通用查詢多個操作 (考慮事務)
// 通用查詢多個操作 (未考慮事務)
// 通用查詢單個操作

public class PreparedStatementQueryTest {
    @Test
    public void testGetForListWithTransaction() {
        Connection conn = null;
        try {
            // 1. 獲取連結
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getTransactionIsolation());
            // 設定隔離級別為 讀取提交
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // 關閉自動提交
            conn.setAutoCommit(false);

            String sql = "select id,name,balance from customers";
            List<Customer> list = getForListWithTransaction(conn, Customer.class, sql);
            list.forEach(System.out::println);

            // 提交
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            JDBCUtils.closeResource(conn);
        }
    }

    // 通用查詢多個操作 (考慮事務)
    public <T> List<T> getForListWithTransaction(Connection conn, Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1. 預先載入sql，並填充佔位符
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 2. 查詢
            rs = ps.executeQuery();
            // 3. 獲取結果集的元數據: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 4. 通過ResultSetMetaData獲取結果集的列數
            int columnCount = rsmd.getColumnCount();
            // 建立數據集合
            LinkedList<T> list = new LinkedList<>();
            // 是否有查到東西
            while (rs.next()) {
                // 5. 透過反射建立通用的類實例
                T t = clazz.newInstance();
                // 6. 處理結果集一行數據中的每一個列
                for (int i = 0; i < columnCount; i++) {
                    // 獲取列值
                    Object columnValue = rs.getObject(i + 1);
                    // 獲取列別名(別名必須與java類屬性名一致)
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通過反射: 給對象指定的columnName屬性，賦值為columnValue
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            // 7. 返回結果集合
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            JDBCUtils.closeResource(ps, rs);
        }
        return null;
    }


    // 通用查詢多個操作 (未考慮事務)
    @Test
    public void testGetForList() {
        String sql = "SELECT id id,name name,email email,birth birth FROM `customers` WHERE id < ?";
        List<Customer> list = getForList(Customer.class, sql, 10);
        list.forEach(System.out::println);
        /* output
        Customer{id=1, name='test1', email='test@gmail.com', birth=2022-05-14, balance=0}
        Customer{id=2, name='Han1', email='alsk1369854@gmail.com', birth=1995-12-31, balance=0}
        Customer{id=5, name='Ming', email='alsk1369854@gmail.com', birth=1995-12-31, balance=0}
        * */
    }

    public <T> List<T> getForList(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 1. 獲取連結
            conn = JDBCUtils.getConnection();
            // 2. 預先載入sql，並填充佔位符
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 3. 查詢
            rs = ps.executeQuery();
            // 4. 獲取結果集的元數據: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 5. 通過ResultSetMetaData獲取結果集的列數
            int columnCount = rsmd.getColumnCount();
            // 建立數據集合
            LinkedList<T> list = new LinkedList<>();
            // 是否有查到東西
            while (rs.next()) {
                // 6. 透過反射建立通用的類實例
                T t = clazz.newInstance();
                // 7. 處理結果集一行數據中的每一個列
                for (int i = 0; i < columnCount; i++) {
                    // 獲取列值
                    Object columnValue = rs.getObject(i + 1);
                    // 獲取列別名(別名必須與java類屬性名一致)
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通過反射: 給對象指定的columnName屬性，賦值為columnValue
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            // 8. 返回結果集合
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }


    // 通用查詢單個操作
    @Test
    public void test1() {
        String sql = "SELECT id id,name name,email email,birth birth FROM `customers` WHERE id = ?";
        Customer customer = getInstance(Customer.class, sql, 1);
        System.out.println(customer);
    }

    public <T> T getInstance(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);

            }

            rs = ps.executeQuery();
            // 獲取結果集的元數據: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通過ResultSetMetaData獲取結果集的列數
            int columnCount = rsmd.getColumnCount();

            // 是否有查到東西
            if (rs.next()) {
                T t = clazz.newInstance();
                // 處理結果集一行數據中的每一個列
                for (int i = 0; i < columnCount; i++) {
                    // 獲取列值
                    Object columnValue = rs.getObject(i + 1);
                    // 獲取列別名(別名必須與java類屬性名一致)
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通過反射: 給對象指定的columnName屬性，賦值為columnValue
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
}
