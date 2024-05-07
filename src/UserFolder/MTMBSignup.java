package UserFolder;
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

import CustomClassLoader.FontLoader;
import CustomClassLoader.InfoRoundedCorner;
import CustomClassLoader.RoundButton;
import CustomClassLoader.RoundPasswordField;
import CustomClassLoader.RoundTxtField;
import DatabaseConnection.MTMBDBAccount;

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
					window.frame.setTitle("Muntinlupa Traffice Management Bureau Impound Inventory System");
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
		frame.setTitle("Muntinlupa Traffic Management Bureau Impounding System");
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

		ImageIcon closedEyeIcon = new ImageIcon(MTMBLogin.class.getResource("/Icons/Closed Eyes.png"));
		ImageIcon eyeIcon = new ImageIcon(MTMBLogin.class.getResource("/Icons/Eye.png"));
		JButton btnNewButton_2 = new JButton(closedEyeIcon);
		btnNewButton_2.setBounds(362, 330, closedEyeIcon.getIconWidth(), closedEyeIcon.getIconHeight());
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setContentAreaFilled(false);
		panel_1.add(btnNewButton_2);

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton_2.getIcon().equals(closedEyeIcon)) {
					btnNewButton_2.setIcon(eyeIcon);
					((JPasswordField) password).setEchoChar((char) 0);
					((JPasswordField) verifyPassword).setEchoChar((char) 0);
				} else {
					btnNewButton_2.setIcon(closedEyeIcon);
					((JPasswordField) password).setEchoChar('•');
					((JPasswordField) verifyPassword).setEchoChar('•');
				}
			}
		});

		JLabel lblNewLabel = new JLabel("New User");
		lblNewLabel.setFont(PrimaryFont);
		lblNewLabel.setBounds(76, 90, 334, 86);
		lblNewLabel.setForeground(new Color(11, 30, 51));
		panel_1.add(lblNewLabel);

		txtUsername = new RoundTxtField(40, new Color(0x0B1E33), 3);
		txtUsername.setFont(SecondaryFont);
		txtUsername.setBounds(22, 222, 400, 62);
		txtUsername.setBackground(new Color(232, 248, 255));
		txtUsername.setForeground(new Color(11, 30, 51));
		panel_1.add(txtUsername);
		txtUsername.setColumns(10);

		password = new RoundPasswordField(40, new Color(0x0B1E33), 3);
		password.setForeground(new Color(11, 30, 51));
		password.setBackground(new Color(232, 248, 255));
		password.setFont(SecondaryFont);
		password.setColumns(10);
		password.setBounds(22, 320, 400, 62);
		panel_1.add(password);

		verifyPassword = new RoundPasswordField(40, new Color(0x0B1E33), 3);
		verifyPassword.setForeground(new Color(11, 30, 51));
		verifyPassword.setBackground(new Color(232, 248, 255));
		verifyPassword.setFont(SecondaryFont);
		verifyPassword.setColumns(10);
		verifyPassword.setBounds(22, 413, 400, 62);
		panel_1.add(verifyPassword);

		JButton btnNewButton = new RoundButton("Create", 60, Color.decode("#0B1E33"));
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (txtUsername.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter username.", "MTMB", JOptionPane.INFORMATION_MESSAGE);
		        } else if (password.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please enter password.", "MTMB", JOptionPane.INFORMATION_MESSAGE);
		        } else if (verifyPassword.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Please verify your password", "MTMB", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            String username = txtUsername.getText();
		            String userPassword = password.getText();
		            String auth = "user";
		            try {
		            	String query = "INSERT INTO users (username, password, authorization) VALUES (?, ?, '" + auth + "')";
		                try (PreparedStatement preparedStatement = conn.getConnection().prepareStatement(query)) {
		                    preparedStatement.setString(1, username);
		                    preparedStatement.setString(2, userPassword);
		                    int rowsAffected = preparedStatement.executeUpdate();
		                    if (rowsAffected > 0) {
		                        System.out.println("User registered successfully.");
		                        // Create and show the pop-up window
		                        MTMBPopUp popUp = new MTMBPopUp(frame);
		                        popUp.showWindow();
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
		btnNewButton.setBounds(60, 486, 334, 62);
		panel_1.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Already a member?");
		lblNewLabel_1.setFont(SemiB);
		lblNewLabel_1.setBounds(117, 559, 199, 45);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_1 = new RoundButton("Login", 10, Color.decode("#FFFFFF"));
		btnNewButton_1.setText("Log In");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTMBLogin log = new MTMBLogin();
				log.setLocationRelativeTo(null);
				log.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setForeground(new Color(11, 30, 51));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(Bold);
		btnNewButton_1.setBounds(255, 571, 100, 21);
		panel_1.add(btnNewButton_1);

		JLabel txtVerifyPass = new JLabel("Verify Password");
		txtVerifyPass.setBounds(31, 391, 226, 21);
		txtVerifyPass.setFont(Bold);
		panel_1.add(txtVerifyPass);

		JLabel txtEnterPassword = new JLabel("Enter Password");
		txtEnterPassword.setBounds(31, 295, 237, 14);
		txtEnterPassword.setFont(Bold);
		panel_1.add(txtEnterPassword);

		JLabel txtEnterUsername = new JLabel("Enter Username");
		txtEnterUsername.setFont(Bold);
		txtEnterUsername.setBounds(31, 197, 199, 14);
		panel_1.add(txtEnterUsername);
		
		JLabel lblBscsa = new JLabel("© 2024 BSCS2A, PLMUN. All Rights Reserved");
		lblBscsa.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblBscsa.setBounds(50, 667, 372, 42);
		panel_1.add(lblBscsa);

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
		ImageIcon logoMain = new ImageIcon(MTMBLogin.class.getResource("/Images/logomain.png"));
		lblNewLabel_3.setIcon(logoMain);
		lblNewLabel_3.setBounds(259, 108, 200, 200);
		panel_2.add(lblNewLabel_3);

	}
}