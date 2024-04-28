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
					window.frame.setTitle("Muntinlupa Traffice Management Bureau Impound Inventory System");
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
		home.setTitle("Muntinlupa Traffice Management Bureau");
	}

	public MTMBMainFrame() {
		initialize();

		// Add a window listener to the frame
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				cardLayout.show(cards, "MTMBHome"); // Show MTMBHome when the frame is opened
			}
		});
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1044, 808);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

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

		ImageIcon logo = new ImageIcon(MTMBLogin.class.getResource("/Images/MTMBLogo.png"));
		JLabel MTMBLogo = new JLabel("");
		MTMBLogo.setBounds(52, 38, 132, 147);
		MTMBLogo.setIcon(logo);
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
			public void mouseEntered(MouseEvent e) {
				homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				homeLabel.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBHome");
			}
		});

		ImageIcon home = new ImageIcon(MTMBLogin.class.getResource("/Icons/IHome.png"));
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(52, 269, 42, 42);
		lblNewLabel_5.setIcon(home);
		navigationPanel.add(lblNewLabel_5);

		ImageIcon db = new ImageIcon(MTMBLogin.class.getResource("/Icons/Database.png"));
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(52, 327, 42, 42);
		lblNewLabel_5_1.setIcon(db);
		navigationPanel.add(lblNewLabel_5_1);

		JLabel recordsLabel = new JLabel("Records");
		recordsLabel.setBounds(100, 333, 102, 36);
		recordsLabel.setForeground(Color.WHITE);
		recordsLabel.setFont(SemiB);
		navigationPanel.add(recordsLabel);
		recordsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				recordsLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				recordsLabel.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBRecordPage");
			}
		});

		ImageIcon download = new ImageIcon(MTMBLogin.class.getResource("/Icons/Download.png"));
		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setBounds(52, 386, 42, 42);
		lblNewLabel_5_1_1.setIcon(download);
		navigationPanel.add(lblNewLabel_5_1_1);

		JLabel incomingLabel = new JLabel("Incoming");
		incomingLabel.setBounds(100, 386, 120, 36);
		incomingLabel.setForeground(Color.WHITE);
		incomingLabel.setFont(SemiB);
		navigationPanel.add(incomingLabel);
		incomingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				incomingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				incomingLabel.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBIncomingPage");
			}
		});

		ImageIcon upload = new ImageIcon(MTMBLogin.class.getResource("/Icons/Upload.png"));
		JLabel lblNewLabel_5_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1.setBounds(52, 445, 42, 42);
		lblNewLabel_5_1_1_1.setIcon(upload);
		navigationPanel.add(lblNewLabel_5_1_1_1);

		JLabel releasingLabel = new JLabel("Releasing");
		releasingLabel.setBounds(100, 445, 120, 36);
		releasingLabel.setForeground(Color.WHITE);
		releasingLabel.setFont(SemiB);
		navigationPanel.add(releasingLabel);
		releasingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				releasingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				releasingLabel.setCursor(Cursor.getDefaultCursor());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBReleasingPage");
			}
		});

		ImageIcon logout = new ImageIcon(MTMBLogin.class.getResource("/Icons/Logout.png"));
		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1_1.setBounds(52, 543, 42, 42);
		lblNewLabel_5_1_1_1_1.setIcon(logout);
		navigationPanel.add(lblNewLabel_5_1_1_1_1);

		JLabel Logout = new JLabel("Logout");
		Logout.setBounds(100, 543, 120, 36);
		Logout.setForeground(Color.WHITE);
		Logout.setFont(SemiB);
		navigationPanel.add(Logout);

		ImageIcon bgInfo = new ImageIcon(MTMBLogin.class.getResource("/Images/BG info.png"));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 293, 768);
		lblNewLabel.setIcon(bgInfo);
		navigationPanel.add(lblNewLabel);

		// Cards Panel
		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);
		cards.setBounds(292, 0, 736, 768);
		frame.getContentPane().add(cards);

		MTMBHome homePanel = new MTMBHome();
		homePanel.setVisible(true); // Ensure the panel is visible
		cards.add(homePanel, "MTMBHome");

		MTMBRecordPage recordPanel = new MTMBRecordPage();
		cards.add(recordPanel, "MTMBRecordPage");

		incomingPanel = new MTMBIncomingPage();
		cards.add(incomingPanel, "MTMBIncomingPage");

		releasePanel = new MTMBReleasingPage();
		cards.add(releasePanel, "MTMBReleasingPage");

		// Set MTMBHome as default panel after adding all panels
		cardLayout.show(cards, "MTMBHome");

		// Add mouse listeners to labels
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBHome"); // Show MTMBHome when homeLabel is clicked
			}
		});

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