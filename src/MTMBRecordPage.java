import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class MTMBRecordPage extends JPanel {

	public MTMBRecordPage() {
		initialize();
	}

	private void initialize() {
		setBounds(292, 0, 736, 768);
		setLayout(null);

		JPanel RecordPanel = new JPanel();
		RecordPanel.setBounds(0, 0, 736, 768);
		setLayout(null);

		// Header
		JPanel InsideRecordPanel = new JPanel();
		InsideRecordPanel.setBounds(0, 0, 736, 70);
		InsideRecordPanel.setLayout(null);

		JLabel Record = new JLabel("Records");
		Record.setBounds(30, 23, 189, 36);
		Record.setFont(new Font("Arial", Font.BOLD, 24));
		InsideRecordPanel.add(Record);
		RecordPanel.setLayout(null);

		JLabel lblYear = new JLabel("Year");
		lblYear.setFont(new Font("Arial", Font.BOLD, 28));
		lblYear.setBounds(55, 50, 61, 33);
		RecordPanel.add(lblYear);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 82, 716, 675);
		RecordPanel.add(scrollPane);

		add(RecordPanel);

		RoundButton btnNewButton = new RoundButton("Create New +", 8, Color.decode("#FFBA42"));
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton.setBounds(555, 11, 140, 27);
		RecordPanel.add(btnNewButton);

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
