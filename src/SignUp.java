import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SignUp extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private JTextField txtUsername;
	private JPasswordField password;
	private JPasswordField verifyPassword;
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
	public void initialize() {
		frame = new JFrame("SignUp");
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setTitle("Muntinlupa Traffic Management Bureau");
		
		
		
		
		//Fonts
		FontLoader.getFont("Primary", 12);
		FontLoader.getFont("Secondary", 18);
		Font AlreadyLog = FontLoader.getFont("Secondary", 13);
		FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font SemiLog = FontLoader.getFont("SemiB", 15);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48); // FONT SIZE 48
		
		
		//Panels
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
		
		InfoRoundedCorner panel_2 = new InfoRoundedCorner(130);
		panel_2.setBounds(-93, 0, 703, 648);
		panel_2.setBackground(Color.decode("#00537A"));
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		InfoRoundedCorner panel_3 = new InfoRoundedCorner(130);
		panel_3.setBounds(-439, 10, 571, 648);
		frame.getContentPane().add(panel_3);
		frame.setBounds(100, 100, 1117, 705);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
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
		
		
		
		password = new JPasswordField();
		password.setFont(SemiB);
		password.setColumns(10);
		password.setBounds(120, 243, 296, 50);
		password.setText("Verify Password");
		panel_1.add(password);
		
		verifyPassword = new JPasswordField();
		verifyPassword.setFont(SemiB);
		verifyPassword.setColumns(10);
		verifyPassword.setBounds(120, 303, 296, 50);
		verifyPassword.setText("Verify Password");
		panel_1.add(verifyPassword);
			

		
		
		JButton btnNewButton = new JButton("Add User");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (txtUsername.getText().equals("")) {
		            JOptionPane.showMessageDialog(null, "Please enter username.", "MTMB", JOptionPane.INFORMATION_MESSAGE);
		        }
		        else if (password.getText().equals("")) {
		            JOptionPane.showMessageDialog(null, "Please enter password.", "MTMB", JOptionPane.INFORMATION_MESSAGE);
		        }
		        else if (verifyPassword.getText().equals("")) {
		            JOptionPane.showMessageDialog(null, "Please verify your password", "MTMB", JOptionPane.INFORMATION_MESSAGE);
		        }
		        else {
		        	//To link your class to popUp
					popUp pop = new popUp();
					pop.showWindow();
					}
				
				
			}
		});
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
		btnNewButton_1.setFont(SemiLog);
		btnNewButton_1.setBounds(310, 414, 86, 17);
		panel_1.add(btnNewButton_1);
		
		
		JLabel lblNewLabel_2 = new JLabel("Impound");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 186, 66));
		lblNewLabel_2.setBounds(209, 332, 326, 47);
		lblNewLabel_2.setFont(PrimaryEB48Font);
		panel_2.add(lblNewLabel_2);
		
		
		JLabel lblNewLabel_2_1 = new JLabel("Inventory");
		lblNewLabel_2_1.setBounds(209, 386, 326, 47);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(PrimaryEB48Font);
		lblNewLabel_2_1.setForeground(new Color(255, 186, 66));
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("System");
		lblNewLabel_2_2.setBounds(209, 443, 326, 47);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(PrimaryEB48Font);
		lblNewLabel_2_2.setForeground(new Color(255, 186, 66));
		panel_2.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("Resources\\Images\\logomain.png"));
		lblNewLabel_3.setBounds(256, 65, 200, 200);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Muntinlupa Traffic Management Bureau");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(AlreadyLog);
		
				
			
		
	
		
		System.out.println();
		
		
	}
}
