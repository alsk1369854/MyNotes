import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class demo1 {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			System.out.println("找不到驅動程式類別");
			e.printStackTrace();
		}
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM gogo");
				while(resultSet.next()) {
					System.out.println(resultSet.getString("q")); //獲得欄位名稱"q"的值
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static Connection getConnection() throws SQLException {
		String serverName = "140.127.74.170"; //伺服器網址
		String database = "test2";
		String user = "test2";
		String password = "test2";
		String url = "jdbc:mysql://" + serverName + "/" + database; //JDBC連線網址
		return DriverManager.getConnection(url, user, password);
	}
}
