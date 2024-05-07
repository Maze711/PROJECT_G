package AdminFolder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import CustomClassLoader.FontLoader;
import CustomClassLoader.RoundButton;
import DatabaseConnection.MTMBDBArchive;
import DatabaseConnection.MTMBDBCONN;

public class CreateNew {

	public JFrame frame;
	private JTextField txtInputYear;
	private JComboBox<String> tableDropdown;
	private final MTMBDBCONN conn = new MTMBDBCONN();
	private final MTMBDBArchive archiveCon = new MTMBDBArchive();

	private MTMBRecordPage recordPage;


	public void showFrame() {
		frame.setVisible(true);
	}

	// Constructor that accepts an instance of MTMBRecordPage
    public CreateNew(MTMBRecordPage recordPage) {
        this.recordPage = recordPage;
        initialize();
    }


	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Muntinlupa Traffic Management Buereau Impounding System");
		frame.setLocationRelativeTo(null);
		frame.setBounds(100, 100, 454, 250);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);

		Font PrimaryFont = FontLoader.getFont("Primary", 30);
		Font SecondaryFont = FontLoader.getFont("Secondary", 15);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font ExtraBold = FontLoader.getFont("ExtraBold", 24);
		Font ExtraBold2 = FontLoader.getFont("ExtraBold", 12);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 454, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel txtCreate = new JLabel("Create New Record");
		txtCreate.setBackground(new Color(16, 10, 14));
		txtCreate.setBounds(46, 55, 362, 44);
		txtCreate.setFont(ExtraBold);
		panel.add(txtCreate);

		txtInputYear = new JTextField();
		txtInputYear.setBounds(46, 110, 362, 46);
		txtInputYear.setFont(ExtraBold2);
		panel.add(txtInputYear);
		txtInputYear.setColumns(10);

		tableDropdown = new JComboBox<>();
		tableDropdown.setBounds(46, 160, 362, 40);
		panel.add(tableDropdown);

		populateTableDropdown();

		tableDropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedTable = (String) tableDropdown.getSelectedItem();
				if (selectedTable != null) {
					System.out.println(selectedTable);
					// Display the selected item in the text field
					txtInputYear.setText(selectedTable);
					// Hide the combo box
					tableDropdown.setVisible(false);
					// Show the text field
					txtInputYear.setVisible(true);
				}
			}
		});

		RoundButton btnCancel = new RoundButton("Cancel", 8, Color.decode("#FFBA42"));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTMBRecordPage window = new MTMBRecordPage();
				frame.dispose();
			}
		});

		btnCancel.setBounds(174, 175, 112, 40);
		btnCancel.setFont(ExtraBold2);
		panel.add(btnCancel);

		RoundButton btnCreate = new RoundButton("Create", 8, Color.decode("#0B1E33"));
		// Inside btnCreate ActionListener
		 btnCreate.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String inputYear = txtInputYear.getText();
	                if (!inputYear.isEmpty()) {
	                    System.out.println("Input Year: " + inputYear);
	                    // Store the input year in the archive
	                    archiveCon.copyTable(inputYear); // Assuming copyTable method copies the table structure and data
	                    // Clear the text field after storing
	                    txtInputYear.setText("");
	                    // Hide the text field
	                    txtInputYear.setVisible(false);
	                    // Show the combo box again after storing
	                    tableDropdown.setVisible(true);
	                    // Refresh or refetch the tables in MTMBRecordPage
	                    recordPage.refreshTables();
	                }
	            }
	        });
		btnCreate.setForeground(new Color(255, 255, 255));
		btnCreate.setBackground(new Color(255, 255, 255));
		btnCreate.setBounds(296, 175, 112, 40);
		btnCreate.setFont(ExtraBold2);
		panel.add(btnCreate);
	}

	private void populateTableDropdown() {
		String[] tableNames = conn.getTableNames();
		for (String tableName : tableNames) {
			tableDropdown.addItem(tableName);
		}

		txtInputYear.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        updateVisibility();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        updateVisibility();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        // Plain text components do not fire these events
		    }

		    private void updateVisibility() {
		        // If the text field is empty, show the combo box
		        if (txtInputYear.getText().isEmpty()) {
		            tableDropdown.setVisible(true);
		        }
		    }
		});

	}
}
