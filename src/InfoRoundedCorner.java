import javax.swing.*;
import java.awt.*;
import java.io.Serializable;


public class InfoRoundedCorner extends JPanel implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	private int cornerRadius;

    public InfoRoundedCorner(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
        g2d.dispose();
    }
}

