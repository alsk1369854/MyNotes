package JavaWeb.myssm.trans;

import JavaWeb.myssm.baseDAO.util.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    public static void beginTrans() throws SQLException {
        JDBCUtils.getConnection().setAutoCommit(false);
    }

    public static void commit() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        conn.commit();
        conn.setAutoCommit(true);
        JDBCUtils.closeConnection();
    }

    public static void rollback() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        conn.rollback();
        conn.setAutoCommit(true);
        JDBCUtils.closeConnection();
    }


}
