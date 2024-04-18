import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MTMBRecordPage {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBRecordPage window = new MTMBRecordPage();
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
	public MTMBRecordPage() {
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
		
		JLabel MTMBLogo = new JLabel("");
		MTMBLogo.setIcon(new ImageIcon("Resources\\Images\\MTMBLogo.png"));
		MTMBLogo.setBounds(52, 38, 132, 147);
		NavigationPanel.add(MTMBLogo);
		
		JLabel Dashboard = new JLabel("Dashboard");
		Dashboard.setForeground(new Color(255, 255, 255));
		Dashboard.setFont(SemiB);
		Dashboard.setBounds(52, 220, 141, 36);
		NavigationPanel.add(Dashboard);
		
		JLabel Home = new JLabel("Home");
		Home.setForeground(Color.WHITE);
		Home.setFont(SemiB);
		Home.setBounds(100, 277, 74, 36);
		NavigationPanel.add(Home);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("Resources\\Icons\\IHome.png"));
		lblNewLabel_5.setBounds(52, 269, 42, 42);
		NavigationPanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setIcon(new ImageIcon("Resources\\Icons\\Database.png"));
		lblNewLabel_5_1.setBounds(52, 327, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1);
		
		JLabel Records = new JLabel("Records");
		Records.setForeground(Color.WHITE);
		Records.setFont(SemiB);
		Records.setBounds(100, 333, 102, 36);
		NavigationPanel.add(Records);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setIcon(new ImageIcon("Resources\\Icons\\Download.png"));
		lblNewLabel_5_1_1.setBounds(52, 386, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1_1);
		
		JLabel Incoming = new JLabel("Incoming");
		Incoming.setForeground(Color.WHITE);
		Incoming.setFont(SemiB);
		Incoming.setBounds(100, 386, 120, 36);
		NavigationPanel.add(Incoming);
		
		JLabel lblNewLabel_5_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Upload.png"));
		lblNewLabel_5_1_1_1.setBounds(52, 445, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1_1_1);
		
		JLabel Releasing = new JLabel("Releasing");
		Releasing.setForeground(Color.WHITE);
		Releasing.setFont(SemiB);
		Releasing.setBounds(100, 445, 120, 36);
		NavigationPanel.add(Releasing);
		
		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Logout.png"));
		lblNewLabel_5_1_1_1_1.setBounds(52, 543, 42, 42);
		NavigationPanel.add(lblNewLabel_5_1_1_1_1);
		
		JLabel Logout = new JLabel("Logout");
		Logout.setForeground(Color.WHITE);
		Logout.setFont(SemiB);
		Logout.setBounds(100, 543, 120, 36);
		NavigationPanel.add(Logout);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Resources\\Images\\BG info.png"));
		lblNewLabel.setBounds(0, 0, 293, 768);
		NavigationPanel.add(lblNewLabel);
		
		JPanel RecordPanel = new JPanel();
		RecordPanel.setBounds(292, 0, 736, 768);
		panel.add(RecordPanel);
		RecordPanel.setLayout(null);
		
		// Header
		JPanel InsideRecordPanel = new JPanel();
		InsideRecordPanel.setBounds(0, 0, 736, 70);
		RecordPanel.add(InsideRecordPanel);
		InsideRecordPanel.setLayout(null);
		
		JLabel Record = new JLabel("RECORD");
		Record.setBounds(30, 23, 189, 36);
		Record.setFont(PrimaryEBFont);
		InsideRecordPanel.add(Record);
	}
}
