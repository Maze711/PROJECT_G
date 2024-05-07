package UserFolder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import CustomClassLoader.RoundTxtField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddVehicle {

	private JFrame frame;
    private JTextField CtrlNo;
    private JTextField Date;
    private JTextField Type;
    private JTextField colorField; // Changed variable name to avoid conflict
    private JTextField Plate;
    private JTextField Status;
    private String tableName; // Add a field to store the table name
    private MTMBIncomingPage incomingPage; // Add a field to store the instance of MTMBIncomingPage

    public AddVehicle(String tableName, MTMBIncomingPage incomingPage) {
        this.tableName = tableName; // Store the table name
        this.incomingPage = incomingPage; // Store the instance of MTMBIncomingPage
        initialize();
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Fonts
		Font PrimaryFont = FontLoader.getFont("Primary", 30);
		Font SecondaryFont = FontLoader.getFont("Secondary", 15);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 16);
		Font ExtraBold = FontLoader.getFont("ExtraBold", 24);
		Font ExtraBold2 = FontLoader.getFont("ExtraBold", 16);

		frame = new JFrame();
		frame.setBounds(100, 100, 451, 448);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Muntinlupa Traffic Management Buereau Impounding System");
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 449);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel txtAddVehicle = new JLabel("Add Vehicle");
		txtAddVehicle.setFont(ExtraBold);
		txtAddVehicle.setBounds(20, 36, 154, 36);
		panel.add(txtAddVehicle);

		JLabel txtCrtlNo = new JLabel("Control No.");
		txtCrtlNo.setFont(SemiB);
		txtCrtlNo.setBounds(20, 95, 94, 24);
		panel.add(txtCrtlNo);

		JLabel txtDate = new JLabel("Date");
		txtDate.setFont(SemiB);
		txtDate.setBounds(272, 95, 94, 24);
		panel.add(txtDate);

		JLabel txtType = new JLabel("Type of Vehicle");
		txtType.setFont(SemiB);
		txtType.setBounds(20, 185, 198, 24);
		panel.add(txtType);

		JLabel txtColor = new JLabel("Color");
		txtColor.setFont(SemiB);
		txtColor.setBounds(228, 185, 94, 24);
		panel.add(txtColor);

		JLabel txtPlateNo = new JLabel("Plate No.");
		txtPlateNo.setFont(SemiB);
		txtPlateNo.setBounds(20, 277, 94, 24);
		panel.add(txtPlateNo);

		JLabel txtStatus = new JLabel("Status");
		txtStatus.setFont(SemiB);
		txtStatus.setBounds(228, 277, 94, 24);
		panel.add(txtStatus);

		RoundButton btnCancel = new RoundButton("Cancel", 8, Color.decode("#FFBA42"));
		btnCancel.setBounds(210, 372, 112, 40);
		btnCancel.setFont(ExtraBold2);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTMBIncomingPage window = new MTMBIncomingPage();
				frame.dispose();
			}
		});
		panel.add(btnCancel);

		RoundButton btnCreate = new RoundButton("Create", 8, Color.decode("#0B1E33"));
		btnCreate.setForeground(new Color(255, 255, 255));
		btnCreate.setFont(ExtraBold2);
		// ActionListener for the "Create" button
		btnCreate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Extract data from text fields and save to database
		        String controlNo = CtrlNo.getText();
		        String date = Date.getText();
		        String vehicleType = Type.getText();
		        String color = colorField.getText();
		        String plateNo = Plate.getText();
		        String status = Status.getText();

		        // Save data to database
		        AddVehicleBE backend = new AddVehicleBE();
		        boolean success = backend.saveData(tableName, controlNo, date, vehicleType, color, plateNo, status);
		        if (success) {
		            System.out.println("Data saved successfully.");

		            // Refresh MTMBIncomingPage
		            refreshMTMBIncomingPage();
		        } else {
		            System.out.println("Failed to save data.");
		        }
		    }
		});

		btnCreate.setBounds(328, 372, 112, 40);
		panel.add(btnCreate);

		CtrlNo = new RoundTxtField(8, new Color(0x0B1E33), 3);
		CtrlNo.setBounds(20, 128, 232, 46);
		panel.add(CtrlNo);
		CtrlNo.setColumns(10);

		Date = new RoundTxtField(8, new Color(0x0B1E33), 3);
		Date.setColumns(10);
		Date.setBounds(258, 128, 182, 46);
		panel.add(Date);

		Type = new RoundTxtField(8, new Color(0x0B1E33), 3);
		Type.setColumns(10);
		Type.setBounds(20, 220, 198, 46);
		panel.add(Type);

		colorField = new RoundTxtField(8, new Color(0x0B1E33), 3); // Changed variable name
		colorField.setColumns(10);
		colorField.setBounds(228, 220, 212, 46); // Changed variable name
		panel.add(colorField); // Changed variable name

		Plate = new RoundTxtField(8, new Color(0x0B1E33), 3);
		Plate.setColumns(10);
		Plate.setBounds(20, 312, 198, 46);
		panel.add(Plate);

		Status = new RoundTxtField(8, new Color(0x0B1E33), 3);
		Status.setColumns(10);
		Status.setBounds(228, 312, 212, 46);
		panel.add(Status);

	}
	
	public void showFrame() {
        frame.setVisible(true);
    }

	private void refreshMTMBIncomingPage() {
		// Call the refresh method on the existing instance of MTMBIncomingPage
        incomingPage.refresh();
	}

}
