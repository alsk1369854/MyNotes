# Java JDBC MySQL

### Connection Util
+ Step 1: 將mysql中驅動導入專案lib中
  + Step 1.1: 在MySQL文件中找到JDBC驅動(Connector)
  + Step 2.2: 將驅動導入專案lib中
+ Step 2: 配置 jdbc.properties -> src/jdbc.properties
+ Step 3: 實作 class JDBCUtils

### CRUD
+ 增
+ 改
+ 刪
+ 查

<br/>

> ## 1. Connection Util
### Step 1: 將mysql中驅動導入專案lib中
#### Step 1.1: 在MySQL文件中找到JDBC驅動(Connector)
<p align=center>
<img height=500px src=/Learn_app\Java_Learn\Java_JDBC\jdbc_mysql_connector.png/>
</p>
<br/>

#### Step 2.2: 將驅動導入專案lib中
<p align=center>
<img height=500px src=/Learn_app\Java_Learn\Java_JDBC\idea_add_mysql_connector_lib.png/>
</p>
<br/>

### Step 2: 配置 jdbc.properties -> src/jdbc.properties
```
user=root
password=root
url=jdbc:mysql://localhost:3306/test
driverClass=com.mysql.cj.jdbc.Driver
```
<br/>

### Step 3: 實作 class JDBCUtils
```java
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    public static Connection getConnection() throws Exception{
        // 1. 讀取配置文件4個基本訊息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties"); // 根目錄為 src
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
        Connection cnn = DriverManager.getConnection(url, user, password);
        return cnn;
    }

    public static void closeResource(Connection conn, PreparedStatement ps){
        // 7. 關閉資源
        if(ps != null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```
<br/>

> ## CRUD
### 增 insert
```java
    // 增
    @Test
    public void insertTest(){
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
            ps.setString(1,"Ming");
            ps.setString(2,"alsk1369854@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
            java.util.Date birth = sdf.parse("1996-08-06");
            ps.setDate(3, new Date(birth.getTime()));

            // 4. 執行
            ps.execute();
            System.out.println("添加完成");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 5. 關閉資源
            JDBCUtils.closeResource(conn, ps);
        }
    }
```
<br/>

### 改 set
```java
    // 改
    @Test
    public void setTest(){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 獲取數據庫的連接
            conn = JDBCUtils.getConnection();

            // 2. 預編譯sql語句，返回PreparedStatement的實例
            // UPDATE customers SET NAME='test1' WHERE id =1;
            String sql = "UPDATE customers SET NAME= ? WHERE id = ?";
            ps = conn.prepareStatement(sql);

            // 3. 填充佔位符
            ps.setObject(1,"Han");
            ps.setObject(2, 2);

            // 4. 執行
            ps.execute();
            System.out.println("修改完成");

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 5. 關閉資源
            JDBCUtils.closeResource(conn, ps);
        }
    }
```
<br/>

### 刪
```java
```
<br/>

### 查
```java
```
<br/>

<br/>

#### _END_