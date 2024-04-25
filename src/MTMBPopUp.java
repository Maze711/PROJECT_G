import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MTMBPopUp  {

	/**
	 * 
	 */
	private static JFrame frmMtmb;
	private JFrame signUpFrame;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBPopUp window = new MTMBPopUp(frmMtmb);
					window.frmMtmb.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	// to connect your class to other class
	public void showWindow() {
		// TODO Auto-generated method stub
			JFrame frmMtmb2 = frmMtmb;
			frmMtmb2.show();
	}
	public MTMBPopUp(JFrame signUpFrame) {
		  this.signUpFrame = signUpFrame;
		initialize();
		
	}
	void initialize() {
		frmMtmb = new JFrame();
		frmMtmb.setTitle("MTMB");
		frmMtmb.setBounds(100, 100, 320, 230);
		frmMtmb.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmMtmb.getContentPane().setLayout(null);
		frmMtmb.setResizable(false);
		frmMtmb.getContentPane().setLayout(null);
		frmMtmb.setLocationRelativeTo(null);
		frmMtmb.setUndecorated(true); 
		
		Font PrimaryFont = FontLoader.getFont("Primary", 30);
		Font SecondaryFont = FontLoader.getFont("Secondary", 15);
		Font PrimaryEBFont = FontLoader.getFont("PrimaryEB32", 24);
		Font SemiB = FontLoader.getFont("SemiB", 24);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 24); // FONT SIZE 48
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 323, 232);
		frmMtmb.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(133, 16, 54, 30);
		lblNewLabel.setIcon(new ImageIcon("Resources/Icons/Check.png"));
		//lblNewLabel.setIcon(new ImageIcon("Resources\\Images\\Upload.png"));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SUCCESS");
		lblNewLabel_1.setBounds(92, 81, 210, 47);
		lblNewLabel_1.setBackground(new Color(11, 30, 51));
		lblNewLabel_1.setFont(PrimaryFont);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Added a new user!");
		lblNewLabel_2.setFont(SecondaryFont);
		lblNewLabel_2.setBackground(new Color(11, 30, 51));
		lblNewLabel_2.setBounds(92, 121, 145, 39);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton = new RoundButton("CONFIRM",  40, Color.decode("#0B1E33"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Link of popUp to Login
				
				MTMBLogin link = new MTMBLogin();
				
				link.setVisible(true);
				link.setLocationRelativeTo(null);
		        frmMtmb.dispose();
		        signUpFrame.dispose();
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(11, 30, 51));
		btnNewButton.setFont(PrimaryEB48Font);
		btnNewButton.setBounds(10, 170, 302, 44);
		panel.add(btnNewButton);
	}
}



