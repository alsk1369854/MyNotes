package JavaWeb.dao;

import JavaWeb.dao.Util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class TestJDBC {

    @Test
    public void test1() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);
    }
}
