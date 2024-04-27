 import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class MTMBReleasePop {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MTMBReleasePop window = new MTMBReleasePop();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		


	public void showFrame() {
		frame.setVisible(true);
		
	}
	
	public MTMBReleasePop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
 
		
		//FONTS
		Font PrimaryFont = FontLoader.getFont("Primary", 20);
		Font SemiB = FontLoader.getFont("SemiB", 13);
		Font Bold = FontLoader.getFont("PrimaryEB32", 17);
		Font PrimaryEB48Font = FontLoader.getFont("PrimaryEB32", 15); 
		Font SecondaryFont = FontLoader.getFont("Secondary", 15);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Release Vehicle");
		lblNewLabel.setFont(PrimaryFont);
		lblNewLabel.setBounds(10, 32, 174, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new RoundButton("Search", 15, Color.decode("#FFFFFF"));
		btnNewButton_1.setForeground(new Color(11, 30, 51));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setBounds(292, 95, 101, 39);
		btnNewButton_1.setFont(Bold);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_3 = new RoundButton("Save", 10, Color.decode("#0B1E33"));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MTMBReleasingPage release = new MTMBReleasingPage();
				dispose();
			}
		});
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBounds(325, 236, 101, 39);
		btnNewButton_3.setFont(Bold);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new RoundButton("Cancel", 10, (new Color(255, 186,66)));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
			}
		});
		btnNewButton_4.setForeground(new Color(11, 30, 51));
		btnNewButton_4.setBackground(new Color(255, 186, 66));
		btnNewButton_4.setBounds(208, 236, 101, 39);
		btnNewButton_4.setFont(Bold);
		frame.getContentPane().add(btnNewButton_4);
		
		textField = new RoundTxtField(10, new Color(0x0B1E33), 3);
		textField.setForeground(new Color(11, 30, 51));
		textField.setBackground(new Color(211, 211, 211));
		textField.setBounds(10, 95, 272, 39);
		textField.setFont(SecondaryFont);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new RoundTxtField(10, new Color(0x0B1E33), 3);
		textField_1.setForeground(new Color(11, 30, 51));
		textField_1.setBackground(new Color(211, 211, 211));
		textField_1.setBounds(10, 187, 350, 39);
		textField_1.setFont(SecondaryFont);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Control No.");
		lblNewLabel_1.setBounds(10, 73, 94, 16);
		lblNewLabel_1.setFont(SemiB);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Change Status");
		lblNewLabel_2.setBounds(10, 136, 150, 21);
		lblNewLabel_2.setFont(PrimaryEB48Font);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(10, 156, 134, 31);
		lblNewLabel_3.setFont(SemiB);
		frame.getContentPane().add(lblNewLabel_3);
		
		
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}

}
