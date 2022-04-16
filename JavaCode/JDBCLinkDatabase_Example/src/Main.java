/*
  1. 裝JAR
  2. import sql*
  3. 設定要用哪一種Driver 這邊用JDBC
  4. 設定要連的資料庫位址
  5. 設定使用者登入資料
  6. 連線
  7. 查詢語句
  8. 取得解果
  9. 印出解果
  10. 關掉
*/

// 2. import sql
import java.sql.*;

public class Main {
	public static void main(String[] args) {
		try {
			// 3. 設定要用哪一種Driver 這邊用JDBC
			String JDBCDriver = "com.mysql.jdbc.Driver";
			Class.forName(JDBCDriver);
		
			// 4. 設定要連的資料庫位址
			String URL = "jdbc:mysql://140.127.74.170/test2";
			
			// 5. 設定使用者登入資料
			String User = "test2";
			String Password = "test2";
			
			// 6. 連線
			Connection conn = DriverManager.getConnection(URL, User, Password);
			Statement stmt = conn.createStatement();
			
			// 7. 查詢語句
			String sql;
			sql = "SELECT * FROM gogo";
			
			// 8. 取得解果
			ResultSet rs = stmt.executeQuery(sql);
			
			// 9. 印出解果
			// q = 屬性名稱
			while(rs.next()) {
				String query = rs.getString("q");
				System.out.println("q: " + query);
			}
			
			// 10. 關掉
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
