import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MTMBSignup extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;
	private final MTMBDBAccount conn = new MTMBDBAccount();
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
					MTMBSignup window = new MTMBSignup();
					window.frame.setVisible(true);
					window.setResizable(false);
					window.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showSignUp() {
		// TODO Auto-generated method stub
			JFrame SignUp = frame;
			SignUp.show();
			SignUp.setLocationRelativeTo(null);
	}
	
	public MTMBSignup() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("SignUp");
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane();
		frame.setBounds(100, 100, 1066, 705);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Muntinlupa Traffic Management Bureau");
		frame.setResizable(false);
		frame.setBackground(new Color(238, 246, 255));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1024, 768);
		frame.setLocationRelativeTo(null);

		// Fonts
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 15);
		Font Bold = FontLoader.getFont("PrimaryEB32", 17);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48); // FONT SIZE 48

		// Panels
		JPanel panel = new JPanel();
		panel.setBackground(new Color(238, 246, 255));
		panel.setBounds(0, 10, 1010, 721);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(244, 247, 252));
		panel_1.setBounds(568, 0, 432, 720);
		panel.add(panel_1);
		panel_1.setLayout(null);

		InfoRoundedCorner panel_2 = new InfoRoundedCorner(130);
		panel_2.setBounds(-93, 10, 662, 701);
		panel_2.setBackground(Color.decode("#00537A"));
		panel.add(panel_2);
		panel_2.setLayout(null);

		InfoRoundedCorner panel_3 = new InfoRoundedCorner(130);
		panel_3.setBounds(-439, 21, 654, 700);
		frame.getContentPane().add(panel_3);

		JLabel lblNewLabel = new JLabel("Add User");
		lblNewLabel.setFont(PrimaryFont);
		lblNewLabel.setBounds(76, 90, 334, 86);
		lblNewLabel.setForeground(new Color(11, 30, 51));
		panel_1.add(lblNewLabel);

		txtUsername = new RoundTxtField(40, new Color(0x0B1E33), 3);
		txtUsername.setFont(SecondaryFont);
		txtUsername.setText("   Username");
		txtUsername.setBounds(60, 222, 334, 62);
		txtUsername.setBackground(new Color(232, 248, 255));
		txtUsername.setForeground(new Color(11, 30, 51));
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);

		password = new RoundPasswordField(40, new Color(0x0B1E33), 3);
		password.setForeground(new Color(11, 30, 51));
		password.setBackground(new Color(232, 248, 255));
		password.setFont(SecondaryFont);
		password.setColumns(10);
		password.setBounds(60, 300, 334, 62);
		password.setText("Verify Password");
		panel_1.add(password);

		verifyPassword = new RoundPasswordField(40, new Color(0x0B1E33), 3);
		verifyPassword.setForeground(new Color(11, 30, 51));
		verifyPassword.setBackground(new Color(232, 248, 255));
		verifyPassword.setFont(SecondaryFont);
		verifyPassword.setColumns(10);
		verifyPassword.setBounds(60, 376, 334, 62);
		verifyPassword.setText("Verify Password");
		panel_1.add(verifyPassword);

		JButton btnNewButton = new RoundButton("Add User", 60, Color.decode("#0B1E33"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUsername.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter username.", "MTMB",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (password.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter password.", "MTMB",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (verifyPassword.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please verify your password", "MTMB",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String username = txtUsername.getText();
					String userPassword = password.getText();
					try {
						String query = "INSERT INTO users (username, password) VALUES (?, ?)";
						try (PreparedStatement preparedStatement = conn.getConnection().prepareStatement(query)) {
							preparedStatement.setString(1, username);
							preparedStatement.setString(2, userPassword);
							int rowsAffected = preparedStatement.executeUpdate();
							if (rowsAffected > 0) {
								System.out.println("User registered successfully.");
								MTMBPopUp pop = new MTMBPopUp(frame);
								pop.showWindow();
							} else {
								System.out.println("Failed to register user.");
							}
						}
					} catch (SQLException ex) {
						System.err.println("Error registering user: " + ex.getMessage());
						ex.printStackTrace();
					}
				}
			}
		});

		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(11, 30, 51));
		btnNewButton.setFont(PrimaryEBFont);
		btnNewButton.setBounds(60, 454, 334, 62);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Already a member?");
		lblNewLabel_1.setFont(SemiB);
		lblNewLabel_1.setBounds(126, 512, 199, 45);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_1 = new RoundButton("Login", 10, Color.decode("#FFFFFF"));
		btnNewButton_1.setText("Log In");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTMBLogin log = new MTMBLogin();
				log.setLocationRelativeTo(null);
				log.setVisible(true);

			}
		});
		btnNewButton_1.setForeground(new Color(11, 30, 51));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(Bold);
		btnNewButton_1.setBounds(260, 524, 100, 21);
		panel_1.add(btnNewButton_1);

		JLabel lblNewLabel_2 = new JLabel("Impound");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setForeground(new Color(255, 186, 66));
		lblNewLabel_2.setBounds(209, 392, 326, 47);
		lblNewLabel_2.setFont(PrimaryEB48Font);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Inventory");
		lblNewLabel_2_1.setBounds(209, 469, 326, 47);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(PrimaryEB48Font);
		lblNewLabel_2_1.setForeground(new Color(255, 186, 66));
		panel_2.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("System");
		lblNewLabel_2_2.setBounds(209, 540, 326, 47);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(PrimaryEB48Font);
		lblNewLabel_2_2.setForeground(new Color(255, 186, 66));
		panel_2.add(lblNewLabel_2_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("Resources\\Images\\logomain.png"));
		lblNewLabel_3.setBounds(259, 108, 200, 200);
		panel_2.add(lblNewLabel_3);

	}
}
