package com.jdbc2.CRUD.preparedstatement_CRUD;

import com.jdbc2.CRUD.Util.JDBCUtils;
import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Properties;

/*
    ORM編成思想 (Object Relational Mapping)
        一個數據表對應一個java類
        表中一條紀錄對應java類的一個對象
        表中的一個字段對應java類的個屬性

    針對於表的字段名與類的屬性名不相同的情況
        1. 必須聲明sql時，使用類的屬性名來命名字段的別名
        2. 使用ResultSetMetaData時，需使用getColumnLabel()來獲取列別名(沒有別名則獲取到原列名)


    // 批量insert
        1.
            connection.setAutoCommit(false) // 關閉自動提交
            addBatch() // 將執行添加至 Batch
            executeBatch() // 處理 Batch 中累積的數據
            clearBatch() // 清空 Batch 中的數據(通常跟在executeBatch()後面)
            connection.commit() // 手動提交數據
        2. mysql服務器是關閉批量處理的，我們需要通過一個參數，讓mysql開啟批量處理的支持。
            mysql.jdbc-5.1.37 在 url 後加上 ?rewriteBatchedStatements=true
                Example - jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8
    // 通用Update(未考慮事務)
    // 通用Update(考慮事務)


* */

// 增刪改 查

public class PreparedStatementUpdateTest {

    // 通用Update(考慮事務) // 模擬轉帳
    @Test
    public void testUpdateWithTransaction() {
        Connection conn = null;
        try {
            // 1. 獲取數據庫的連接
            conn = JDBCUtils.getConnection();
            // 2. 關閉自動提交
            conn.setAutoCommit(false);
            // 扣除甲方帳戶
            String sql1 = "UPDATE customers SET balance = balance-100 WHERE id = ?";
            updateWithTransaction(conn, sql1, 1);

            // System.out.println(2/0); // 模擬網路異常

            // 添加乙方帳戶
            String sql2 = "UPDATE customers SET balance = balance+100 WHERE id = ?";
            updateWithTransaction(conn, sql2, 2);
            // 3. 提交事務
            conn.commit();
            System.out.println("轉帳成功");
        } catch (Exception e) {
            e.printStackTrace();
            // 發生錯誤時回滾事務
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // 恢復自動提交事務
            // 主要針對連接池同一狀態
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 4. 關閉資源
            JDBCUtils.closeResource(conn);
        }
    }

    public int updateWithTransaction(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            // 1. 預編譯sql語句，返回PreparedStatement的實例
            ps = conn.prepareStatement(sql);
            // 2. 填充佔位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            // 3. 執行: 回傳數據庫引響行數
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4. 關閉資源
            JDBCUtils.closeResource(ps);
        }
        return 0;
    }


    // 批量insert
    @Test
    public void testBatch() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 獲取數據庫的連接
            conn = JDBCUtils.getConnection();
            // 1. 關閉自動提交數據
            conn.setAutoCommit(false);
            // 預編譯sql語句，返回PreparedStatement的實例
            String sql = "INSERT INTO good(name) VALUE(?)";
            ps = conn.prepareStatement(sql);
            // 填充佔位符
            for (int i = 1; i <= 10000; i++) {
                ps.setObject(1, "name_" + i);
                // 1. 加入batch
                ps.addBatch();
                if ((i % 500) == 0) {
                    // 2. 執行batch
                    ps.executeBatch();
                    // 3. 清空batch
                    ps.clearBatch();
                }
            }
            // 4. 提交數據
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 關閉資源
            JDBCUtils.closeResource(conn, ps);
        }
    }

    // 通用Update(未考慮事務)
    @Test
    public void testUpdateSQL() {
        // 刪除操作
        String sql = "DELETE FROM customers WHERE id = ?";
        int i = updateSQL(sql, 4);
        if (i > 0) {
            System.out.println("更新成功");
        } else {
            System.out.println("更新失敗");
        }

        // 新增操作
        //String sql = "INSERT INTO customers(NAME,email,birth) VALUE(?,?,?)";
        //int i = updateSQL(sql, "JJ", "JJ@gmail.com", "1996-5-13");
        //if(i > 0){
        //    System.out.println("更新成功");
        //}else{
        //    System.out.println("更新失敗");
        //}
    }

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

    // 改
    @Test
    public void setTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 獲取數據庫的連接
            conn = JDBCUtils.getConnection();

            // 2. 預編譯sql語句，返回PreparedStatement的實例
            // UPDATE customers SET NAME='test1' WHERE id =1;
            String sql = "UPDATE `customers` SET NAME= ? WHERE id = ?";
            ps = conn.prepareStatement(sql);

            // 3. 填充佔位符
            ps.setObject(1, "Han");
            ps.setObject(2, 2);

            // 4. 執行
            ps.execute();
            System.out.println("修改完成");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 關閉資源
            JDBCUtils.closeResource(conn, ps);
        }
    }

    // 增
    @Test
    public void insertTest() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 獲取數據庫的連接
            conn = JDBCUtils.getConnection();

            // 2. 預編譯sql語句，返回PreparedStatement的實例
            // INSERT INTO customers(NAME, email, birth) VALUE(Ming, alsk1369854@gmail.com, 1996-08-06);
            String sql = "INSERT INTO customers(NAME,email,birth) VALUE(?,?,?)";
            ps = conn.prepareStatement(sql);

            // 3. 填充佔位符
            ps.setString(1, "Ming");
            ps.setString(2, "alsk1369854@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            java.util.Date birth = sdf.parse("1996-08-06");
            ps.setDate(3, new Date(birth.getTime()));

            // 4. 執行
            ps.execute();
            System.out.println("添加完成");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 關閉資源
            JDBCUtils.closeResource(conn, ps);
        }
    }

    // 增 (大雜燴版)
    @Test
    public void preparedStatementTest1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 讀取配置文件4個基本訊息
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(is);
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");
            String url = prop.getProperty("url");
            String driverClass = prop.getProperty("driverClass");

            // 2. 加載 Driver
            Class.forName(driverClass);

            // 3. 連接Sql
            conn = DriverManager.getConnection(url, user, password);

            // 4. 預編輯sql語句，返回 PreparedStatement 的實例
            String sql = "INSERT INTO customers(NAME,email,birth) VALUE(?,?,?)";
            ps = conn.prepareStatement(sql);
            // 5. 填充佔位符
            ps.setString(1, "Ming");
            ps.setString(2, "alsk1369854@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            java.util.Date birth = sdf.parse("1996-08-06");
            ps.setDate(3, new Date(birth.getTime()));

            // 6. 執行操作
            ps.execute();
            System.out.println("添加完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. 關閉資源
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
