import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HomePage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public HomePage() {
		setLayout(null);
		
//		CUSTOM FONT FAMILY STYLE
		Font PrimaryFont = FontLoader.getFont("Primary", 64);
		Font SecondaryFont = FontLoader.getFont("Secondary", 24);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 48); // FONT SIZE 48
		
		// Background of the Navigation
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\admin\\git\\PROJECT_G\\Resources\\Images\\MTMBLogo.png"));
		lblNewLabel_1.setBounds(52, 38, 132, 147);
		add(lblNewLabel_1);
		
		//MTMB Icon
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\admin\\git\\PROJECT_G\\Resources\\Images\\BG info.png"));
		lblNewLabel_2.setBounds(0, 0, 293, 768);
		add(lblNewLabel_2);
		
		//Panel for the Header
		JPanel panel = new JPanel();
		panel.setBounds(294, 0, 730, 70);
		add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Welcome Back");
		lblNewLabel.setBounds(30, 23, 189, 36);
		lblNewLabel.setFont(SecondaryFont);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\admin\\git\\PROJECT_G\\Resources\\Icons\\Waving Hand Emoji.png"));
		lblNewLabel_3.setBounds(228, 25, 32, 32);
		panel.add(lblNewLabel_3);
		

	}
}
