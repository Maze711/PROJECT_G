import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MTMBLogin extends JFrame {

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
					MTMBLogin frame = new MTMBLogin();
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
	public MTMBLogin() {
		setBackground(new Color(238, 246, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 246, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel LoginFrame = new JPanel();
		LoginFrame.setBounds(95, 190, 334, 417);
		contentPane.setOpaque(false);
		LoginFrame.setOpaque(false);
		contentPane.add(LoginFrame);
		
//		CUSTOM FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
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
		lblNewLabel.setIcon(new ImageIcon("Resources\\Images\\logomain.png"));
		lblNewLabel.setBounds(163, 160, 208, 216);
		InfoFrame.add(lblNewLabel);
		
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
