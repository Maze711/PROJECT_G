import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Color;

public class RoundTxtField extends JTextField {
    private static final long serialVersionUID = 1L;
    
    private int radius;
    private Color strokeColor;
    private int strokeWidth;

    public RoundTxtField(int radius, Color strokeColor, int strokeWidth) {
        this.radius = radius;
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        super.paintComponent(graphics);
        graphics.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(strokeColor);
        graphics.setStroke(new BasicStroke(strokeWidth));
        graphics.drawRoundRect(strokeWidth / 2, strokeWidth / 2, getWidth() - strokeWidth - 1, getHeight() - strokeWidth - 1, radius, radius);
        graphics.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 30); // Adjust height as needed
    }
}
