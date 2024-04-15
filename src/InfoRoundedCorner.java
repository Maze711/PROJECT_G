import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class InfoRoundedCorner extends JPanel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int cornerRadius;
    private Color startColor;
    private Color endColor;

    public InfoRoundedCorner(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        this.startColor = Color.decode("#00537A");
        this.endColor = Color.decode("#0C1F34");
        setOpaque(false);
    }

    public InfoRoundedCorner(int cornerRadius, Color startColor, Color endColor) {
        this.cornerRadius = cornerRadius;
        this.startColor = startColor;
        this.endColor = endColor;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Create a gradient paint from top to bottom
        GradientPaint gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);

        // Set the paint to the graphics context
        g2d.setPaint(gradientPaint);

        // Fill the rounded rectangle with the gradient paint
        g2d.fillRoundRect(0, 0, width - 1, height - 1, cornerRadius, cornerRadius);
        g2d.dispose();
    }
}
