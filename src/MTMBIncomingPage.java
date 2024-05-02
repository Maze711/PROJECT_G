import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Cursor;

public class MTMBIncomingPage extends JPanel {

    DefaultTableModel model;
    private JTable table;
    private JTextField searchBar;
    private final MTMBDBCONN conn = new MTMBDBCONN();
    private JButton searchTableButton;
    private String tableName;
    private JButton filterButton; // Declare filterButton at class level

    public void refresh() {
        fetchData(); // Update table data from the database
    }

    public MTMBIncomingPage() {
        initialize();
    }

    private void initialize() {

        // CUSTOM FONT FAMILY STYLE
        Font PrimaryFont = FontLoader.getFont("Primary", 64);
        Font SecondaryFont = FontLoader.getFont("Secondary", 24);
        Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
        Font SemiB = FontLoader.getFont("SemiB", 24);
        Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);
        Font Bold = FontLoader.getFont("Bold", 28);
        Font Bold2 = FontLoader.getFont("Bold", 16);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(-41, 0, 1069, 768);
        panel.setLayout(null);
        add(panel);

        JPanel recordPanel = new JPanel();
        recordPanel.setBounds(42, 11, 736, 768);
        panel.add(recordPanel);
        recordPanel.setLayout(null);

        // Header
        JPanel insideRecordPanel = new JPanel();
        insideRecordPanel.setBounds(0, 0, 736, 70);
        recordPanel.add(insideRecordPanel);
        insideRecordPanel.setLayout(null);

        JLabel recordLabel = new JLabel("Incoming");
        recordLabel.setBounds(30, 23, 189, 36);
        recordLabel.setFont(PrimaryEBFont);
        insideRecordPanel.add(recordLabel);

        // Create search table text field
        RoundTxtField searchTable = new RoundTxtField(18, new Color(132, 132, 132), 1);
        searchTable.setText("Search Table");
        searchTable.setFont(Bold2);
        searchTable.setColumns(10);
        searchTable.setBounds(220, 370, 292, 38);
        recordPanel.add(searchTable);
        searchTable.setColumns(10);

        JLabel errorMessage = new JLabel("Table doesn't exist, try again");
        errorMessage.setForeground(Color.RED); // Set color to red
        errorMessage.setFont(Bold2); // Set font
        errorMessage.setBounds(220, 410, 292, 20); // Set bounds
        errorMessage.setVisible(false); // Initially hide the error message
        recordPanel.add(errorMessage); // Add the error message label to the panel

        searchTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableName = searchTable.getText().trim(); // Update the class field
                if (!tableName.isEmpty()) {
                    errorMessage.setVisible(false); // Hide error message initially
                    if (tableExists(tableName)) {
                        fetchData(tableName);
                        // You can remove or comment out the line below to keep the searchTable visible
                        searchTable.setVisible(false); // Hide the search field after searching for a table name
                        filterButton.setEnabled(true); // Enable filter button
                    } else {
                        errorMessage.setVisible(true); // Show error message if table doesn't exist
                        filterButton.setEnabled(false); // Disable filter button
                    }
                } else {
                    filterButton.setEnabled(false); // Disable filter button if search field is empty
                }
            }
        });

        searchTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchTable.setText(""); // Clear the search field text when clicked
                errorMessage.setVisible(false); // Hide error message when clicked
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 130, 716, 627);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        recordPanel.add(scrollPane);

        table = new JTable();
        table.setShowHorizontalLines(false);
        model = new DefaultTableModel();
        Object[] column = { "Ctrl No.", "Type", "Plate No.", "Color", "Date", "Status" };
        model.setColumnIdentifiers(column);
        table.setModel(model);
        table.setEnabled(false);
        table.setFont(Bold2);
        table.setForeground(new Color(101, 95, 95));
        table.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < column.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Add a MouseWheelListener to both the table and the scroll pane
        MouseWheelListener mouseWheelListener = new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                // Increase scrolling speed by a factor of 10
                verticalScrollBar
                        .setValue(verticalScrollBar.getValue() + (verticalScrollBar.getUnitIncrement() * notches * 10));
            }
        };
        table.addMouseWheelListener(mouseWheelListener);
        scrollPane.addMouseWheelListener(mouseWheelListener);

        JTableHeader header = table.getTableHeader();
        header.setFont(Bold2);
        header.setBackground(new Color(11, 30, 51));
        header.setForeground(Color.WHITE);
        table.setRowHeight(50);
        table.setFocusable(true);
        table.setTableHeader(header);

        // Add the table to the scroll pane
        scrollPane.setViewportView(table);

        filterButton = new RoundButton("Filter", 16, Color.decode("#D3D9E0"));
        filterButton.setBounds(420, 81, 80, 38);
        filterButton.setFont(Bold2);
        filterButton.setForeground(new Color(11, 30, 51));
        filterButton.setEnabled(false); // Initially disable the filter button
        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                filterButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                filterButton.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (filterButton.isEnabled()) { // Check if the button is enabled
                    FilterFunction filterFunction = new FilterFunction(tableName, model);
                    filterFunction.getFrame().setVisible(true);
                }
            }
        });
        recordPanel.add(filterButton);

        JButton addButton = new RoundButton("ADD +", 16, Color.decode("#FFBA42"));
        addButton.setBounds(510, 81, 90, 38);
        addButton.setFont(Bold2);
        addButton.setForeground(new Color(11, 30, 51));
        recordPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedTableName = searchTable.getText().trim();
                if (!searchedTableName.isEmpty()) {
                    // No need to hide the search field here
                    fetchData(searchedTableName);
                    // Pass the searched table name to the AddVehicle constructor
                    AddVehicle addVehicleWindow = new AddVehicle(searchedTableName);
                    addVehicleWindow.showFrame();
                }
            }
        });


        JButton importButton = new RoundButton("Import", 16, Color.decode("#00537A"));
        importButton.setBounds(609, 81, 117, 38);
        importButton.setFont(Bold2);
        importButton.setForeground(Color.WHITE);
        recordPanel.add(importButton);

        importButton.addActionListener(e -> {
            // Prompt user to enter table name
            String tableName = JOptionPane.showInputDialog(this, "Enter table name:");
            if (tableName != null && !tableName.isEmpty()) {
                // Browse file
                JFileChooser fileChooser = new JFileChooser();

                // Set file filter to show only Excel files (*.xlsx)
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();

                    SwingUtilities.invokeLater(() -> {
                        MTMBImporter importer = new MTMBImporter();
                        // Call importExcelFile with additional parameters including the current frame
                        // and this instance
                        importer.importExcelFile(filePath, tableName, this, this).thenRun(() -> {
                            System.out.println("Import completed successfully!");
                            // Call the refresh method after successful import
                            refresh();
                        }).exceptionally(ex -> {
                            ex.printStackTrace();
                            return null;
                        });
                    });
                }
            }
        });

        // Only add importButton to recordPanel, not to the frame
        recordPanel.add(importButton);

        searchBar = new RoundTxtField(18, new Color(132, 132, 132), 1);
        searchBar.setText("Search");
        searchBar.setBounds(10, 81, 292, 38);
        searchBar.setFont(Bold2);
        recordPanel.add(searchBar);
        searchBar.setColumns(10);

        fetchData();
    }

    // Fetch data from the specified table name
    private void fetchData(String tableName) {
        try (Connection connection = conn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName)) {

            // Clear existing table data
            model.setRowCount(0);

            // Populate table with data from the database
            while (resultSet.next()) {
                int id = resultSet.getInt("CTRLNo");
                String type = resultSet.getString("Type");
                String plateno = resultSet.getString("PlateNo");
                String color = resultSet.getString("Color");
                String date = resultSet.getString("Date");
                String status = resultSet.getString("Status");

                model.addRow(new Object[]{id, type, plateno, color, date, status});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch data from the default table
    private void fetchData() {
        // For the initial fetch, you might want to fetch data from a default table or
        // display an empty table.
        // Since the behavior isn't explicitly specified, you can adjust this method as
        // needed.
    }

    private boolean tableExists(String tableName) {
        try (Connection connection = conn.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE '" + tableName + "'")) {

            return resultSet.next(); // If resultSet.next() returns true, it means the table exists
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // In case of any exception, return false
        }
    }
}
