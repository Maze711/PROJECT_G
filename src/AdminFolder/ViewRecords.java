package AdminFolder;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import DatabaseConnection.MTMBDBArchive;
import AdminFolder.EditPopUp;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class ViewRecords {

	DefaultTableModel model;
	private JFrame frame;
	private JTextField textField;
	private JTable table;
	private String tableName;
	private JScrollPane scrollPane;
	private ViewRecordsFilter ViewRecordsFilter;
	private EditPopUp EditPopUp;
	private final MTMBDBArchive conn = new MTMBDBArchive();

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

	public JFrame getFrame() {
		return frame;
	}

	public void refresh(String tableName) {
		refresher();
	}

	public ViewRecords(String tableName) {
		initialize();
		fetchData(tableName);
		ViewRecordsFilter = new ViewRecordsFilter(this, model);
		ViewRecordsFilter.setTableName(tableName);

		EditPopUp = new EditPopUp(this, model);
		EditPopUp.setTableName(tableName);
	}

	public String getTableName() {
		return tableName;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Fonts
		Font PrimaryFont = FontLoader.getFont("Primary", 30);
		Font SecondaryFont = FontLoader.getFont("Secondary", 15);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font Bold = FontLoader.getFont("Bold", 16);
		Font ExtraBold = FontLoader.getFont("ExtraBold", 32);
		Font Bold2 = FontLoader.getFont("Bold", 16);

		frame = new JFrame();
		frame.setBounds(100, 100, 691, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Muntinlupa Traffic Management Buereau Impounding System");
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 690, 550);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel txtYear = new JLabel("2024 Records");
		txtYear.setFont(ExtraBold);
		txtYear.setBounds(18, 26, 221, 48);
		panel.add(txtYear);

		textField = new JTextField();
		textField.setBounds(92, 88, 152, 25);
		panel.add(textField);
		textField.setColumns(10);

		JLabel txtSearch = new JLabel("Search");
		txtSearch.setFont(Bold);
		txtSearch.setBounds(18, 88, 86, 25);
		panel.add(txtSearch);

		ViewRecordsFilter = new ViewRecordsFilter(this, model); // Initialize FilterFunction
		RoundButton btnFilter = new RoundButton("Filter", 8, Color.decode("#D3D9E0"));
		btnFilter.setFont(Bold);
		btnFilter.setBounds(499, 81, 80, 38);
		btnFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Make the FilterFunction frame visible
				ViewRecordsFilter.getFrame();
			}
		});
		panel.add(btnFilter);

		// Update the ActionListener for btnEdit to pass the table name to EditPopUp
		RoundButton btnEdit = new RoundButton("Edit", 8, Color.decode("#FFBA42"));
		btnEdit.setBounds(589, 81, 80, 38);
		btnEdit.setFont(Bold);
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Show the EditPopUp JFrame with the table name
				EditPopUp.getFrame();
			}
		});
		panel.add(btnEdit);

		RoundButton btnExport = new RoundButton("Export", 8, Color.decode("#0B1E33"));
		btnExport.setForeground(new Color(255, 255, 255));
		btnExport.setFont(Bold);
		btnExport.setBounds(552, 490, 117, 38);
		btnExport.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the table name from the label
				String tableName = txtYear.getText().trim();
				// Format the table name (remove underscores and convert to lowercase)
				tableName = tableName.replaceAll("\\s", "_").toLowerCase();

				if (tableName != null && !tableName.isEmpty()) {
					// Use the table name as part of the file name
					String fileName = "Archive_" + formatTableName(tableName); // Call formatTableName here
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Export Table as Excel");
					fileChooser.setSelectedFile(new File(fileName + ".xlsx"));
					int userSelection = fileChooser.showSaveDialog(frame);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();
						String filePath = fileToSave.getAbsolutePath(); // No need to add file extension, it's already
																		// included
						// Export the table to Excel
						TableExporter.exportToExcel(table, filePath);
						JOptionPane.showMessageDialog(frame, "Table exported successfully to: " + filePath);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a table before exporting.");
				}
			}

			// Method to format table name (remove white spaces and convert to lowercase)
			private String formatTableName(String tableName) {
				return tableName.replaceAll("\\s", "_").toLowerCase();
			}
		});

		panel.add(btnExport);

		RoundButton btnCancel = new RoundButton("Cancel", 8, Color.decode("#F14A54"));
		btnCancel.setForeground(Color.WHITE);
		btnCancel.setFont(Bold);
		btnCancel.setBounds(425, 490, 117, 38);
		panel.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});

		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(10, 124, 670, 358);
		panel.add(tablePanel);
		tablePanel.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(2, 5, 666, 351);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tablePanel.add(scrollPane);

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

		setupSearchFunctionality();
		scrollPane.setViewportView(table);
		fetchData();
	}

	// Fetch data from the specified table name
	private void fetchData(String tableName) {
		if (tableName == null) {
			// Handle null tableName gracefully
			return;
		}
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

				model.addRow(new Object[] { id, type, plateno, color, date, status });
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

	private void refresher() {
		fetchData(tableName); // Fetch data for the specific table
		scrollPane.revalidate();
		scrollPane.repaint();
	}

	private void setupSearchFunctionality() {
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filterTable(textField.getText().trim().toLowerCase());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filterTable(textField.getText().trim().toLowerCase());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				filterTable(textField.getText().trim().toLowerCase());
			}
		});

		// Apply row sorter when setting up search functionality
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
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
}
