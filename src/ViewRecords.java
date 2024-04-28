import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class ViewRecords {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewRecords window = new ViewRecords();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ViewRecords() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//Fonts
		Font PrimaryFont = FontLoader.getFont("Primary", 30);
		Font SecondaryFont = FontLoader.getFont("Secondary", 15);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font Bold = FontLoader.getFont("Bold", 16);
		Font ExtraBold = FontLoader.getFont("ExtraBold",32);
		
		
		
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
		
		RoundButton btnFilter = new RoundButton("Filter",8,Color.decode("#D3D9E0"));
		btnFilter.setFont(Bold);
		btnFilter.setBounds(499, 81, 80, 38);
		panel.add(btnFilter);
		
		RoundButton btnEdit = new RoundButton("Edit",8,Color.decode("#FFBA42"));
		btnEdit.setBounds(589, 81, 80, 38);
		btnEdit.setFont(Bold);
		panel.add(btnEdit);
		
		RoundButton btnExport = new RoundButton("Export",8,Color.decode("#0B1E33"));
		btnExport.setForeground(new Color(255, 255, 255));
		btnExport.setFont(Bold);
		btnExport.setBounds(552, 490, 117, 38);
		panel.add(btnExport);
		
		table = new JTable();
		table.setBounds(18, 131, 651, 340);
		panel.add(table);
		
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
	}
}
