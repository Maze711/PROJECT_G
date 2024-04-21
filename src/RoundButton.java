import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class RoundButton extends JButton {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int cornerRadius;
	    private Color backgroundColor;

	    public RoundButton(String text, int cornerRadius, Color backgroundColor) {
	        super(text);
	        this.cornerRadius = cornerRadius;
	        this.backgroundColor = backgroundColor;
	        setContentAreaFilled(false); // Make the button transparent
	        setFocusPainted(false); // Remove the focus border
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        // Changes colors when clicked
	        if (getModel().isPressed()) {
	            g2.setColor(backgroundColor.darker());
	        } else if (getModel().isRollover()) {
	            g2.setColor(backgroundColor.brighter());
	        } else {
	            g2.setColor(backgroundColor);
	        }
	        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
	        super.paintComponent(g2);
	        g2.dispose();
	    }

	    @Override
	    protected void paintBorder(Graphics g) {
	        // No border needed
	    }

	    @Override
	    public Dimension getPreferredSize() {
	        return super.getPreferredSize();
	    }
}