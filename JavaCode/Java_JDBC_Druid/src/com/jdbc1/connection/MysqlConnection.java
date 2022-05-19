package com.jdbc1.connection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MysqlConnection {

    // 方式一 (不推薦)
    @Test
    public void test1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        // jdbc:mysql:協議
        // localhost:ip地址
        // 3306:默認mysql的端口
        // test:test數據庫
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        // 將用戶名封裝在Properties中
        info.setProperty("user", "root");
        info.setProperty("password", "root");

        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    // 方式二
    @Test
    public void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) driverClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "root");

        Connection connect = driver.connect(url, info);
        System.out.println(connect);
    }

    // 方式三
    @Test
    public void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // 1. 獲取Driver實現類的對象
        Class<?> driverClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) driverClass.newInstance();
        /*
          static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
        * */

        // 2. 提供另外三個連接基本信息:
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";

        // 3. 註冊驅動
        DriverManager.registerDriver(driver);

        // 4. 獲取連接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    // 方式四
    @Test
    public void test4() throws SQLException, ClassNotFoundException {
        // 1. 提供另外三個連接基本信息:
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";

        // 2. 加載Driver
        Class.forName("com.mysql.cj.jdbc.Driver");
        /* 加載 mysql Driver時, 自動的註冊了Driver
          static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
        * */

        // 3. 獲取連接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    /* 方式五
    * 好處:
    *   1. 實現數據與代碼分離。(解耦合)
    *   2. 如需修改配置訊息，可避免代碼重新打包。
    * */
    @Test
    public void test() throws IOException, ClassNotFoundException, SQLException {
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
        /* 加載 mysql Driver時, 自動的註冊了Driver
          static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
        * */

        // 3. 連接Sql
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn); // com.mysql.cj.jdbc.ConnectionImpl@704921a5
    }
}
