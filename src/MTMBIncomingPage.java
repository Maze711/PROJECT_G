import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JTextField;

public class MTMBIncomingPage {

	private JFrame frame;
	DefaultTableModel model;
	private JTable table;
	private final MTMBDBCONN conn = new MTMBDBCONN();
	private JTextField SearchBar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBIncomingPage window = new MTMBIncomingPage();
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
	public MTMBIncomingPage() {
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
		Font SemiB16 = FontLoader.getFont("SemiB", 16);
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

		JLabel Record = new JLabel("INCOMING");
		Record.setBounds(30, 23, 189, 36);
		Record.setFont(PrimaryEBFont);
		InsideRecordPanel.add(Record);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 716, 627);
		RecordPanel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel();
		Object[] column = { "Ctrl No.", "Type", "Plate No.", "Color", "Date", "Status" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		table.setEnabled(false);
		table.setFont(SemiB16);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(table);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(SemiB16);

		table.setTableHeader(header);

		JButton FilterButton = new JButton("Filter");
		FilterButton.setBounds(420, 81, 80, 38);
		FilterButton.setFont(SemiB16);
		RecordPanel.add(FilterButton);

		JButton AddButton = new JButton("ADD+");
		AddButton.setBounds(510, 81, 90, 38);
		AddButton.setFont(SemiB16);
		RecordPanel.add(AddButton);

		JButton importButton = new JButton("Import");
		importButton.setBounds(609, 81, 117, 38);
		importButton.setFont(SemiB16);
		importButton.addActionListener(e -> {
		    SwingUtilities.invokeLater(() -> {
		        MTMBImporter importer = new MTMBImporter();
		        importer.importExcelFile("Importer/gg.xlsx").thenRun(() -> {
		            System.out.println("Import completed successfully!");
		        });
		    });
		});

		RecordPanel.add(importButton);

		
		SearchBar = new JTextField();
		SearchBar.setText("Search");
		SearchBar.setBounds(10, 81, 292, 38);
		RecordPanel.add(SearchBar);
		SearchBar.setColumns(10);

		fetchData();
	}

	private void fetchData() {
		try {
			Connection connection = conn.getConnection(); // Assuming you have a method to get the connection
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 2024mtmbrecord");

			while (resultSet.next()) {
				int id = resultSet.getInt("CTRLNo");
				String type = resultSet.getString("Type");
				String plateno = resultSet.getString("PlateNo");
				String color = resultSet.getString("Color");
				String date = resultSet.getString("Date");
				String status = resultSet.getString("Status");

				model.addRow(new Object[] { id, type, plateno, color, date, status });
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
