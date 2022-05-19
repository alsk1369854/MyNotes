package com.jdbc6.DBUtils.Util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Queue;

public class JDBCUtils {
    // 連接 Druid 連接池
    private static DataSource source;

    // 連接 Druid 連接池
    static {
        InputStream is = null;
        try {
            is = ClassLoader.getSystemResourceAsStream("jdbc.properties");
            Properties prop = new Properties();
            prop.load(is);
            source = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        // 從連接池拿取連接
        return source.getConnection();
    }

    public static void closeResource(Connection conn) {
        // dbUtils.jar 提供DbUtils工具類實現資源的關閉
        DbUtils.closeQuietly(conn);
    }

    public static void closeResource(PreparedStatement ps) {
        DbUtils.closeQuietly(ps);
    }

    public static void closeResource(ResultSet rs) {
        DbUtils.closeQuietly(rs);
    }

    public static void closeResource(Connection conn, PreparedStatement ps) {
        JDBCUtils.closeResource(conn);
        JDBCUtils.closeResource(ps);
    }

    public static void closeResource(PreparedStatement ps, ResultSet rs) {
        JDBCUtils.closeResource(ps);
        JDBCUtils.closeResource(rs);
    }

    public static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        JDBCUtils.closeResource(conn, ps);
        JDBCUtils.closeResource(rs);
    }
}
