package UserFolder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import CustomClassLoader.RoundTxtField;
import DatabaseConnection.MTMBDBCONN;

public class MTMBReleasePop {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JComboBox<String> comboBox;
    private JComboBox<String> comboBox1;

    private final MTMBDBCONN conn = new MTMBDBCONN();
    private final MTMBReleasingPage release = new MTMBReleasingPage(this.username);
    private String tableName; // Add a field to store the table name
    private MTMBReleasingPage releasingPage;; // Add a field to store the table name
    private String username;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showFrame() {
        frame.setVisible(true);

    }

    public MTMBReleasePop(String tableName, MTMBReleasingPage releasingPage, String username) {
        this.tableName = tableName; // Store the table name
        this.releasingPage = releasingPage; // Store the instance of MTMBReleasingPage
        this.username = username; // Store the username
        initialize();
    }



    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        // FONTS
        Font PrimaryFont = FontLoader.getFont("Primary", 20);
        Font SemiB = FontLoader.getFont("SemiB", 13);
        Font Bold = FontLoader.getFont("PrimaryEB32", 17);
        Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 15);
        Font SecondaryFont = FontLoader.getFont("Secondary", 15);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Release Vehicle");
        lblNewLabel.setFont(PrimaryFont);
        lblNewLabel.setBounds(10, 32, 174, 31);
        frame.getContentPane().add(lblNewLabel);

        // Add comboBox for dropdown
        comboBox = new JComboBox<>();
        comboBox.setBounds(10, 130, 272, 39);
        comboBox.setFont(SecondaryFont);
        frame.getContentPane().add(comboBox);

        JButton btnNewButton_1 = new RoundButton("Search", 15, Color.decode("#FFFFFF"));
        btnNewButton_1.setForeground(new Color(11, 30, 51));
        btnNewButton_1.setBackground(new Color(255, 255, 255));
        btnNewButton_1.setBounds(292, 95, 101, 39);
        btnNewButton_1.setFont(Bold);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchButtonActionPerformed(e);
            }
        });
        frame.getContentPane().add(btnNewButton_1);

        JButton btnNewButton_3 = new RoundButton("Save", 10, Color.decode("#0B1E33"));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText();
                String status = (String) comboBox1.getSelectedItem(); // Get selected status from comboBox

                updateStatusInDatabase(input, status, username); // Update status in the database
                releasingPage.refresh(tableName); // Refresh the releasing page
            }
        });

        btnNewButton_3.setForeground(new Color(255, 255, 255));
        btnNewButton_3.setBounds(325, 236, 101, 39);
        btnNewButton_3.setFont(Bold);
        frame.getContentPane().add(btnNewButton_3);

        JButton btnNewButton_4 = new RoundButton("Cancel", 10, (new Color(255, 186, 66)));
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                frame.setVisible(false);
            }
        });
        btnNewButton_4.setForeground(new Color(11, 30, 51));
        btnNewButton_4.setBackground(new Color(255, 186, 66));
        btnNewButton_4.setBounds(208, 236, 101, 39);
        btnNewButton_4.setFont(Bold);
        frame.getContentPane().add(btnNewButton_4);

        textField = new RoundTxtField(10, new Color(0x0B1E33), 3);
        textField.setForeground(new Color(11, 30, 51));
        textField.setBackground(new Color(211, 211, 211));
        textField.setBounds(10, 95, 272, 39);
        textField.setFont(SecondaryFont);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        comboBox1 = new JComboBox<>();
        comboBox1.setBounds(10, 187, 350, 39);
        comboBox1.setFont(SecondaryFont);
        frame.getContentPane().add(comboBox1);

        // Add "Released" and "Impounded" options
        comboBox1.addItem("Released");
        comboBox1.addItem("Impounded");

        JLabel lblNewLabel_1 = new JLabel("Control No.");
        lblNewLabel_1.setBounds(10, 73, 94, 16);
        lblNewLabel_1.setFont(SemiB);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Change Status");
        lblNewLabel_2.setBounds(10, 136, 150, 21);
        lblNewLabel_2.setFont(PrimaryEB48Font);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Status");
        lblNewLabel_3.setBounds(10, 156, 134, 31);
        lblNewLabel_3.setFont(SemiB);
        frame.getContentPane().add(lblNewLabel_3);

        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateComboBox();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateComboBox();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateComboBox();
            }
        });

    }

    protected void dispose() {
        // TODO Auto-generated method stub

    }

    private void updateComboBox() {
        String input = textField.getText();
        if (!input.isEmpty()) {
            fetchDataFromDatabase(input, tableName); // Pass the table name
            comboBox.setVisible(true); // Show comboBox when there's input
        } else {
            comboBox.removeAllItems(); // Clear comboBox if textField is empty
            comboBox.setVisible(false); // Hide comboBox when there's no input
        }
    }

    private void fetchDataFromDatabase(String input, String tableName) { // Update method signature
        String query = "SELECT CtrlNo FROM " + tableName + " WHERE CtrlNo LIKE ?"; // Use the provided table name

        try (Connection conn = this.conn.getConnection();
                PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, "%" + input + "%");
            ResultSet resultSet = statement.executeQuery();

            comboBox.removeAllItems(); // Clear existing items

            while (resultSet.next()) {
                String ctrlNo = resultSet.getString("CtrlNo");
                comboBox.addItem(ctrlNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchButtonActionPerformed(ActionEvent e) {
        String input = (String) comboBox.getSelectedItem();
        textField.setText(input);

        String status = fetchStatusFromDatabase(input, tableName); // Pass the table name
        comboBox1.setSelectedItem(status); // Set selected item in comboBox1

        comboBox.setVisible(false);
    }

    private String fetchStatusFromDatabase(String ctrlNo, String tableName) { // Update method signature
        String status = "";
        String query = "SELECT Status FROM " + tableName + " WHERE CtrlNo = ?"; // Use the provided table name

        try (Connection conn = this.conn.getConnection();
                PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, ctrlNo);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                status = resultSet.getString("Status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }

    private void updateStatusInDatabase(String ctrlNo, String status, String username) {
        String query = "UPDATE " + tableName + " SET Status = ?, edited_by = ? WHERE CtrlNo = ?"; // Include edited_by field

        try (Connection conn = this.conn.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {

            statement.setString(1, status);
            statement.setString(2, username); // Set the username for edited_by field
            statement.setString(3, ctrlNo);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Status of Control No. " + ctrlNo + " is changed by " + username);
            } else {
                System.out.println("No rows were updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
