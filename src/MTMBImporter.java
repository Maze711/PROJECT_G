import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CompletableFuture;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MTMBImporter {

    private static final Logger logger = LogManager.getLogger(MTMBImporter.class);

    public CompletableFuture<Void> importExcelFile(String filePath, String tableName) {
        return CompletableFuture.runAsync(() -> {
            try {
                // Check file type
                if (filePath.endsWith(".xlsx")) {
                    // Log Excel file content
                    logExcelFileContent(filePath);
                    // Proceed with reading and importing
                    readExcelXlsx(filePath, tableName);
                } else {
                    throw new IllegalArgumentException("Unsupported file type");
                }
            } catch (IOException | InvalidFormatException | SQLException e) {
                logger.error("Error importing Excel file: {}", e.getMessage());
                e.printStackTrace(); // Print stack trace for debugging
            }
        });
    }

    private void logExcelFileContent(String filePath) throws IOException, InvalidFormatException {
        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            // Log content of each row and column
            logger.info("Content of Excel file:");
            for (Row row : sheet) {
                StringBuilder rowContent = new StringBuilder();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowContent.append(cell.getStringCellValue()).append("\t");
                            break;
                        case NUMERIC:
                            rowContent.append(cell.getNumericCellValue()).append("\t");
                            break;
                        case BOOLEAN:
                            rowContent.append(cell.getBooleanCellValue()).append("\t");
                            break;
                        case FORMULA:
                            rowContent.append(cell.getCellFormula()).append("\t");
                            break;
                        default:
                            rowContent.append("<Unsupported Cell Type>").append("\t");
                    }
                }
                logger.info(rowContent.toString());
            }
        }
    }

    private void readExcelXlsx(String filePath, String tableName) throws IOException, InvalidFormatException, SQLException {
        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            // Get the first row which contains the column headers
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("No header row found");
            }

            // Get the database connection
            try (Connection connection = getConnection()) {
                // Check if the table exists, and create it if not
                createTableIfNotExists(connection, tableName, headerRow);

                // Prepare SQL insert statement
                String sql = generateInsertSQL(tableName, headerRow);
                PreparedStatement statement = connection.prepareStatement(sql);

                // Iterate through rows and process data
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        // Set values for each cell in the row
                        for (int j = 0; j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            if (cell != null) {
                                switch (cell.getCellType()) {
                                    case STRING:
                                        statement.setString(j + 1, cell.getStringCellValue());
                                        break;
                                    case NUMERIC:
                                        statement.setDouble(j + 1, cell.getNumericCellValue());
                                        break;
                                    case BOOLEAN:
                                        statement.setBoolean(j + 1, cell.getBooleanCellValue());
                                        break;
                                    case FORMULA:
                                        statement.setString(j + 1, cell.getCellFormula());
                                        break;
                                    default:
                                        statement.setString(j + 1, null);
                                }
                            } else {
                                statement.setString(j + 1, null);
                            }
                        }
                        // Execute the insert statement
                        statement.executeUpdate();
                    }
                }
            }
        }
    }

    private void createTableIfNotExists(Connection connection, String tableName, Row headerRow) throws SQLException {
        // Check if the table exists
        try (PreparedStatement checkStatement = connection.prepareStatement("SHOW TABLES LIKE ?")) {
            checkStatement.setString(1, tableName);
            try (ResultSet resultSet = checkStatement.executeQuery()) {
                if (!resultSet.next()) {
                    // Table does not exist, create it
                    StringBuilder createTableSQL = new StringBuilder("CREATE TABLE ").append(tableName).append(" (");
                    for (Cell cell : headerRow) {
                        createTableSQL.append("`").append(cell.getStringCellValue()).append("` VARCHAR(255),");
                    }
                    createTableSQL.deleteCharAt(createTableSQL.length() - 1); // Remove the last comma
                    createTableSQL.append(")");

                    try (Statement createStatement = connection.createStatement()) {
                        createStatement.executeUpdate(createTableSQL.toString());
                    }
                }
            }
        }
    }

    private String generateInsertSQL(String tableName, Row headerRow) {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO `").append(tableName).append("` (");
        for (Cell cell : headerRow) {
            sqlBuilder.append("`").append(cell.getStringCellValue()).append("`").append(",");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the last comma
        sqlBuilder.append(") VALUES (");
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sqlBuilder.append("?,");
        }
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the last comma
        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    private Connection getConnection() throws SQLException {
        // Replace the connection URL, username, and password with your database credentials
        String url = "jdbc:mysql://localhost:3306/mtmbrecord";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
}
