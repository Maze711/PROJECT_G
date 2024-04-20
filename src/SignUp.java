import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class SignUp {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtVerifyPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
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
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		
		
		Font mtmb = FontLoader.getFont("Primary", 12);
		Font SecondaryFont = FontLoader.getFont("Secondary", 18);
		Font AlreadyLog = FontLoader.getFont("Secondary", 13);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48); // FONT SIZE 48
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 10, 1083, 648);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setBounds(620, 0, 463, 648);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add User");
		lblNewLabel.setFont(PrimaryEB48Font);
		lblNewLabel.setBounds(131, 89, 236, 40);
		panel_1.add(lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setFont(SemiB);
		txtUsername.setText("Username");
		txtUsername.setBounds(120, 178, 296, 55);
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(SemiB);
		txtPassword.setText("Password");
		txtPassword.setBounds(120, 243, 296, 50);
		panel_1.add(txtPassword);
		txtPassword.setColumns(10);
		
		txtVerifyPassword = new JTextField();
		txtVerifyPassword.setText("Verify Password");
		txtVerifyPassword.setFont(SemiB);
		txtVerifyPassword.setColumns(10);
		txtVerifyPassword.setBounds(120, 303, 296, 50);
		panel_1.add(txtVerifyPassword);
		
		JButton btnNewButton = new JButton("Add User");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(11, 30, 51));
		btnNewButton.setFont(SemiB);
		btnNewButton.setBounds(120, 366, 296, 40);
		panel_1.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Already a member?");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_1.setBounds(169, 410, 129, 24);
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setForeground(new Color(0, 0, 64));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(310, 414, 74, 17);
		panel_1.add(btnNewButton_1);
		
		
		InfoRoundedCorner panel_2 = new InfoRoundedCorner(130);
		panel_2.setBounds(-93, 20, 714, 608);
		panel_2.setBackground(Color.decode("#00537A"));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Impound");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 186, 66));
		lblNewLabel_2.setBounds(231, 333, 326, 47);
		lblNewLabel_2.setFont(PrimaryEB48Font);
		panel_2.add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_2_1 = new JLabel("Inventory");
		lblNewLabel_2_1.setBounds(231, 389, 326, 47);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(PrimaryEB48Font);
		lblNewLabel_2_1.setForeground(new Color(255, 186, 66));
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("System");
		lblNewLabel_2_2.setBounds(221, 443, 326, 47);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(PrimaryEB48Font);
		lblNewLabel_2_2.setForeground(new Color(255, 186, 66));
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\flore\\git\\PROJECT_G\\Resources\\Images\\MTMBLogo.png"));
		lblNewLabel_3.setBounds(317, 69, 200, 200);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Muntinlupa Traffic Management Bureau");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(AlreadyLog);
		lblNewLabel_5.setBounds(247, 264, 300, 39);
		panel_2.add(lblNewLabel_5);
		frame.setBounds(100, 100, 1117, 705);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		System.out.println();
		
		
	}
}
