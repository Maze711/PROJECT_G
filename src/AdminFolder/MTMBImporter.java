package AdminFolder;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
    private static final String EDITED_BY_COLUMN_NAME = "edited_by";

    // Define the expected column names
    private static final Set<String> EXPECTED_COLUMN_NAMES = new HashSet<>(
            Arrays.asList("CtrlNo", "Type", "PlateNo", "Color", "Date", "Status"));

    public CompletableFuture<Void> importExcelFile(String filePath, String tableName, MTMBIncomingPage mtmbIncomingPage,
            MTMBIncomingPage incomingPage) {
        return CompletableFuture.runAsync(() -> {
            try {
                // Check file type
                if (filePath.endsWith(".xlsx")) {
                    // Log Excel file content
                    logExcelFileContent(filePath);
                    // Proceed with reading and importing
                    readExcelXlsx(filePath, tableName, mtmbIncomingPage, incomingPage);
                } else {
                    throw new IllegalArgumentException("Unsupported file type");
                }
            } catch (IOException | InvalidFormatException e) {
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

    private void showImportSuccessMessage(MTMBIncomingPage mtmbIncomingPage, MTMBIncomingPage incomingPage) {
        try {
            JOptionPane.showMessageDialog(mtmbIncomingPage, "Import Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            // Refresh the MTMBIncomingPage
            incomingPage.refresh();
        } catch (Exception e) {
            logger.error("Error refreshing MTMBIncomingPage after import: {}", e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    private void readExcelXlsx(String filePath, String tableName, MTMBIncomingPage mtmbIncomingPage, MTMBIncomingPage incomingPage)
            throws IOException, InvalidFormatException {
        Connection connection = null;
        PreparedStatement statement = null;

        try (Workbook workbook = new XSSFWorkbook(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0);

            // Get the first row which contains the column headers
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("No header row found");
            }

            // Validate column names
            validateColumnNames(headerRow);

            // Get the database connection
            connection = getConnection();

            // Check if the table exists, and create it if not
            createTableIfNotExists(connection, tableName, headerRow);

            // Prepare SQL insert statement
            String sql = generateInsertSQL(tableName, headerRow);
            statement = connection.prepareStatement(sql);

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
                                double numericValue = cell.getNumericCellValue();
                                // Check if the numeric value is an integer
                                if (numericValue == Math.floor(numericValue)) {
                                    statement.setInt(j + 1, (int) numericValue); // Cast to int if it's an integer
                                } else {
                                    statement.setDouble(j + 1, numericValue); // Set double otherwise
                                }
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
                    // Set empty value for the edited_by column
                    statement.setString(headerRow.getLastCellNum() + 1, "");
                    try {
                        statement.executeUpdate();
                    } catch (SQLIntegrityConstraintViolationException e) {
                        // Handle duplicate entry error
                        JFrame frame = new JFrame("Duplicate Entry Error");
                        JOptionPane.showMessageDialog(frame, "Duplicate entry encountered: " + e.getMessage(),
                                "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            // Import completed successfully, show message and refresh page
            showImportSuccessMessage(mtmbIncomingPage, incomingPage);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        } finally {
            // Close statement and connection in the finally block to ensure proper cleanup
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
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
                        String columnName = cell.getStringCellValue();
                        createTableSQL.append("`").append(columnName).append("` ");
                        if (columnName.equals("CtrlNo")) {
                            createTableSQL.append("INT PRIMARY KEY"); // Assuming CtrlNo is an integer
                        } else {
                            createTableSQL.append("VARCHAR(255)"); // Default to VARCHAR(255) for other columns
                        }
                        createTableSQL.append(",");
                    }
                    // Add the edited_by column
                    createTableSQL.append("`").append(EDITED_BY_COLUMN_NAME).append("` VARCHAR(255),");
                    createTableSQL.deleteCharAt(createTableSQL.length() - 1);
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
        // Add the edited_by column to the INSERT statement
        sqlBuilder.append("`").append(EDITED_BY_COLUMN_NAME).append("`").append(",");
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(") VALUES (");
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            sqlBuilder.append("?,");
        }
        // Add placeholder for the edited_by column
        sqlBuilder.append("?,");
        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1); // Remove the last comma
        sqlBuilder.append(")");
        return sqlBuilder.toString();
    }

    private void validateColumnNames(Row headerRow) {
        Set<String> actualColumnNames = new HashSet<>();
        for (Cell cell : headerRow) {
            actualColumnNames.add(cell.getStringCellValue().trim());
        }

        // Check if all expected column names are present
        for (String expectedColumnName : EXPECTED_COLUMN_NAMES) {
            if (!actualColumnNames.contains(expectedColumnName)) {
                JFrame frame = new JFrame("Column Name Validation");
                JOptionPane.showMessageDialog(frame, expectedColumnName + " column is missing");
            }
        }
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mtmbrecord";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }

}
