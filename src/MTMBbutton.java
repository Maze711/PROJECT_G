import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class MTMBbutton extends JLabel implements ActionListener {
    JLabel Home;
    JLabel Records;
    JLabel Incoming;
    JLabel Releasing;
    JLabel Logout;

    public MTMBbutton(){
        Home = new JLabel("Home");
        Records = new JLabel("Records");
        Incoming = new JLabel("Incoming");
        Releasing = new JLabel("Releasing");
        Logout = new JLabel("Logout");

        setLabelProperties(Home, 100, 277);
        setLabelProperties(Records, 100, 333);
        setLabelProperties(Incoming, 100, 386);
        setLabelProperties(Releasing, 100, 445);
        setLabelProperties(Logout, 100, 543);

        Home.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            }
        });
    }

    public static void main(String[] args) {
    	JLabel buttonLabel = Home();
    	buttonLabel.addMouseListener((MouseListener) new MouseListener(){
    		public void mousePressed(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				
			}

			public void mouseReleased(MouseEvent e) {
				
			}

			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}

    }
    
    private static JLabel Home() {
		return null;
	}

	private void setLabelProperties(JLabel label, int x, int y) {
        label.setBounds(x, y, 120, 36);
        label.setForeground(Color.WHITE);
    
    		}
    	}
    }	
}