import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;


public class MTMBMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsernameTxtField;
	private JTextField PasswordTxtField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBMain frame = new MTMBMain();
					frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setTitle("MTMB"); // sets a title of frame
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MTMBMain() {
		setBackground(new Color(238, 246, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 246, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		INFORMATION TAB FOR LOGO AND TITLE OF THE SYSTEM
		JPanel InfoFrame = new JPanel();
		InfoFrame.setBounds(462, -2, 562, 768);
		InfoFrame.setBackground(Color.decode("#00537A"));
		contentPane.add(InfoFrame);
		
		JPanel LoginFrame = new JPanel();
		LoginFrame.setBounds(64, 190, 334, 417);
		contentPane.setOpaque(false);
		LoginFrame.setOpaque(false);
		contentPane.add(LoginFrame);
		
//		CUSTOME FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		
//		The LoginFrame have properties of ABSOLUTE Layout
		LoginFrame.setLayout(new BorderLayout(0, 0));
		
//		SECONDARY PANEL INSIDE LOGIN FRAME
		JPanel PanelLoginLabel = new JPanel();
		PanelLoginLabel.setForeground(new Color(11, 30, 51));
		LoginFrame.add(PanelLoginLabel, BorderLayout.NORTH);
		PanelLoginLabel.setOpaque(false);
		
		JLabel LoginLabel = new JLabel("Log In");
		LoginLabel.setFont(PrimaryFont);
		PanelLoginLabel.add(LoginLabel, BorderLayout.NORTH);
		
		JPanel InputPanel = new JPanel();
		LoginFrame.add(InputPanel, BorderLayout.CENTER);
		InputPanel.setOpaque(false);
		InputPanel.setLayout(null);
		
		
		UsernameTxtField = new JTextField();
		UsernameTxtField.setForeground(new Color(11, 30, 51));
		UsernameTxtField.setBackground(new Color(232, 248, 255));
		UsernameTxtField.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		UsernameTxtField.setText("Username");
		UsernameTxtField.setBounds(0, 37, 334, 62);
		UsernameTxtField.setFont(SecondaryFont);
		InputPanel.add(UsernameTxtField);
		UsernameTxtField.setColumns(10);
		
		PasswordTxtField = new JTextField();
		PasswordTxtField.setForeground(new Color(11, 30, 51));
		PasswordTxtField.setBackground(new Color(232, 248, 255));
		PasswordTxtField.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		PasswordTxtField.setText("Password");
		PasswordTxtField.setBounds(0, 127, 334, 62);
		PasswordTxtField.setFont(SecondaryFont);
		InputPanel.add(PasswordTxtField);
		PasswordTxtField.setColumns(10);
		
		JToggleButton LoginToggleButton = new JToggleButton("Login");
		LoginToggleButton.setForeground(new Color(255, 255, 255));
		LoginToggleButton.setBackground(new Color(11, 30, 51));
		LoginToggleButton.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		LoginToggleButton.setBounds(0, 214, 334, 62);
		LoginToggleButton.setFont(PrimaryEBFont);
		InputPanel.add(LoginToggleButton);
		
	}
}
