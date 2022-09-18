package JavaWeb.myssm.baseDAO.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    // 連接 Druid 連接池
    private static DataSource source;

    // 連接 Druid 連接池
    static {
//        InputStream is = null;
        FileInputStream is = null;
        try {
            // JavaWebProject 中 PathRoot = tomcat/bin
            is = new FileInputStream(new File("config/jdbc.properties"));
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


    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        // 從連接池拿取連接
        Connection conn = threadLocal.get();
        if( conn == null){
            conn = source.getConnection();
            threadLocal.set(conn);
        }
        return threadLocal.get();
    }
    public static void closeConnection() throws SQLException {
        // 從連接池拿取連接
        Connection conn = threadLocal.get();
        if( conn == null){
            return;
        }
        if(!conn.isClosed()){
            conn.close();
//            threadLocal.set(null);
            threadLocal.remove();
        }
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
