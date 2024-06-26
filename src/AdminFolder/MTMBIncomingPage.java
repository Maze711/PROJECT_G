package AdminFolder;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import CustomClassLoader.RoundTxtField;
import DatabaseConnection.MTMBDBCONN;
import AdminFolder.MTMBIncomingPage;

import javax.swing.filechooser.FileNameExtensionFilter;

public class MTMBIncomingPage extends JPanel {

	DefaultTableModel model;
	private JTable table;
	private JTextField searchBar;
	private final MTMBDBCONN conn = new MTMBDBCONN();
	private String username;
	private String tableName;
	private JButton filterButton; // Declare filterButton at class level
	private JButton addButton;
	private JScrollPane scrollPane;
	private JButton CreateTable;
	private Color placeholderColor;

	public void refresh() {
		refresher(); // Update table data from the database
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
		panel.setBounds(-41, 0, 1045, 790);
		panel.setLayout(null);
		add(panel);

		JPanel recordPanel = new JPanel();
		recordPanel.setBounds(42, 11, 736, 768);
		panel.add(recordPanel);
		recordPanel.setLayout(null);

		CreateTable = new RoundButton("Create Table", 16, Color.decode("#FFBA42"));
		CreateTable.setBounds(420, 81, 180, 38);
		CreateTable.setFont(Bold2);
		CreateTable.setForeground(new Color(11, 30, 51));

		CreateTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show a JFrame to input the table name
				String tableName = JOptionPane.showInputDialog(MTMBIncomingPage.this, "Enter table name:");
				if (tableName != null && !tableName.isEmpty()) {
					// Create the table in the database
					createTableInDatabase(tableName);
				}
			}
		});
		recordPanel.add(CreateTable);

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
		placeholderColor = new Color(132, 132, 132);
		searchTable.setForeground(placeholderColor);
		searchTable.setFont(Bold2);
		searchTable.setColumns(10);
		searchTable.setBounds(10, 81, 292, 38);
		recordPanel.add(searchTable);
		searchTable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchTable.getText().equals("Search")) {
					searchTable.setText("");
					searchTable.setForeground(Color.BLACK); // Change text color to black when focused
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchTable.getText().isEmpty()) {
					searchTable.setText("Search");
					searchTable.setForeground(placeholderColor); // Reset text color to placeholder color when focus
																	// lost
				}
			}
		});

		JLabel errorMessage = new JLabel("Table doesn't exist, try again");
		errorMessage.setForeground(Color.RED); // Set color to red
		errorMessage.setFont(Bold2); // Set font
		errorMessage.setBounds(220, 410, 292, 20); // Set bounds
		errorMessage.setVisible(false); // Initially hide the error message
		recordPanel.add(errorMessage); // Add the error message label to the panel

		searchTable.addActionListener(e -> {
		    tableName = searchTable.getText().trim();
		    if (!tableName.isEmpty()) {
		        errorMessage.setVisible(false);
		        if (tableExists(tableName)) {
		            fetchData(tableName);
		            searchBar.setVisible(true);
		            searchTable.setVisible(false);
		            filterButton.setEnabled(true);
		            addButton.setEnabled(true);
		            CreateTable.setVisible(false);
		        } else {
		            errorMessage.setVisible(true);
		            filterButton.setEnabled(false);
		            addButton.setEnabled(false);
		            CreateTable.setVisible(true);
		        }
		    } else {
		        filterButton.setEnabled(false);
		        addButton.setEnabled(false);
		        CreateTable.setVisible(true);
		    }
		});

		searchTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchTable.setText(""); // Clear the search field text when clicked
				errorMessage.setVisible(false); // Hide error message when clicked
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 716, 627);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		recordPanel.add(scrollPane);

		table = new JTable();
		table.setShowHorizontalLines(false);
		model = new DefaultTableModel();
		Object[] column = { "Ctrl No.", "Type", "Plate No.", "Color", "Date", "Status", "Edited By" };
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

		addButton = new RoundButton("ADD +", 16, Color.decode("#FFBA42"));
		addButton.setBounds(510, 81, 90, 38);
		addButton.setFont(Bold2);
		addButton.setForeground(new Color(11, 30, 51));
		addButton.setEnabled(false);
		recordPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchedTableName = searchTable.getText().trim();
				if (!searchedTableName.isEmpty()) {
					// No need to hide the search field here
					fetchData(searchedTableName);
					// Pass the searched table name and the instance of MTMBIncomingPage
					// to the AddVehicle constructor
					AddVehicle addVehicleWindow = new AddVehicle(searchedTableName, MTMBIncomingPage.this);
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
		placeholderColor = new Color(132, 132, 132);
		searchBar.setForeground(placeholderColor);
		searchBar.setBounds(10, 81, 292, 38);
		searchBar.setFont(Bold2);
		searchBar.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (searchBar.getText().equals("Search")) {
					searchBar.setText("");
					searchBar.setForeground(Color.BLACK); // Change text color to black when focused
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (searchBar.getText().isEmpty()) {
					searchBar.setForeground(placeholderColor); // Reset text color to placeholder color when focus lost
				}
			}
		});
		recordPanel.add(searchBar);
		searchBar.setColumns(10);
		searchBar.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_F5) {
		            // Hide unnecessary components and show only "Create Table" button
		            searchBar.setVisible(false);
		            searchTable.setVisible(true);
		            CreateTable.setVisible(true);
		            clearTable();
		        }
		    }
		});

		setupSearchFunctionality();

		fetchData();
	}
	private void clearTable() {
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	    model.setRowCount(0); // Clear all rows
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
				String editby = resultSet.getString("Edited_By");

				model.addRow(new Object[] { id, type, plateno, color, date, status, editby });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Fetch data from the default table
	private void fetchData() {

	}

	// Fetch data from the default table
	private void refresher() {
		fetchData(tableName); // Fetch data for the specific table
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	private void setupSearchFunctionality() {
		searchBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTable(searchBar.getText().trim().toLowerCase());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTable(searchBar.getText().trim().toLowerCase());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTable(searchBar.getText().trim().toLowerCase());
			}
		});
	}

	private void filterTable(String searchText) {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);

		if (!searchText.isEmpty()) {
			RowFilter<TableModel, Integer> filter = RowFilter.regexFilter("(?i)" + Pattern.quote(searchText));
			sorter.setRowFilter(filter);
		} else {
			sorter.setRowFilter(null);
		}
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

	private void createTableInDatabase(String tableName) {
		// SQL query to create the table with specified columns
		String query = "CREATE TABLE " + tableName + " (" + "CtrlNo INT(11) PRIMARY KEY AUTO_INCREMENT,"
				+ "Type VARCHAR(255)," + "PlateNo VARCHAR(255)," + "Color VARCHAR(255)," + "Date VARCHAR(255),"
				+ "Status VARCHAR(255)," + "edited_by VARCHAR(225)" + ")";

		try (Connection connection = conn.getConnection(); Statement statement = connection.createStatement()) {
			// Execute the query to create the table
			statement.executeUpdate(query);
			JOptionPane.showMessageDialog(MTMBIncomingPage.this, "Table created successfully!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(MTMBIncomingPage.this, "Error creating table: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
