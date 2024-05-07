package AdminFolder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import CustomClassLoader.RoundTxtField;
import DatabaseConnection.MTMBDBCONN;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class FilterFunction {

	DefaultTableModel model;
	private JFrame frame;
	private JTable table;
	private String tableName;
	private final MTMBDBCONN conn = new MTMBDBCONN();

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

	/**
	 * @wbp.parser.entryPoint
	 */
	public FilterFunction(String tableName, DefaultTableModel model) {
		this.tableName = tableName;
		this.model = model;

		initialize();
	}
	
	public void setTableName(String tableName) {
	    this.tableName = tableName;
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
		Font Bold3 = FontLoader.getFont("Bold", 14);

		frame = new JFrame();
	    frame.setBounds(100, 100, 672, 275);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.getContentPane().setLayout(null);
	    frame.setTitle("Muntinlupa Traffic Management Buereau Impounding System");
	    frame.setLocationRelativeTo(null);
	    frame.setUndecorated(true); // Set undecorated before making it visible
	    frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 690, 275);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel CtrlNoLabel = new JLabel("Control No");
		CtrlNoLabel.setBounds(23, 11, 146, 52);
		CtrlNoLabel.setFont(Bold2);
		panel.add(CtrlNoLabel);

		RoundTxtField CtrlNo = new RoundTxtField(32, new Color(0x0B1E33), 1);
		CtrlNo.setForeground(new Color(11, 30, 51));
		CtrlNo.setBackground(new Color(232, 248, 255));
		CtrlNo.setBounds(10, 43, 211, 46);
		CtrlNo.setFont(Bold3);
		panel.add(CtrlNo);
		CtrlNo.setColumns(10);

		RoundTxtField vehicle = new RoundTxtField(32, new Color(0x0B1E33), 1);
		vehicle.setForeground(new Color(11, 30, 51));
		vehicle.setBackground(new Color(232, 248, 255));
		vehicle.setBounds(231, 43, 211, 46);
		vehicle.setFont(Bold3);
		panel.add(vehicle);
		vehicle.setColumns(10);
		
		RoundTxtField PlateNo = new RoundTxtField(32, new Color(11, 30, 51), 1);
		PlateNo.setForeground(new Color(11, 30, 51));
		PlateNo.setFont(Bold3);
		PlateNo.setColumns(10);
		PlateNo.setBackground(new Color(232, 248, 255));
		PlateNo.setBounds(452, 43, 211, 46);
		panel.add(PlateNo);

		RoundTxtField Color = new RoundTxtField(32, new Color(11, 30, 51), 1);
		Color.setForeground(new Color(11, 30, 51));
		Color.setFont(Bold3);
		Color.setColumns(10);
		Color.setBackground(new Color(232, 248, 255));
		Color.setBounds(10, 131, 211, 46);
		panel.add(Color);

		RoundTxtField Date = new RoundTxtField(32, new Color(11, 30, 51), 1);
		Date.setForeground(new Color(11, 30, 51));
		Date.setFont(Bold3);
		Date.setColumns(10);
		Date.setBackground(new Color(232, 248, 255));
		Date.setBounds(231, 131, 211, 46);
		panel.add(Date);

		RoundTxtField Status = new RoundTxtField(32, new Color(11, 30, 51), 1);
		Status.setForeground(new Color(11, 30, 51));
		Status.setFont(Bold3);
		Status.setColumns(10);
		Status.setBackground(new Color(232, 248, 255));
		Status.setBounds(452, 131, 211, 46);
		panel.add(Status);

		RoundButton btnSearch = new RoundButton("Search", 8,  new Color(255, 186, 66));
		btnSearch.setFont(Bold);
		btnSearch.setBounds(463, 216, 200, 38);
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get CtrlNo from the text field
				String ctrlNo = CtrlNo.getText().trim().toLowerCase();
				String vehicleText = vehicle.getText().trim().toLowerCase();
				String plateNo = PlateNo.getText().trim().toLowerCase();
				String color = Color.getText().trim().toLowerCase();
				String date = Date.getText().trim().toLowerCase();
				String status = Status.getText().trim().toLowerCase();
				// Call the fetch function with the CtrlNo and table name
				fetchData(ctrlNo,vehicleText,plateNo,color,date,status, tableName);
			}
		});
		panel.add(btnSearch);

		RoundButton btnCancel = new RoundButton("Cancel", 8,  new Color(241,74,84));
		btnCancel.setFont(Bold);
		btnCancel.setBounds(213, 216, 117, 38);
		panel.add(btnCancel);

		JLabel Type = new JLabel("Vehicle Type");
		Type.setBounds(242, 11, 146, 52);
		Type.setFont(Bold2);
		panel.add(Type);

		JLabel PlateNumber = new JLabel("Plate No.");
		PlateNumber.setBounds(467, 11, 146, 52);
		PlateNumber.setFont(Bold2);
		panel.add(PlateNumber);

		JLabel VehiceColor = new JLabel("Color Vehicle");
		VehiceColor.setBounds(23, 100, 146, 52);
		VehiceColor.setFont(Bold2);
		panel.add(VehiceColor);

		JLabel CarDate = new JLabel("Registered Date");
		CarDate.setBounds(241, 100, 146, 52);
		CarDate.setFont(Bold2);
		panel.add(CarDate);

		JLabel CarStatus = new JLabel("Vehicle Status");
		CarStatus.setBounds(463, 100, 146, 52);
		CarStatus.setFont(Bold2);
		
		panel.add(CarStatus);
		
		RoundButton rndbtnClear = new RoundButton("Clear", 8, new Color(0, 83, 122));
		rndbtnClear.setForeground(new Color(255, 255, 255));
		rndbtnClear.setFont(Bold);
		rndbtnClear.setBounds(340, 216, 117, 38);
		rndbtnClear.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Clear all text fields
		        CtrlNo.setText("");
		        vehicle.setText("");
		        PlateNo.setText("");
		        Color.setText("");
		        Date.setText("");
		        Status.setText("");

		        // Fetch all rows again
		        fetchData("", "", "", "", "", "", tableName);
		    }
		});
		panel.add(rndbtnClear);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}

	private void fetchData(String ctrlNo, String vehicleText, String plateNoText, String colorText, String regDateText, String statusText, String tableName) {
	    try (Connection connection = conn.getConnection(); Statement statement = connection.createStatement()) {
	        // Construct the base query
	        String query = "SELECT * FROM " + tableName + " WHERE 1=1";

	        // Append conditions for filtering based on the provided text fields
	        if (!ctrlNo.isEmpty()) {
	            query += " AND CTRLNo = " + ctrlNo;
	        }
	        if (!vehicleText.isEmpty()) {
	            query += " AND Type LIKE '%" + vehicleText + "%'";
	        }
	        if (!plateNoText.isEmpty()) {
	            query += " AND PlateNo LIKE '%" + plateNoText + "%'";
	        }
	        if (!colorText.isEmpty()) {
	            query += " AND Color LIKE '%" + colorText + "%'";
	        }
	        if (!regDateText.isEmpty()) {
	            query += " AND Date LIKE '%" + regDateText + "%'";
	        }
	        if (!statusText.isEmpty()) {
	            query += " AND Status LIKE '%" + statusText + "%'";
	        }

	        // Execute the query
	        try (ResultSet resultSet = statement.executeQuery(query)) {
	            // Clear existing table data
	            model.setRowCount(0);

	            // Populate table with data from the database
	            while (resultSet.next()) {
	                int id = resultSet.getInt("CTRLNo");
	                String type = resultSet.getString("Type");
	                String plateNo = resultSet.getString("PlateNo");
	                String color = resultSet.getString("Color");
	                String date = resultSet.getString("Date");
	                String status = resultSet.getString("Status");

	                model.addRow(new Object[]{id, type, plateNo, color, date, status});
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }
	}
}
