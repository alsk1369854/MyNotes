package com.jdbc2.CRUD.preparedstatement_CRUD;

import com.jdbc2.CRUD.bean.Customer;
import com.jdbc2.CRUD.Util.JDBCUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;

public class CustomerForQuery {
    // 通用查詢操作
    @Test
    public void testQueryForCustomers(){
        String sql = "SELECT id id,name name,email email,birth birth FROM `customers` WHERE id = ?";
        Customer customer = QueryForCustomers(sql, 3);
        System.out.println(customer);
    }

    public Customer QueryForCustomers(String sql, Object... args){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for(int i=0; i<args.length;i++){
                ps.setObject(i+1, args[i]);

            }

            rs = ps.executeQuery();
            // 獲取結果集的元數據: ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通過ResultSetMetaData獲取結果集的列數
            int columnCount = rsmd.getColumnCount();

            // 是否有查到東西
            if(rs.next()){
                Customer cust = new Customer();
                // 處理結果集一行數據中的每一個列
                for(int i=0; i<columnCount; i++){
                    // 獲取列值
                    Object columnValue = rs.getObject(i + 1);
                    // 獲取列別名(別名必須與java類屬性名一致)
                    String columnName = rsmd.getColumnLabel(i + 1);
                    // 通過反射: 給對象指定的columnName屬性，賦值為columnValue
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(cust,columnValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return null;
    }

    // 查詢blob類型 並下載到本地
    @Test
    public void test1(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtils.getConnection();
            // SELECT id,NAME,email,birth FROM customers  WHERE id = 2;
            String sql = "SELECT id,name,email,birth,photo FROM customers  WHERE id = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1, 7);

            rs = ps.executeQuery();

            if(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date date = rs.getDate(4);

                Customer customer = new Customer(id, name, email, date);
                System.out.println(customer);

                Blob blob = rs.getBlob(5);
                is = blob.getBinaryStream();
                fos = new FileOutputStream(new File("DownloadImg.png"));
                byte[] buffer = new byte[1024];
                int len;
                while((len = is.read(buffer)) != -1){
                    fos.write(buffer,0,len);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 關閉資源
            JDBCUtils.closeResource(conn,ps,rs);
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
