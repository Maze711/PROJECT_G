package AdminFolder;
import java.awt.*;
import javax.swing.*;

import CustomClassLoader.FontLoader;
import DatabaseConnection.MTMBDBCONN;
import UserFolder.MTMBLogin;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MTMBAdminMainFrame {
	private JFrame frame;
	private final MTMBDBCONN conn = new MTMBDBCONN();
	private JPanel cards;
	private CardLayout cardLayout;
	private MTMBIncomingPage incomingPanel;
	private MTMBReleasingPage releasePanel;
	private JLabel sliderLabel;
	private JPanel panel;
	private MTMBHome homePanel;
	private String username;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBAdminMainFrame window = new MTMBAdminMainFrame("username");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void showWindowHome() {
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Muntinlupa Traffic Management Bureau");
	}

	public MTMBAdminMainFrame(String username) {
		this.username = username;
		initialize();

		// Add a window listener to the frame
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				// Show MTMBHome when the frame is opened
				cardLayout.show(cards, "MTMBHome");
			}
		});
	}

	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 1045, 805);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(0);

		// CUSTOM FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 16);
		Font SemiB24 = FontLoader.getFont("SemiB", 24);
		Font SemiB22 = FontLoader.getFont("SemiB", 22);
		Font Bold = FontLoader.getFont("Bold", 42);
		Font Bold28 = FontLoader.getFont("Bold16", 28);
		Font Bold2 = FontLoader.getFont("Bold", 16);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);

		ImageIcon sliderImg = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Icons/Slider.png"));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1028, 768);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// Navigation Panel
		JPanel navigationPanel = new JPanel();
		navigationPanel.setBounds(0, 0, 293, 768);
		panel.add(navigationPanel);
		navigationPanel.setLayout(null);

		ImageIcon logo = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Images/MTMBLogo.png"));

		sliderLabel = new JLabel("");
		sliderLabel.setBounds(264, 267, 79, 58);
		navigationPanel.add(sliderLabel);
		sliderLabel.setIcon(sliderImg);

		JLabel MTMBLogo = new JLabel("");
		MTMBLogo.setBounds(52, 38, 132, 147);
		MTMBLogo.setIcon(logo);
		navigationPanel.add(MTMBLogo);

		JLabel Dashboard = new JLabel("Dashboard");
		Dashboard.setBounds(52, 220, 141, 36);
		Dashboard.setForeground(new Color(255, 255, 255));
		Dashboard.setFont(SemiB22);
		navigationPanel.add(Dashboard);

		JLabel homeLabel = new JLabel("Home");
		homeLabel.setBounds(100, 277, 74, 36);
		homeLabel.setForeground(Color.WHITE);
		homeLabel.setFont(SemiB22);
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

		ImageIcon home = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Icons/IHome.png"));
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBounds(52, 269, 42, 42);
		lblNewLabel_5.setIcon(home);
		navigationPanel.add(lblNewLabel_5);

		ImageIcon db = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Icons/Database.png"));
		JLabel lblNewLabel_5_1 = new JLabel("");
		lblNewLabel_5_1.setBounds(52, 335, 42, 42);
		lblNewLabel_5_1.setIcon(db);
		navigationPanel.add(lblNewLabel_5_1);

		JLabel recordsLabel = new JLabel("Records");
		recordsLabel.setBounds(100, 341, 102, 36);
		recordsLabel.setForeground(Color.WHITE);
		recordsLabel.setFont(SemiB22);
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

		ImageIcon download = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Icons/Download.png"));
		JLabel lblNewLabel_5_1_1 = new JLabel("");
		lblNewLabel_5_1_1.setBounds(52, 395, 42, 42);
		lblNewLabel_5_1_1.setIcon(download);
		navigationPanel.add(lblNewLabel_5_1_1);

		JLabel incomingLabel = new JLabel("Incoming");
		incomingLabel.setBounds(100, 401, 120, 36);
		incomingLabel.setForeground(Color.WHITE);
		incomingLabel.setFont(SemiB22);
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

		ImageIcon upload = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Icons/Upload.png"));
		JLabel lblNewLabel_5_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1.setBounds(52, 458, 42, 42);
		lblNewLabel_5_1_1_1.setIcon(upload);
		navigationPanel.add(lblNewLabel_5_1_1_1);

		JLabel releasingLabel = new JLabel("Releasing");
		releasingLabel.setBounds(100, 464, 120, 36);
		releasingLabel.setForeground(Color.WHITE);
		releasingLabel.setFont(SemiB22);
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

		ImageIcon logout = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Icons/Logout.png"));
		JLabel lblNewLabel_5_1_1_1_1 = new JLabel("");
		lblNewLabel_5_1_1_1_1.setBounds(52, 560, 42, 42);
		lblNewLabel_5_1_1_1_1.setIcon(logout);
		navigationPanel.add(lblNewLabel_5_1_1_1_1);

		JLabel Logout = new JLabel("Logout");
		Logout.setBounds(100, 566, 120, 36);
		Logout.setForeground(Color.WHITE);
		Logout.setFont(SemiB22);
		Logout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Clear all data and close the window
				clearDataAndCloseWindow();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				Logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Logout.setCursor(Cursor.getDefaultCursor());
			}
		});

		navigationPanel.add(Logout);

		ImageIcon bgInfo = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Images/BG info.png"));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 293, 768);
		lblNewLabel.setIcon(bgInfo);
		navigationPanel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(292, -1, 736, 70);
		panel.add(panel_2);

		JLabel txtWelcome = new JLabel("Home");
		txtWelcome.setFont(PrimaryEBFont);
		txtWelcome.setBounds(30, 23, 189, 36);
		panel_2.add(txtWelcome);

		JLabel txtUsertype = new JLabel("Hello Admin: " + username);
		txtUsertype.setFont(Bold2);
		txtUsertype.setBounds(490, 26, 236, 36);
		panel_2.add(txtUsertype);

		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(292, 70, 735, 698);
		panel.add(panel_3);
		int impoundCount = getImpoundCount();

		int releaseCount = getReleaseCount();

		ImageIcon car = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Icons/Car.png"));
		JLabel ITotal = new JLabel("");
		ITotal.setIcon(car);
		ITotal.setBounds(270, 110, 135, 40);
		panel_3.add(ITotal);

		JLabel txtTotalVehicle = new JLabel(String.valueOf(impoundCount));
		txtTotalVehicle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalVehicle.setForeground(Color.WHITE);
		txtTotalVehicle.setFont(Bold28);
		txtTotalVehicle.setBounds(37, 96, 274, 49);
		panel_3.add(txtTotalVehicle);

		JLabel txtTotalDisplay = new JLabel("Total Vehicle Impounded ");
		txtTotalDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalDisplay.setForeground(Color.WHITE);
		txtTotalDisplay.setFont(Bold2);
		txtTotalDisplay.setBounds(37, 140, 274, 40);
		panel_3.add(txtTotalDisplay);

		JLabel txtTotalVehicle_2 = new JLabel(String.valueOf(releaseCount));
		txtTotalVehicle_2.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotalVehicle_2.setForeground(Color.WHITE);
		txtTotalVehicle_2.setFont(Bold28);
		txtTotalVehicle_2.setBounds(440, 96, 274, 49);
		panel_3.add(txtTotalVehicle_2);

		JLabel lblTotalVehicleReleased = new JLabel("Total Vehicle Released ");
		lblTotalVehicleReleased.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalVehicleReleased.setForeground(Color.WHITE);
		lblTotalVehicleReleased.setFont(Bold2);
		lblTotalVehicleReleased.setBounds(440, 140, 274, 40);
		panel_3.add(lblTotalVehicleReleased);

		JLabel txtCurrentMnt = new JLabel("April 2024");
		txtCurrentMnt.setFont(Bold28);
		txtCurrentMnt.setBounds(30, 16, 286, 40);
		panel_3.add(txtCurrentMnt);

		ImageIcon total = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Images/Total.png"));
		JLabel bgTotal_1 = new JLabel("");
		bgTotal_1.setIcon(total);
		bgTotal_1.setBounds(30, 67, 393, 125);
		panel_3.add(bgTotal_1);

		ImageIcon total1 = new ImageIcon(MTMBAdminMainFrame.class.getResource("/Images/Total-1.png"));
		JLabel bgTotal_2 = new JLabel("");
		bgTotal_2.setIcon(total1);
		bgTotal_2.setBounds(436, 67, 270, 125);
		panel_3.add(bgTotal_2);

		JLabel txtInfo = new JLabel("Daily Impounded Records");
        txtInfo.setFont(SemiB24);
        txtInfo.setBounds(31, 205, 361, 40);
        panel_3.add(txtInfo);
        
        ImageIcon chart = new ImageIcon(MTMBLogin.class.getResource("/Images/Chart.png"));
        JLabel Chart = new JLabel("");
        Chart.setIcon(chart);
        Chart.setBounds(30, 243, 672, 352);
        panel_3.add(Chart);

		// Create CardLayout and JPanel for cards
		cardLayout = new CardLayout();
		cards = new JPanel(cardLayout);
		cards.setBounds(292, 0, 736, 768);
		frame.getContentPane().add(cards);

		// Create and add MTMBHome panel
		MTMBHome homePanel = new MTMBHome(username);
		cards.add(homePanel, "MTMBHome");

		// Show MTMBHome panel by default
		cardLayout.show(cards, "MTMBHome");

		// Create and add other panels
		MTMBRecordPage recordPanel = new MTMBRecordPage();
		cards.add(recordPanel, "MTMBRecordPage");

		incomingPanel = new MTMBIncomingPage();
		cards.add(incomingPanel, "MTMBIncomingPage");

		releasePanel = new MTMBReleasingPage(this.username);
		cards.add(releasePanel, "MTMBReleasingPage");

		// Add mouse listeners to labels...

		// Show the window
		showWindowHome();

		// Add mouse listeners to labels
		homeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBHome"); // Show MTMBHome when homeLabel is clicked
				animateSlider(homeLabel.getY());

			}
		});

		recordsLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBRecordPage");
				recordPanel.setVisible(true); // Show the MTMBRecordPage panel
				animateSlider(recordsLabel.getY());

			}
		});

		incomingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBIncomingPage");
				animateSlider(incomingLabel.getY());

			}
		});

		releasingLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cardLayout.show(cards, "MTMBReleasingPage");
				animateSlider(releasingLabel.getY());

			}
		});
	}

	private void animateSlider(final int yPos) {
		final int targetY = yPos;
		final int startX = sliderLabel.getY();

		Thread animationThread = new Thread(() -> {
			int sliderY = startX;
			int step = (targetY < startX) ? -1 : 1; // Determine the direction of movement

			// Slide until very close to the target position
			while (Math.abs(sliderY - targetY) > 1) { // Adjust threshold as needed
				sliderY += step;
				sliderLabel.setBounds(264, sliderY, 79, 58);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// Set the slider to the exact position of the label
			sliderLabel.setBounds(264, targetY, 79, 58);
		});

		animationThread.start();
	}

	private void clearDataAndCloseWindow() {
		frame.setVisible(false);
		MTMBLogin show = new MTMBLogin();
		show.setVisible(true);
		show.setLocationRelativeTo(null);
	}

	private int getImpoundCount() {
		int count = 0;
		try (Connection connection = conn.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT COUNT(*) AS count FROM april WHERE Status = 'Impounded'")) {
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	private int getReleaseCount() {
		int count = 0;
		try (Connection connection = conn.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT COUNT(*) AS count FROM april WHERE Status = 'Released'")) {
			if (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}