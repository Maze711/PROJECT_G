import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MTMBMainFrame {

	private JFrame frame;
	private final MTMBDBCONN conn = new MTMBDBCONN();
	private JPanel cards;
	private CardLayout cardLayout;
	private MTMBIncomingPage incomingPanel;
	private MTMBReleasingPage releasePanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBMainFrame window = new MTMBMainFrame();
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

	public void showWindowHome() {
		// TODO Auto-generated method stub
		JFrame home = frame;
		home.show();
		home.setLocationRelativeTo(null);
	}

	public MTMBMainFrame() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1044, 808);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// CUSTOM FONT FAMILY STYLE
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
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBounds(0, 0, 293, 768);
		panel.add(navigationPanel);
		navigationPanel.setLayout(null);

		JLabel MTMBLogo = new JLabel("");
		MTMBLogo.setBounds(52, 38, 132, 147);
		MTMBLogo.setIcon(new ImageIcon("Resources\\Images\\MTMBLogo.png"));
		navigationPanel.add(MTMBLogo);

		JLabel Dashboard = new JLabel("Dashboard");
		Dashboard.setBounds(52, 220, 141, 36);
		Dashboard.setForeground(new Color(255, 255, 255));
		Dashboard.setFont(SemiB);
		navigationPanel.add(Dashboard);

		JLabel homeLabel = new JLabel("Home");
		homeLabel.setBounds(100, 277, 74, 36);
		homeLabel.setForeground(Color.WHITE);
		homeLabel.setFont(SemiB);
		navigationPanel.add(homeLabel);
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBHome");
			}
		});

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(52, 269, 42, 42);
		lblNewLabel_5.setIcon(new ImageIcon("Resources\\Icons\\IHome.png"));
		navigationPanel.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(52, 327, 42, 42);
		lblNewLabel_5_1.setIcon(new ImageIcon("Resources\\Icons\\Database.png"));
		navigationPanel.add(lblNewLabel_5_1);

		JLabel recordsLabel = new JLabel("Records");
		recordsLabel.setBounds(100, 333, 102, 36);
		recordsLabel.setForeground(Color.WHITE);
		recordsLabel.setFont(SemiB);
		navigationPanel.add(recordsLabel);
		recordsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBRecordPage");
			}
		});

		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setBounds(52, 386, 42, 42);
		lblNewLabel_5_1_1.setIcon(new ImageIcon("Resources\\Icons\\Download.png"));
		navigationPanel.add(lblNewLabel_5_1_1);

		JLabel incomingLabel = new JLabel("Incoming");
		incomingLabel.setBounds(100, 386, 120, 36);
		incomingLabel.setForeground(Color.WHITE);
		incomingLabel.setFont(SemiB);
		navigationPanel.add(incomingLabel);
		incomingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBIncomingPage");
			}
		});

		JLabel lblNewLabel_5_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1.setBounds(52, 445, 42, 42);
		lblNewLabel_5_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Upload.png"));
		navigationPanel.add(lblNewLabel_5_1_1_1);

		JLabel releasingLabel = new JLabel("Releasing");
		releasingLabel.setBounds(100, 445, 120, 36);
		releasingLabel.setForeground(Color.WHITE);
		releasingLabel.setFont(SemiB);
		navigationPanel.add(releasingLabel);
		releasingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBReleasingPage");
			}
		});

		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1_1.setBounds(52, 543, 42, 42);
		lblNewLabel_5_1_1_1_1.setIcon(new ImageIcon("Resources\\Icons\\Logout.png"));
		navigationPanel.add(lblNewLabel_5_1_1_1_1);

		JLabel Logout = new JLabel("Logout");
		Logout.setBounds(100, 543, 120, 36);
		Logout.setForeground(Color.WHITE);
		Logout.setFont(SemiB);
		navigationPanel.add(Logout);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 293, 768);
		lblNewLabel.setIcon(new ImageIcon("Resources\\Images\\BG info.png"));
		navigationPanel.add(lblNewLabel);

		// Cards Panel
		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);
		cards.setBounds(292, 0, 736, 768);
		frame.getContentPane().add(cards);

		// Add mouse listeners to labels
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBHome"); // Show MTMBHome when homeLabel is clicked
			}
		});

		MTMBHome homePanel = new MTMBHome();
		cards.add(homePanel, "MTMBHome");

		cardLayout.show(cards, "MTMBHome"); // Show MTMBHome by default

		MTMBRecordPage recordPanel = new MTMBRecordPage();
		cards.add(recordPanel, "MTMBRecordPage");

		incomingPanel = new MTMBIncomingPage();
		cards.add(incomingPanel, "MTMBIncomingPage");

		releasePanel = new MTMBReleasingPage();
		cards.add(releasePanel, "MTMBReleasingPage");

		recordsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBRecordPage");
				recordPanel.setVisible(true); // Show the MTMBRecordPage panel
			}
		});

		incomingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBIncomingPage");
			}
		});

		releasingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBReleasingPage");
			}
		});
	}
}