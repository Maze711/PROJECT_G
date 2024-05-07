package DatabaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MTMBDBAccount {
	// Declaring SQL classes
		Connection conn = null;

		// DB Credentials
		private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
		private final String DBname = "mtmbaccount";
		private final String DBurl = "jdbc:mysql://localhost:3306/" + DBname;
		private final String dbUsername = "root";
		private final String dbPassword = "";

		public static void main(String[] args) {
			new MTMBDBCONN();
		}

		public MTMBDBAccount() {
			Connect();
		}

		void Connect() {
			try {
				Class.forName(jdbcDriver);
				conn = DriverManager.getConnection(DBurl, dbUsername, dbPassword);
				if (conn != null) {
					System.out.println("Successfully Connected to Database!");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			} 
		}

		public Connection getConnection() {
			return conn;
		}
}
