import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;

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
		Font SemiB = FontLoader.getFont("SemiB", 16);
		Font Bold = FontLoader.getFont("Bold", 42);
		Font Bold16 = FontLoader.getFont("Bold16", 16);
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
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(269, 274, 24, 42);
		lblNewLabel_4.setIcon(new ImageIcon("Resources\\Icons\\Slider.png"));
		NavigationPanel.add(lblNewLabel_4);

		JLabel MTMBLogo = new JLabel("");
		MTMBLogo.setBounds(52, 38, 132, 147);
		MTMBLogo.setIcon(new ImageIcon("Resources\\Images\\MTMBLogo.png"));
		NavigationPanel.add(MTMBLogo);

		JLabel Dashboard = new JLabel("Dashboard");
		Dashboard.setBounds(52, 220, 141, 36);
		Dashboard.setForeground(new Color(255, 255, 255));
		Dashboard.setFont(SemiB);
		NavigationPanel.add(Dashboard);

		JLabel Home = new JLabel("Home");
		Home.setBounds(100, 277, 74, 36);
		Home.setForeground(Color.WHITE);
		Home.setFont(SemiB);
		NavigationPanel.add(Home);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(52, 269, 42, 42);
		lblNewLabel_5.setIcon(new ImageIcon("Resources\\Icons\\IHome.png"));
		NavigationPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(52, 327, 42, 42);
		lblNewLabel_5_1.setIcon(new ImageIcon("Resources\\Icons\\Database.png"));
		NavigationPanel.add(lblNewLabel_5_1);

		JLabel Records = new JLabel("Records");
		Records.setBounds(100, 333, 102, 36);
		Records.setForeground(Color.WHITE);
		Records.setFont(SemiB);
		NavigationPanel.add(Records);

		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setBounds(52, 386, 42, 42);
		lblNewLabel_5_1_1.setIcon(new ImageIcon("Resources\\Icons\\Download.png"));
		NavigationPanel.add(lblNewLabel_5_1_1);

		JLabel Incoming = new JLabel("Incoming");
		Incoming.setBounds(100, 386, 120, 36);
		Incoming.setForeground(Color.WHITE);
		Incoming.setFont(SemiB);
		NavigationPanel.add(Incoming);

		JLabel lblNewLabel_5_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1.setBounds(52, 445, 42, 42);
		lblNewLabel_5_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Upload.png"));
		NavigationPanel.add(lblNewLabel_5_1_1_1);

		JLabel Releasing = new JLabel("Releasing");
		Releasing.setBounds(100, 445, 120, 36);
		Releasing.setForeground(Color.WHITE);
		Releasing.setFont(SemiB);
		NavigationPanel.add(Releasing);

		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1_1.setBounds(52, 543, 42, 42);
		lblNewLabel_5_1_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Logout.png"));
		NavigationPanel.add(lblNewLabel_5_1_1_1_1);

		JLabel Logout = new JLabel("Logout");
		Logout.setBounds(100, 543, 120, 36);
		Logout.setForeground(Color.WHITE);
		Logout.setFont(SemiB);
		NavigationPanel.add(Logout);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 293, 768);
		lblNewLabel.setIcon(new ImageIcon("Resources\\Images\\BG info.png"));
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

		JLabel txtWelcome = new JLabel("Welcome Back");
		txtWelcome.setBounds(30, 23, 189, 36);
		txtWelcome.setFont(PrimaryEBFont);
		panel_2.add(txtWelcome);

		JLabel IWelcome = new JLabel("");
		IWelcome.setIcon(new ImageIcon("Resources\\Icons\\Waving Hand Emoji.png"));
		IWelcome.setBounds(228, 25, 32, 32);
		panel_2.add(IWelcome);
		
		JLabel txtUsertype = new JLabel("User");
		txtUsertype.setFont(Bold16);
		txtUsertype.setBounds(600, 23, 95, 36);
		panel_2.add(txtUsertype);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 71, 735, 698);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel txtCurrentMnt = new JLabel("April 2024");
		txtCurrentMnt.setFont(Bold);
		txtCurrentMnt.setBounds(30, 16, 286, 40);
		panel_3.add(txtCurrentMnt);
		
		JLabel ITotal = new JLabel("");
		ITotal.setIcon(new ImageIcon("Resources\\Icons\\Car.png"));
		ITotal.setBounds(270, 110, 135, 40);
		panel_3.add(ITotal);
		
		JLabel txtTotalVehicle = new JLabel("578");
		txtTotalVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalVehicle.setForeground(Color.WHITE);
		txtTotalVehicle.setFont(Bold);
		txtTotalVehicle.setBounds(37, 96, 274, 49);
		panel_3.add(txtTotalVehicle);
		
		JLabel txtTotalDisplay = new JLabel("Total Vehicle Impounded ");
		txtTotalDisplay.setForeground(new Color(255, 255, 255));
		txtTotalDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalDisplay.setFont(SemiB);
		txtTotalDisplay.setBounds(37, 140, 274, 40);
		panel_3.add(txtTotalDisplay);
		
		JLabel txtTotalVehicle_2 = new JLabel("98");
		txtTotalVehicle_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalVehicle_2.setForeground(Color.WHITE);
		txtTotalVehicle_2.setFont(Bold);
		txtTotalVehicle_2.setBounds(440, 96, 274, 49);
		panel_3.add(txtTotalVehicle_2);
		
		JLabel lblTotalVehicleReleased = new JLabel("Total Vehicle Released ");
		lblTotalVehicleReleased.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalVehicleReleased.setForeground(Color.WHITE);
		lblTotalVehicleReleased.setFont(SemiB);
		lblTotalVehicleReleased.setBounds(440, 140, 274, 40);
		panel_3.add(lblTotalVehicleReleased);
		
		JLabel bgTotal_1 = new JLabel("");
		bgTotal_1.setIcon(new ImageIcon("Resources\\Images\\Total.png"));
		bgTotal_1.setBounds(30, 67, 393, 125);
		panel_3.add(bgTotal_1);
		
		JLabel bgTotal_2 = new JLabel("");
		bgTotal_2.setIcon(new ImageIcon("Resources\\Images\\Total-1.png"));
		bgTotal_2.setBounds(436, 67, 270, 125);
		panel_3.add(bgTotal_2);
		
		JLabel txtInfo = new JLabel("Daily Impounded Records");
		txtInfo.setFont(SemiB);
		txtInfo.setBounds(31, 205, 361, 40);
		panel_3.add(txtInfo);
		
		
		
		
	}
}