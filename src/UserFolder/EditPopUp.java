package UserFolder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import DatabaseConnection.MTMBDBArchive;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditPopUp extends JFrame {

	private ViewRecords viewRecords;
	DefaultTableModel model;
	private JFrame frame;
	private JComboBox<String> ctrlNoComboBox;
	private JTextField statusTextField;
	private JTextField updateInto;
	private JTextField UPINTO;
	public String tableName;
	private JButton btnNewButton;
	private final MTMBDBArchive conn = new MTMBDBArchive();
	private JTextField UpdateStatus;
	private JLabel label;

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
		frame.setVisible(true);
		return frame;

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public EditPopUp(ViewRecords viewRecords, DefaultTableModel model) {
		this.viewRecords = viewRecords; // Store the reference to ViewRecords
		this.tableName = viewRecords.getTableName();
		this.model = model;
		initialize();
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void initialize() {

		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);
		Font Bold = FontLoader.getFont("Bold", 28);
		Font Bold2 = FontLoader.getFont("Bold", 16);

		frame = new JFrame();
		frame.setTitle("Edit Status");
		frame.setSize(341, 244);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(null);

		// Initialize components
		ctrlNoComboBox = new JComboBox<>();
		ctrlNoComboBox.setBounds(150, 27, 160, 22);

		statusTextField = new JTextField(10);
		statusTextField.setBounds(150, 67, 160, 35);
		statusTextField.setFont(Bold2);
		statusTextField.setEditable(false);

		// Populate comboBox with CtrlNo from the table
		for (int i = 0; i < model.getRowCount(); i++) {
			ctrlNoComboBox.addItem(model.getValueAt(i, 0).toString());
		}

		// Add action listener to comboBox to update statusTextField
		ctrlNoComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedCtrlNo = (String) ctrlNoComboBox.getSelectedItem();
				String status = getStatusFromTable(selectedCtrlNo, model);
				statusTextField.setText(status);
			}
		});

		// Add components to the panel
		panel.add(ctrlNoComboBox);
		panel.add(statusTextField);

		JLabel lblControlNo = new JLabel("Control No:");
		lblControlNo.setBounds(10, 30, 99, 14);
		lblControlNo.setFont(Bold2);
		panel.add(lblControlNo);

		JLabel label_1 = new JLabel("Status:");
		label_1.setBounds(10, 68, 99, 33);
		label_1.setFont(Bold2);
		panel.add(label_1);

		// Add the panel to the frame
		frame.getContentPane().add(panel);

		UpdateStatus = new JTextField(10);
		UpdateStatus.setFont(Bold2);
		UpdateStatus.setBounds(150, 113, 160, 35);
		panel.add(UpdateStatus);

		label = new JLabel("Update");
		label.setFont(Bold2);
		label.setBounds(10, 115, 99, 33);
		panel.add(label);

		btnNewButton = new RoundButton("UPDATE", 10, Color.decode("#FFBA42"));
		btnNewButton.setBounds(10, 157, 300, 37);
		btnNewButton.setFont(Bold2);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the selected CtrlNo and new status
				String selectedCtrlNo = (String) ctrlNoComboBox.getSelectedItem();
				String newStatus = UpdateStatus.getText();

				// Check if CtrlNo and new status are not null or empty
				if (selectedCtrlNo != null && !selectedCtrlNo.isEmpty() && newStatus != null && !newStatus.isEmpty()) {
					// Execute SQL UPDATE statement
					try (Connection connection = conn.getConnection();
							Statement statement = connection.createStatement()) {
						// Construct the SQL UPDATE statement
						String sql = "UPDATE " + tableName + " SET status = '" + newStatus + "' WHERE ctrlNo = '"
								+ selectedCtrlNo + "'";
						// Execute the SQL statement
						statement.executeUpdate(sql);
						// Show success message
						JOptionPane.showMessageDialog(frame, "Status updated successfully.");

						viewRecords.refresh(tableName);
					} catch (SQLException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(frame, "Failed to update status: " + ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Please select a CtrlNo and enter a new status.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		panel.add(btnNewButton);
	}

	// Method to fetch status from the table based on CtrlNo
	private String getStatusFromTable(String ctrlNo, DefaultTableModel model) {
		for (int i = 0; i < model.getRowCount(); i++) {
			if (model.getValueAt(i, 0).toString().equals(ctrlNo)) {
				return model.getValueAt(i, 5).toString(); // Assuming status is at index 5
			}
		}
		return null;
	}
}
