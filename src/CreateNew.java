import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateNew {

	private JFrame frame;
	private JTextField txtInputYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateNew window = new CreateNew();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void CreateNew(){
		JFrame create = frame;
		create.show();
	}
	/**
	 * Create the application.
	 */
	public CreateNew() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		
		//Fonts
		Font PrimaryFont = FontLoader.getFont("Primary", 30);
		Font SecondaryFont = FontLoader.getFont("Secondary", 15);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font ExtraBold = FontLoader.getFont("ExtraBold",24);
		Font ExtraBold2 = FontLoader.getFont("ExtraBold",12);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 454, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel txtCreate = new JLabel("Create New Record");
		txtCreate.setBackground(new Color(16, 10, 14));
		txtCreate.setBounds(46, 55, 362, 44);
		txtCreate.setFont(ExtraBold);
		panel.add(txtCreate);
		
		txtInputYear = new RoundTxtField(40, new Color(0x0B1E33), 3);
		txtInputYear.setHorizontalAlignment(SwingConstants.LEFT);
		txtInputYear.setText("     Input Year");
		txtInputYear.setBounds(46, 110, 362, 46);
		panel.add(txtInputYear);
		txtInputYear.setColumns(10);
		
		RoundButton btnCancel = new RoundButton("Cancel",8,Color.decode("#FFBA42"));
		btnCancel.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	MTMBRecordPage window = new MTMBRecordPage();
		    	window.showRecords();
		        frame.dispose();
		    }
		});

		btnCancel.setBounds(174, 175, 112, 40);
		btnCancel.setFont(ExtraBold2);
		panel.add(btnCancel);
		
		RoundButton btnCreate = new RoundButton("Create",8,Color.decode("#0B1E33"));
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCreate.setForeground(new Color(255, 255, 255));
		btnCreate.setBackground(new Color(255, 255, 255));
		btnCreate.setBounds(296, 175, 112, 40);
		btnCreate.setFont(ExtraBold2);
		panel.add(btnCreate);
	}
}
