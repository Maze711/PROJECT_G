import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MTMBLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final JFrame InfoFrame = null;
	private static final JFrame LoginFrame = null;
	private JPanel contentPane;
	private JTextField UsernameTxtField;
	private JTextField PasswordTxtField;
	private final MTMBDBAccount conn = new MTMBDBAccount();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBLogin frame = new MTMBLogin();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
					frame.setTitle("Muntinlupa Traffic Management Buereau Impounding System");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MTMBLogin() {
		setTitle("Muntinlupa Traffic Management Bureau Impounding System");
		setBackground(new Color(238, 246, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 246, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel LoginFrame = new JPanel();
		LoginFrame.setBounds(38, 198, 406, 452);
		contentPane.setOpaque(false);
		LoginFrame.setOpaque(false);
		contentPane.add(LoginFrame);

//		CUSTOM FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font Secondary16 = FontLoader.getFont("Secondary", 16);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font SemiB20 = FontLoader.getFont("SemiB", 20);
		Font SemiB15 = FontLoader.getFont("SemiB", 15);
		Font Bold = FontLoader.getFont("PrimaryEB32", 17);
		Font Bold36 = FontLoader.getFont("Bold", 36);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48); // FONT SIZE 48

//		The LoginFrame have properties of ABSOLUTE Layout
		LoginFrame.setLayout(new BorderLayout(0, 0));

//		INFORMATION TAB FOR LOGO AND TITLE OF THE SYSTEM
		InfoRoundedCorner InfoFrame = new InfoRoundedCorner(130);
		InfoFrame.setBounds(488, 22, 1000, 685);
		InfoFrame.setBackground(Color.decode("#00537A"));
		contentPane.add(InfoFrame);
		InfoFrame.setLayout(null);

		JLabel MTMBTitle = new JLabel("Impound");
		MTMBTitle.setHorizontalAlignment(SwingConstants.CENTER);
		MTMBTitle.setForeground(new Color(255, 186, 66));
		MTMBTitle.setBounds(101, 367, 345, 149);
		MTMBTitle.setFont(PrimaryEB48Font);
		InfoFrame.add(MTMBTitle);

		JLabel MTMBTitle2 = new JLabel("Inventory");
		MTMBTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		MTMBTitle2.setForeground(new Color(255, 186, 66));
		MTMBTitle2.setFont(PrimaryEB48Font);
		MTMBTitle2.setBounds(101, 440, 345, 149);
		InfoFrame.add(MTMBTitle2);

		JLabel MTMBTitle3 = new JLabel("System");
		MTMBTitle3.setHorizontalAlignment(SwingConstants.CENTER);
		MTMBTitle3.setForeground(new Color(255, 186, 66));
		MTMBTitle3.setFont(PrimaryEB48Font);
		MTMBTitle3.setBounds(101, 512, 345, 149);
		InfoFrame.add(MTMBTitle3);

		JLabel lblNewLabel = new JLabel("");
		ImageIcon logoMain = new ImageIcon(MTMBLogin.class.getResource("/Images/logomain.png"));
		lblNewLabel.setIcon(logoMain);
		lblNewLabel.setBounds(163, 160, 208, 216);
		InfoFrame.add(lblNewLabel);

//		SECONDARY PANEL INSIDE LOGIN FRAME
		JPanel PanelLoginLabel = new JPanel();
		PanelLoginLabel.setForeground(new Color(11, 30, 51));
		LoginFrame.add(PanelLoginLabel, BorderLayout.NORTH);
		PanelLoginLabel.setOpaque(false);

		JPanel InputPanel = new JPanel();
		LoginFrame.add(InputPanel, BorderLayout.CENTER);
		InputPanel.setOpaque(false);
		InputPanel.setLayout(null);

		ImageIcon closedEyeIcon = new ImageIcon(MTMBLogin.class.getResource("/Icons/Closed Eyes.png"));
		ImageIcon eyeIcon = new ImageIcon(MTMBLogin.class.getResource("/Icons/Eye.png"));

		JButton btnNewButton = new JButton(closedEyeIcon);
		btnNewButton.setBounds(350, 183, closedEyeIcon.getIconWidth(), closedEyeIcon.getIconHeight());
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		InputPanel.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnNewButton.getIcon().equals(closedEyeIcon)) {
					btnNewButton.setIcon(eyeIcon);
					((JPasswordField) PasswordTxtField).setEchoChar((char) 0);
				} else {
					btnNewButton.setIcon(closedEyeIcon);
					((JPasswordField) PasswordTxtField).setEchoChar('•');
				}
			}
		});

		UsernameTxtField = new RoundTxtField(40, new Color(0x0B1E33), 3);
		UsernameTxtField.setForeground(new Color(11, 30, 51));
		UsernameTxtField.setBackground(new Color(232, 248, 255));
		UsernameTxtField.setBounds(0, 60, 400, 62);
		UsernameTxtField.setFont(SecondaryFont);
		InputPanel.add(UsernameTxtField);
		UsernameTxtField.setColumns(10);

		PasswordTxtField = new RoundPasswordField(40, new Color(0x0B1E33), 3);
		PasswordTxtField.setForeground(new Color(11, 30, 51));
		PasswordTxtField.setBackground(new Color(232, 248, 255));
		PasswordTxtField.setBounds(0, 173, 400, 62);
		PasswordTxtField.setFont(SecondaryFont);
		InputPanel.add(PasswordTxtField);
		PasswordTxtField.setColumns(10);

		RoundButton LoginToggleButton = new RoundButton("Login", 60, Color.decode("#0B1E33"));
		LoginToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = UsernameTxtField.getText();
				String enteredPassword = PasswordTxtField.getText();

				if (username.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter username.", "MTMB",
							JOptionPane.INFORMATION_MESSAGE);
				} else if (enteredPassword.equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter password.", "MTMB",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					try {
						String query = "SELECT * FROM users WHERE username = ?";
						try (PreparedStatement preparedStatement = conn.getConnection().prepareStatement(query)) {
							preparedStatement.setString(1, username);
							ResultSet resultSet = preparedStatement.executeQuery();

							if (resultSet.next()) {
								String storedPassword = resultSet.getString("password");
								if (enteredPassword.equals(storedPassword)) {
									System.out.println("Login successful.");
									MTMBMainFrame home = new MTMBMainFrame(username);
									home.showWindowHome();
									dispose();
								} else {
									JOptionPane.showMessageDialog(null, "Incorrect password.", "MTMB",
											JOptionPane.ERROR_MESSAGE);
								}
							} else {
								JOptionPane.showMessageDialog(null, "User not found.", "MTMB",
										JOptionPane.ERROR_MESSAGE);
							}
						}
					} catch (SQLException ex) {
						System.err.println("Error logging in: " + ex.getMessage());
						ex.printStackTrace();
					}
				}
			}
		});

		LoginToggleButton.setForeground(new Color(255, 255, 255));
		LoginToggleButton.setBounds(0, 255, 400, 62);
		LoginToggleButton.setFont(PrimaryEBFont);
		InputPanel.add(LoginToggleButton);

		JLabel lblNewLabel_1_1 = new JLabel("Are you a member?");
		lblNewLabel_1_1.setFont(SemiB20);
		lblNewLabel_1_1.setBounds(44, 343, 211, 20);
		InputPanel.add(lblNewLabel_1_1);

		JButton btnNewButton_1 = new RoundButton("Sign Up", 10, Color.decode("#FFFFFF"));
		btnNewButton_1.setFont(SemiB20);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTMBSignup signup = new MTMBSignup();
				signup.showSignUp();
				dispose();
			}
		});

		btnNewButton_1.setForeground(new Color(11, 30, 51));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(Bold);
		btnNewButton_1.setBounds(223, 340, 131, 28);
		InputPanel.add(btnNewButton_1);

		JLabel txtEnterPassword = new JLabel("Enter Password");
		txtEnterPassword.setFont(Bold);
		txtEnterPassword.setBounds(0, 148, 252, 14);
		InputPanel.add(txtEnterPassword);

		JLabel txtEnterUsername = new JLabel("Enter Username");
		txtEnterUsername.setFont(Bold);
		txtEnterUsername.setBounds(0, 35, 252, 14);
		InputPanel.add(txtEnterUsername);

		JLabel LoginLabel = new JLabel("Hello");
		LoginLabel.setBounds(38, 90, 168, 97);
		contentPane.add(LoginLabel);
		LoginLabel.setFont(PrimaryFont);

		JLabel lblPleaseLoginTo = new JLabel("Please login to your system");
		lblPleaseLoginTo.setFont(Secondary16);
		lblPleaseLoginTo.setBounds(38, 166, 274, 42);
		contentPane.add(lblPleaseLoginTo);
		
		JLabel lblBscsa = new JLabel("© 2024 BSCS2A, PLMUN. All Rights Reserved");
		lblBscsa.setFont(new Font("Poppins Medium", Font.PLAIN, 16));
		lblBscsa.setBounds(59, 665, 372, 42);
		contentPane.add(lblBscsa);
		
		ImageIcon wave = new ImageIcon(MTMBLogin.class.getResource("/Icons/Waving Hand Emoji.png"));
		JLabel WaveHand = new JLabel("");
		WaveHand.setIcon(wave);
		WaveHand.setBounds(204, 109, 64, 65);
		contentPane.add(WaveHand);

	}
}