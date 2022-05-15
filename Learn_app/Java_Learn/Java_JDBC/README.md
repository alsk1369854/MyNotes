# Java JDBC MySQL

### ORM編成思想 (Object Relational Mapping)
+ 一個數據表對應一個java類
+ 表中一條紀錄對應java類的一個對象
+ 表中的一個字段對應java類的個屬性
<br/>

### Java 與 MySQL 數據對應關係
<p align=center>
<img height=400px src=/Learn_app\Java_Learn\Java_JDBC\Java_jdbc_數據類型對應關係.png/>
</p> 
<br/>

## 簡介
### DAO 範例
+ BaseDAO
+ CustomerDAO
+ CustomerDAOImpl

### Connection Util
+ Step 1: 將mysql中驅動導入專案lib中
  + Step 1.1: 在MySQL文件中找到JDBC驅動(Connector)
  + Step 2.2: 將驅動導入專案lib中
+ Step 2: 配置 jdbc.properties -> src/jdbc.properties
+ Step 3: 實作 class JDBCUtils

### CRUD
+ Java Bean Class Customer
+ 增/刪/改 Update
  + 未考慮事務(Transaction)
  + 考慮事務(Transaction)
+ 查 Query
+ Blob Data 處理
  + Blob Data 數據說明
  + 更新Blob數據
  + 查詢Blob類型 並下載到本地
+ 批量(Batch)插入與刪除

### 事務的隔離 Transaction isolation
+ 事務的ACID屬性
+ 四種隔離級別

### Demo SQL Language

<br/>

> ## DAO
### BaseDAO
```java
import com.jdbc5.Druid.Util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseDAO<T> {

    // 透過映射取出子類傳入的泛型類型
    private Class<T> clazz = null;
    {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType)type;
        clazz = (Class<T>)paramType.getActualTypeArguments()[0];
    }

    // 通用查詢多個操作 (考慮事務)
    public List<T> getForList(Connection conn, String sql, Object... args) {
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

    // 通用查詢單個操作 (考慮事務)
    public T getInstance( Connection conn, String sql, Object... args) {
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
            JDBCUtils.closeResource(ps, rs);
        }
        return null;
    }

    // 通用Update(考慮事務)
    public int update(Connection conn, String sql, Object... args) {
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

    // 用於查詢特殊值得通用方法
    public <E> E getValue(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for(int i=0; i<args.length; i++){
                ps.setObject(i+1, args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, rs);
        }
        return null;
    }
}

```
<br/>

### CustomerDAO
```java
import com.jdbc5.Druid.bean.Customer;

import java.sql.Connection;
import java.util.List;

public interface CustomerDAO {
    int insert(Connection conn, Customer cust);

    int deleteById(Connection conn, int id);

    int update(Connection conn, Customer cust);

    Customer getCustomerById(Connection conn, int id);

    List<Customer> getAll(Connection conn);

    Long getCount(Connection conn);
}
```
<br/>

### CustomerDAOImpl
```java
import com.jdbc5.Druid.bean.Customer;

import java.sql.Connection;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public int insert(Connection conn, Customer cust) {
        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public int deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        return update(conn, sql, id);
    }

    @Override
    public int update(Connection conn, Customer cust) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        return update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        return getInstance(conn, sql, id);
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id, name, email, birth from customers";
        return getForList(conn,sql);
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }
}
```
<br/>

> ## Connection Util
### Step 1: 將mysql中驅動導入專案lib中
#### Step 1.1: 在MySQL文件中找到JDBC驅動(Connector)
<p align=center>
<img height=400px src=/Learn_app\Java_Learn\Java_JDBC\jdbc_mysql_connector.png/>
</p>
<br/>

#### Step 2.2: 將驅動導入專案lib中
<p align=center>
<img height=400px src=/Learn_app\Java_Learn\Java_JDBC\idea_add_mysql_connector_lib.png/>
</p>
<br/>

### Step 2: 配置 jdbc.properties -> src/jdbc.properties
```
username=root
password=root
url=jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8
driverClassName=com.mysql.cj.jdbc.Driver

# Druid
# https://github.com/alibaba/druid/wiki/DruidDataSource%E9%85%8D%E7%BD%AE%E5%B1%9E%E6%80%A7%E5%88%97%E8%A1%A8
initialSize=10
minIdle=10
maxActive=20
maxWait=1000
filters=wall
```
<br/>

### Step 3: 實作 class JDBCUtils
```java
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    // 連接 Druid 數據池
    private static DataSource source;

    // 連接 Druid 數據池
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

        // 自己取得連接的方式(不推薦)
        //// 1. 讀取配置文件4個基本訊息
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        //Properties prop = new Properties();
        //prop.load(is);
        //String user = prop.getProperty("username");
        //String password = prop.getProperty("password");
        //String url = prop.getProperty("url");
        //String driverClass = prop.getProperty("driverClassName");
        //
        //// 2. 加載 Driver
        //Class.forName(driverClass);
        ///* 加載 mysql Driver時, 自動的註冊了Driver
        //  static {
        //        try {
        //            DriverManager.registerDriver(new Driver());
        //        } catch (SQLException var1) {
        //            throw new RuntimeException("Can't register driver!");
        //        }
        //    }
        //* */
        //
        //// 3. 連接Sql
        //Connection cnn = DriverManager.getConnection(url, user, password);
        //return cnn;
    }

    public static void closeResource(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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

```
<br/>

> ## CRUD
### Java Bean Class Customer
```java
import java.sql.Date;

public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;
    private Integer balance;

    public Customer() {
    }

    public Customer(int id, String name, String email, Date birth) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", balance=" + balance +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
```
<br/>

### 增/刪/改 Update
#### 未考慮事務(Transaction)
```java
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
```
<br/>

#### 考慮事務(Transaction)
```java
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
```
<br/>

### 查 Query
#### 1. 針對於表的字段名與類的屬性名不相同的情況
  1. 必須聲明sql時，使用類的屬性名來命名字段的別名
  2. 使用ResultSetMetaData時，需使用getColumnLabel()來獲取列別名(沒有別名則獲取到原列名)
<br/>

#### 2. Java Code
```java
    // 通用查詢多個操作
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
```
<br/>

### Blob Data 處理
#### Blob Data 數據說明
<p align=center>
<img height=400px src=/Learn_app\Java_Learn\Java_JDBC\jdbc_blob_type.png/>
</p> 
<br/>

#### 更新Blob數據
```java
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
```
<br/>

#### 查詢Blob類型 並下載到本地
```java
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
```
<br/>

### 批量插入與刪除
#### 1. connection.setAutoCommit(false) // 關閉自動提交
+ addBatch() // 將執行添加至 Batch
+ executeBatch() // 處理 Batch 中累積的數據
+ clearBatch() // 清空 Batch 中的數據(通常跟在executeBatch()後面)
+ connection.commit() // 手動提交數據
#### 2. mysql服務器是關閉批量處理的，我們需要通過一個參數，讓mysql開啟批量處理的支持。
+ mysql.jdbc-5.1.37 在 url 後加上 ?rewriteBatchedStatements=true
    + Example - jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true&useUnicode=true&characterEncoding=utf-8
<br/>

#### 3. java Code
```java
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
```
<br/>

> ## 事務的隔離 Transaction isolation
### 事務的ACID屬性
<p align=center>
<img height=400px src=/Learn_app\Java_Learn\Java_JDBC\jdbc_transaction_ACID.png/>
</p>
<br/>

### 四種隔離級別
<p align=center>
<img height=400px src=/Learn_app\Java_Learn\Java_JDBC\sql_4種隔離級別.png/>
</p>
<br/>

> ## Demo SQL Language
```sql
```

<br/>

#### _END_