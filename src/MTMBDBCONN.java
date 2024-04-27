import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MTMBDBCONN {
    // Declaring SQL classes
    private Connection conn = null;

    // DB Credentials
    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private final String DBname = "mtmbrecord";
    private final String DBurl = "jdbc:mysql://localhost:3306/" + DBname;
    private final String dbUsername = "root";
    private final String dbPassword = "";

    public MTMBDBCONN() {
        connect(); // Automatically establish the connection upon instantiation
    }

    private void connect() {
        try {
            Class.forName(jdbcDriver);
            conn = DriverManager.getConnection(DBurl, dbUsername, dbPassword);
            if (conn != null) {
                System.out.println("Successfully Connected to Database!");
            } else {
                System.out.println("Failed to Connect to Database!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
