import java.awt.*;

/**
 * The Diamond class is a subclass of the Shape class, representing a diamond shape that can be drawn on a graphics object.
 * It overrides the draw method of the Shape class to define how the diamond is drawn on the graphics object.
 * The Diamond class also inherits the x1, y1, WIDTH, HEIGHT, c, text, flag, isTextDefined variables, and methods from the Shape class.
 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public class Diamond extends Shape {

    private int lines = 0;
    /**
     * Constructor for creating a Diamond object with the specified x1, y1, WIDTH, HEIGHT, and color values.
     * @param x1 the x-coordinate of the top left corner of the diamond.
     * @param y1 the y-coordinate of the top left corner of the diamond.
     * @param WIDTH the width of the diamond.
     * @param HEIGHT the height of the diamond.
     * @param c the color of the diamond.
     */
    public Diamond (int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        super(x1, y1, WIDTH, HEIGHT, c);
    }

    /**
     * Draws the diamond on the Graphics object.
     * @param g the Graphics object to draw the diamond on.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(3));
        int diamondSize = 150;
        int[] xPoints = calculateCenterX(diamondSize);
        int[] yPoints = calculateCenterY(diamondSize);

        g2d.fillPolygon(xPoints, yPoints, 4);
        g.setColor(Color.BLACK);

        // Start drawing text at x = x1 + 10 and y = y1 + 45
        int x = super.x1 + 25;
        int y = super.y1 + 75;
        int maxWidth = 100; // Maximum width of text that will fit inside the shape

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
    }


    public int[] calculateCenterX(int diamondSize) {
        int centerX = super.x1 + diamondSize / 2;

        return new int[] {
                centerX,
                calculateRight(centerX, diamondSize),
                centerX,
                calculateLeft(centerX, diamondSize)
        };
    }

    public int[] calculateCenterY(int diamondSize) {
        int centerY = super.y1 + diamondSize / 2;

        return new int[] {
                calculateLeft(centerY, diamondSize),
                centerY,
                calculateRight(centerY, diamondSize),
                centerY
        };
    }

    public int calculateLeft(int center, int diamondSize) {
        return center - diamondSize / 2;
    }

    public int calculateRight(int center, int diamondSize) {
        return center + diamondSize / 2;
    }
}