import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MTMBHome {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBHome window = new MTMBHome();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
					window.frame.setResizable(false);
                    window.frame.setTitle("MTMB");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MTMBHome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1044, 808);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//		CUSTOM FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1028, 768);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// Navigation Panel
		JPanel NavigationPanel = new JPanel();
		NavigationPanel.setBounds(0, 0, 293, 768);
		panel.add(NavigationPanel);
		NavigationPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("Resources\\Images\\MTMBLogo.png"));
		lblNewLabel_1.setBounds(52, 38, 132, 147);
		NavigationPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Dashboard");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(SemiB);
		lblNewLabel_4.setBounds(52, 220, 141, 36);
		NavigationPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("Home");
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(SemiB);
		lblNewLabel_4_1.setBounds(100, 277, 74, 36);
		NavigationPanel.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("Resources\\Icons\\IHome.png"));
		lblNewLabel_5.setBounds(52, 269, 42, 42);
		NavigationPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon("Resources\\Icons\\Database.png"));
		lblNewLabel_5_1.setBounds(52, 327, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_4_1_1 = new JLabel("Records");
		lblNewLabel_4_1_1.setForeground(Color.WHITE);
		lblNewLabel_4_1_1.setFont(SemiB);
		lblNewLabel_4_1_1.setBounds(100, 333, 102, 36);
		NavigationPanel.add(lblNewLabel_4_1_1);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setIcon(new ImageIcon("Resources\\Icons\\Download.png"));
		lblNewLabel_5_1_1.setBounds(52, 386, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_4_1_1_1 = new JLabel("Incoming");
		lblNewLabel_4_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_4_1_1_1.setFont(SemiB);
		lblNewLabel_4_1_1_1.setBounds(100, 386, 120, 36);
		NavigationPanel.add(lblNewLabel_4_1_1_1);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Upload.png"));
		lblNewLabel_5_1_1_1.setBounds(52, 445, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1 = new JLabel("Releasing");
		lblNewLabel_4_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_4_1_1_1_1.setFont(SemiB);
		lblNewLabel_4_1_1_1_1.setBounds(100, 445, 120, 36);
		NavigationPanel.add(lblNewLabel_4_1_1_1_1);
		
		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Logout.png"));
		lblNewLabel_5_1_1_1_1.setBounds(52, 543, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1_1_1_1);
		
		JLabel lblNewLabel_4_1_1_1_1_1 = new JLabel("Logout");
		lblNewLabel_4_1_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_4_1_1_1_1_1.setFont(SemiB);
		lblNewLabel_4_1_1_1_1_1.setBounds(100, 543, 120, 36);
		NavigationPanel.add(lblNewLabel_4_1_1_1_1_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resources\\Images\\BG info.png"));
		lblNewLabel.setBounds(0, 0, 293, 768);
		NavigationPanel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(292, 0, 736, 768);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		// Header
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 736, 70);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Welcome Back");
		lblNewLabel_2.setBounds(30, 23, 189, 36);
		lblNewLabel_2.setFont(PrimaryEBFont);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("Resources\\Icons\\Waving Hand Emoji.png"));
		lblNewLabel_3.setBounds(228, 25, 32, 32);
		panel_2.add(lblNewLabel_3);
	}
}
