import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MTMBDBCONN {
    private Connection conn = null;
    private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private final String DBname = "mtmbrecord";
    private final String DBurl = "jdbc:mysql://localhost:3306/" + DBname;
    private final String dbUsername = "root";
    private final String dbPassword = "";

    public MTMBDBCONN() {
        connect();
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
    
    public String[] getTableNames() {
        ArrayList<String> tableNames = new ArrayList<>();
        try {
            DatabaseMetaData metaData = conn.getMetaData();
            String[] tableTypes = {"TABLE"}; // Specify the table type
            String catalog = conn.getCatalog(); // Get the current database name
            ResultSet resultSet = metaData.getTables(catalog, null, "%", tableTypes);
            while (resultSet.next()) {
                String tableName = resultSet.getString("TABLE_NAME");
                tableNames.add(tableName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames.toArray(new String[0]);
    }

}
