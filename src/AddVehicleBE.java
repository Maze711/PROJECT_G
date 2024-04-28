import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class AddVehicleBE {

    private final MTMBDBCONN conn = new MTMBDBCONN();
    public boolean saveData(String controlNo, String date, String vehicleType, String color, String plateNo, String status) {
        Connection connection = null;
        PreparedStatement checkStatement = null;
        PreparedStatement insertStatement = null;
        ResultSet resultSet = null;
        try {
            connection = conn.getConnection(); // Get database connection

            // Check if the record already exists
            String checkSql = "SELECT * FROM 2024mtmbrecord WHERE CtrlNo = ?";
            checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, controlNo);
            resultSet = checkStatement.executeQuery();

            if (resultSet.next()) {
                // Record already exists, show error message
                JOptionPane.showMessageDialog(null, "Record with Control No. " + controlNo + " already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                // Record does not exist, perform insert
                String insertSql = "INSERT INTO 2024mtmbrecord (CtrlNo, Type, PlateNo, Color, Date, Status) VALUES (?, ?, ?, ?, ?, ?)";
                insertStatement = connection.prepareStatement(insertSql);
                insertStatement.setString(1, controlNo);
                insertStatement.setString(2, vehicleType);
                insertStatement.setString(3, plateNo);
                insertStatement.setString(4, color);
                insertStatement.setString(5, date);
                insertStatement.setString(6, status);
                int rowsInserted = insertStatement.executeUpdate();
                if (rowsInserted > 0) {
                    // Insert successful
                    JOptionPane.showMessageDialog(null, "Data saved successfully.");
                    return true;
                } else {
                    // Insert failed
                    JOptionPane.showMessageDialog(null, "Failed to save data.", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to save data. Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // Return false in case of any exception or failure
        } finally {
            // Close resources in the finally block
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (checkStatement != null) {
                try {
                    checkStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (insertStatement != null) {
                try {
                    insertStatement.close();
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
}
