
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JButton;

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
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(269, 327, 24, 42);
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
		
		JButton btnNewButton = new JButton("New button");
        btnNewButton.setBounds(562, 81, 164, 35);
        RecordPanel.add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 125, 716, 632);
        RecordPanel.add(scrollPane);

        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel viewport = (JPanel) scrollPane.getViewport().getView();
                if (viewport == null || viewport.getComponentCount() == 0) {
                    viewport = new JPanel();
                    viewport.setLayout(new GridLayout(0, 2));
                    scrollPane.setViewportView(viewport);
                }

                JPanel container = new JPanel();
                container.setPreferredSize(new Dimension(301, 214));
                container.setLayout(new BorderLayout());

                // Add a border to the panel
                Border border = BorderFactory.createEmptyBorder(10, 10, 10, 10);
                container.setBorder(border);

                JLabel label = new JLabel("Component " + (viewport.getComponentCount() + 1));
                container.add(label, BorderLayout.CENTER);
                viewport.add(container);

                viewport.revalidate();
                viewport.repaint();

                SwingUtilities.invokeLater(() -> {
                    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                    verticalScrollBar.setValue(verticalScrollBar.getMaximum());
                });

                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            }
        });
	}
}
