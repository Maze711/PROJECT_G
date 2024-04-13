import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;

public class HomePage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePage() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(0, 0, 293, 778);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(36, 45, 135, 71);
		panel.add(lblNewLabel);

	}
}
