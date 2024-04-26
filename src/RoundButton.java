import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class RoundButton extends JButton {
    private static final long serialVersionUID = 1L;

    private int cornerRadius;
    private Color backgroundColor;


    public RoundButton(String text, int cornerRadius, Color backgroundColor) {
        super(text);
        this.cornerRadius = cornerRadius;
        this.backgroundColor = backgroundColor; 
 
        
        
        setContentAreaFilled(false); // Make the button transparent
        setFocusPainted(false); // Remove the focus border

        // Add a mouse listener to change cursor to hand cursor when hovered
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(Cursor.getDefaultCursor());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(backgroundColor);
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
