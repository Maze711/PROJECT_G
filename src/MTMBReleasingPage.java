import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class MTMBReleasingPage extends JPanel {

	private JFrame frame;
	DefaultTableModel model;
	private JTable table;
	private JTextField SearchBar;
	private final MTMBDBCONN conn = new MTMBDBCONN();

	public void refresh() {
		fetchData(); // Update table data from the database
	}

	public MTMBReleasingPage() {
		initialize();
	}

	private void initialize() {

//		CUSTOM FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48);
		Font Bold = FontLoader.getFont("Bold", 28);
		Font Bold2 = FontLoader.getFont("Bold", 16);
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(-41, 0, 1069, 768);
		panel.setLayout(null);
		add(panel);

		JPanel recordPanel = new JPanel();
		recordPanel.setBounds(42, 11, 736, 768);
		panel.add(recordPanel);
		recordPanel.setLayout(null);

		// Header
		JPanel insideRecordPanel = new JPanel();
		insideRecordPanel.setBounds(0, 0, 736, 70);
		recordPanel.add(insideRecordPanel);
		insideRecordPanel.setLayout(null);

		JLabel recordLabel = new JLabel("Release");
		recordLabel.setBounds(30, 23, 189, 36);
		recordLabel.setFont(PrimaryEBFont);
		insideRecordPanel.add(recordLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 716, 627);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		recordPanel.add(scrollPane);

		table = new JTable();
		table.setShowHorizontalLines(false);
		model = new DefaultTableModel();
		Object[] column = { "Ctrl No.", "Type", "Plate No.", "Color", "Date", "Status" };
		model.setColumnIdentifiers(column);
		table.setModel(model);
		table.setEnabled(false);
		table.setFont(Bold2);
		table.setForeground(new Color(101, 95, 95));
		table.getTableHeader().setReorderingAllowed(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < column.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		// Add a MouseWheelListener to both the table and the scroll pane
		MouseWheelListener mouseWheelListener = new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int notches = e.getWheelRotation();
				JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
				// Increase scrolling speed by a factor of 10
				verticalScrollBar
						.setValue(verticalScrollBar.getValue() + (verticalScrollBar.getUnitIncrement() * notches * 10));
			}
		};
		table.addMouseWheelListener(mouseWheelListener);
		scrollPane.addMouseWheelListener(mouseWheelListener);

		JTableHeader header = table.getTableHeader();
		header.setFont(Bold2);
		header.setBackground(new Color(11, 30, 51));
		header.setForeground(Color.WHITE);
		table.setRowHeight(50);
		table.setFocusable(true);
		table.setTableHeader(header);

		// Add the table to the scroll pane
		scrollPane.setViewportView(table);

		JButton releaseButton = new RoundButton("Release", 16, Color.decode("#FFBA42"));
		releaseButton.setBounds(395, 81, 105, 38);
		releaseButton.setFont(Bold2);
		releaseButton.setForeground(new Color(11, 30, 51));
		recordPanel.add(releaseButton);

		releaseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MTMBReleasePop releasePop = new MTMBReleasePop();
				releasePop.showFrame();

			}
		});

		JButton filterButton = new RoundButton("Filter", 16, Color.decode("#D3D9E0"));
		filterButton.setBounds(510, 81, 90, 38);
		filterButton.setFont(Bold2);
		filterButton.setForeground(new Color(11, 30, 51));
		recordPanel.add(filterButton);

		JButton importButton = new RoundButton("Export", 16, Color.decode("#00537A"));
		importButton.setBounds(609, 81, 117, 38);
		importButton.setFont(Bold2);
		importButton.setForeground(Color.WHITE);
		recordPanel.add(importButton);

		SearchBar = new RoundTxtField(18, new Color(132, 132, 132), 1);
		SearchBar.setText("Search");
		SearchBar.setBounds(10, 81, 292, 38);
		SearchBar.setFont(Bold2);
		recordPanel.add(SearchBar);
		SearchBar.setColumns(10);

		fetchData();
	}

	private void fetchData() {
		try {
			Connection connection = conn.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM 2024mtmbrecord");
			// Clear existing table data
			model.setRowCount(0);
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
