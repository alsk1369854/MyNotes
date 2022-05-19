package com.jdbc2.CRUD.preparedstatement_CRUD;

import com.jdbc2.CRUD.Util.JDBCUtils;
import com.jdbc2.CRUD.bean.Customer;
import java.sql.Blob;
import org.junit.Test;

import java.io.*;
import java.sql.*;

// 查詢blob類型 並下載到本地
// 更新blob數據

public class BlobCRUD {
    // 查詢 blob類型 並下載到本地
    // 使用 resultSet.getBlob(n) 獲取 Blob 數據對象
    // 使用 blob.getBinaryStream() 獲取 InputStream 流對象
    @Test
    public void testDownloadBlobData(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            // 獲取連結
            conn = JDBCUtils.getConnection();
            // 預先加載 sql
            String sql = "SELECT id,name,email,birth,photo FROM customers  WHERE id = ?";
            ps = conn.prepareStatement(sql);
            // 填充佔位符
            ps.setObject(1, 7);
            // 執行查詢
            rs = ps.executeQuery();
            // 使否存在數據
            if(rs.next()){
                // 建立關聯物件
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date date = rs.getDate(4);
                Customer customer = new Customer(id, name, email, date);
                System.out.println(customer); // Customer{id=7, name='Green', email='green@Email.com', birth=1990-01-01, balance=0}

                // 1. 讀出圖片
                Blob blob = rs.getBlob(5);
                // 2. 調用 getBinaryStream() 獲取 InputStream
                is = blob.getBinaryStream();
                // 3. 寫入本地文件
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
            // 4. 關閉資源
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

    // 更新blob數據
    // 寫入Blob數據需要寫入 InputStream
    @Test
    public void testUpdateBlob(){
        FileInputStream fis = null;
        try {
            String sql = "INSERT INTO customers(NAME, email, birth, photo) VALUE(?,?,?,?)";
            // 讀取本圖片轉成 InputStream 寫入sql ; PathRoot = Module/
            fis = new FileInputStream(new File("img.png"));
            updateSQL(sql, "Green", "green@Email.com", "1990-1-1", fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            // 關閉資源
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 通用Update(未考慮事務)
    public int updateSQL(String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 獲取數據庫的連接
            conn = JDBCUtils.getConnection();
            // 2. 預編譯sql語句，返回PreparedStatement的實例
            ps = conn.prepareStatement(sql);
            // 3. 填充佔位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 4. 執行: 回傳數據庫引響行數
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 關閉資源
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }
}
