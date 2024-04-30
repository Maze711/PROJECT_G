import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MTMBDBArchive {
	private Connection conn = null;
	private final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
	private final String recordDBname = "mtmbrecord";
	private final String archiveDBname = "mtmbarchive";
	private final String recordDBurl = "jdbc:mysql://localhost:3306/" + recordDBname;
	private final String archiveDBurl = "jdbc:mysql://localhost:3306/" + archiveDBname;
	private final String dbUsername = "root";
	private final String dbPassword = "";

	public MTMBDBArchive() {
		connect();
	}

	private void connect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(archiveDBurl, dbUsername, dbPassword);
			if (conn != null) {
				System.out.println("Successfully Connected to Archive Database!");
			} else {
				System.out.println("Failed to Connect to Archive Database!");
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

	public void copyTable(String tableName) {
		Connection sourceConn = null;
		Statement sourceStmt = null;
		ResultSet rs = null;
		PreparedStatement targetStmt = null;

		try {
			sourceConn = DriverManager.getConnection(recordDBurl, dbUsername, dbPassword);
			sourceStmt = sourceConn.createStatement();
			rs = sourceStmt.executeQuery("SELECT * FROM " + tableName);

			conn.setAutoCommit(false); // Set auto-commit to false for better performance

			// Create the table in the archive database with the year prefix
			String year = String.valueOf(java.time.LocalDate.now().getYear());
			String archiveTableName = year + "_" + tableName;
			createTable(archiveTableName, rs.getMetaData());

			// Prepare the insert statement with placeholders for all columns
			StringBuilder insertSQL = new StringBuilder("INSERT INTO " + archiveTableName + " VALUES (");
			for (int i = 0; i < rs.getMetaData().getColumnCount() + 1; i++) { // Add 1 for MonthArchived
				insertSQL.append("?, ");
			}
			// Remove the trailing comma and add closing parenthesis
			insertSQL.replace(insertSQL.lastIndexOf(", "), insertSQL.length(), ")");

			targetStmt = conn.prepareStatement(insertSQL.toString());

			// Iterate over the result set and insert each row into the archive table
			while (rs.next()) {
				// Set values for each column
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					targetStmt.setObject(i, rs.getObject(i));
				}
				// Set value for the new MonthArchived column (name of the month)
				targetStmt.setString(rs.getMetaData().getColumnCount() + 1, getCurrentMonth());

				// Execute the insert statement for each row
				targetStmt.executeUpdate();
			}

			conn.commit(); // Commit the transaction
			System.out.println("Table '" + tableName + "' archived successfully.");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null) {
					conn.rollback(); // Rollback if an exception occurs
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} finally {
			// Close resources in the finally block
			try {
				if (rs != null)
					rs.close();
				if (sourceStmt != null)
					sourceStmt.close();
				if (targetStmt != null)
					targetStmt.close();
				if (sourceConn != null)
					sourceConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// Method to get the current month (name of the month)
	private String getCurrentMonth() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int month = cal.get(java.util.Calendar.MONTH) + 1; // Month value is 0-based
		switch (month) {
		case 1:
			return "January";
		case 2:
			return "February";
		case 3:
			return "March";
		case 4:
			return "April";
		case 5:
			return "May";
		case 6:
			return "June";
		case 7:
			return "July";
		case 8:
			return "August";
		case 9:
			return "September";
		case 10:
			return "October";
		case 11:
			return "November";
		case 12:
			return "December";
		default:
			return ""; // Return empty string for invalid month
		}
	}

	private void createTable(String tableName, ResultSetMetaData rsMetaData) throws SQLException {
		StringBuilder createTableSQL = new StringBuilder("CREATE TABLE " + tableName + " (");

		// Loop through columns from ResultSet metadata
		int columnCount = rsMetaData.getColumnCount();
		for (int i = 1; i <= columnCount; i++) {
			String columnName = rsMetaData.getColumnName(i);
			String columnType = mapColumnTypeToSQL(rsMetaData.getColumnType(i));
			createTableSQL.append(columnName).append(" ").append(columnType);
			if (i < columnCount) {
				createTableSQL.append(",");
			}
		}

		// Add additional column for MonthArchived and define primary key
		createTableSQL.append(", MonthArchived VARCHAR(255), PRIMARY KEY (CtrlNo))");

		// Execute the SQL statement to create the table
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(createTableSQL.toString());
		stmt.close();
	}

	// Method to map JDBC types to SQL types
	private String mapColumnTypeToSQL(int columnType) {
		switch (columnType) {
		case java.sql.Types.INTEGER:
			return "INT";
		case java.sql.Types.VARCHAR:
			return "VARCHAR(255)";
		// Add more cases for other data types as needed
		default:
			// Default to VARCHAR(255) for unrecognized column types
			return "VARCHAR(255)";
		}
	}
	
	public String[] getTableNames() {
	    ArrayList<String> tableNames = new ArrayList<>();
	    try {
	        DatabaseMetaData metaData = conn.getMetaData();
	        String[] tableTypes = {"TABLE"}; // Specify the table type
	        String catalog = conn.getCatalog(); // Get the current database name
	        ResultSet resultSet = metaData.getTables(catalog, null, "%", tableTypes);
	        while (resultSet.next()) {
	            String tableName = resultSet.getString("TABLE_NAME").toUpperCase().replace("_", " ");
	            tableNames.add(tableName);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return tableNames.toArray(new String[0]);
	}

}
