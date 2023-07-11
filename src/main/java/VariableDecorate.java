import java.awt.*;

/**
 * The VariableDecorate class draws the lines onto a rectangle for the variable object using a decorator.
 *
 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */

public class VariableDecorate extends ShapeDecorator {

    /**
     * Constructs a VariableDecorate object with the specified parameters.
     *
     * @param decoratedShape The shape to be decorated.
     * @param x1             The x-coordinate of the shape.
     * @param y1             The y-coordinate of the shape.
     * @param WIDTH          The width of the shape.
     * @param HEIGHT         The height of the shape.
     * @param c              The color of the shape.
     */
    public VariableDecorate(Shape decoratedShape, int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        super(decoratedShape, x1, y1, WIDTH, HEIGHT, c);
    }

    /**
     * Draws the decorated shape and variable lines using the provided graphics object.
     *
     * @param g The graphics object to draw the shape and variable lines.
     */
    @Override
    public void draw(Graphics g) {
        decoratedShape.draw(g);
        drawVariableLines(g);
    }

    /**
     * Sets the location and size of the decorated shape and variable lines using the specified parameters.
     *
     * @param x The new x-coordinate of the shape.
     * @param y The new y-coordinate of the shape.
     * @param w The new width of the shape.
     * @param h The new height of the shape.
     */
    @Override
    public void setLocation(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.WIDTH = w;
        this.HEIGHT = h;
        decoratedShape.setLocation(x, y, w, h);
    }

    private void drawVariableLines(Graphics g) {
        int x = super.x1 + 20; // Start drawing text at x = x1 + 10
        int y = super.y1 + g.getFontMetrics().getAscent() + 35; // Start drawing text at y = y1 + ascent + 45
        int maxWidth = 75; // Maximum width of text that will fit inside the shape

        FontMetrics fm = g.getFontMetrics();
        String[] lines = text.split("\n");
        for (String line : lines) {
            int lineWidth = 0;
            StringBuilder currentLine = new StringBuilder();
            for (char c : line.toCharArray()) {
                int charWidth = fm.charWidth(c);
                if (lineWidth + charWidth > maxWidth) {
                    // Draw the current line and move to the next line
                    g.drawString(currentLine.toString().trim(), x, y);
                    y += fm.getHeight();
                    lineWidth = 0;
                    currentLine = new StringBuilder();
                }
                lineWidth += charWidth;
                currentLine.append(c);
            }
            // Draw the last line
            g.drawString(currentLine.toString().trim(), x, y);
            y += fm.getHeight();
        }

        int width = 100;
        int height = 100;

        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2));
        g.drawLine(super.x1 + 10, super.y1 + 10, super.x1 + width - 10, super.y1 + 10);
        g.drawLine(super.x1 + 10, super.y1 + 10, super.x1 + 10, super.y1 + (height + 5) - 10);
        g2d.setStroke(oldStroke);
    }
}