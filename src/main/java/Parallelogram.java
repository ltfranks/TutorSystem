import java.awt.*;

/**
 * The Parallelogram class represents a parallelogram shape that extends from the Shape class. It inherits the properties
 * and methods of the Shape class and overrides the draw method to draw a parallelogram using the given coordinates and
 * color.
 *
 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public class Parallelogram extends Shape {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 100;

    public Parallelogram(int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        super(x1, y1, WIDTH, HEIGHT, c);
    }

    /**
     * Draws the diamond on the Graphics object.
     *
     * @param g the Graphics object to draw the diamond on.
     */


    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(3));

        int yDiff = (int) (Math.sin(Math.toRadians(45)) * HEIGHT); // calculate difference in y-coordinates
        int[] xPoints = calculateX(yDiff);
        int[] yPoints = calculateY();
        Polygon parallelogram = new Polygon(xPoints, yPoints, 4);
        g2d.fill(parallelogram);
        g.setColor(Color.BLACK);

        // Start drawing text at x = x1 + 10 and y = y1 + fm.getAscent() + 45
        int x = super.x1 - 10;
        int y = super.y1 + g.getFontMetrics().getAscent() + 25;
        int maxWidth = 115; // Maximum width of text that will fit inside the shape

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


    public int[] calculateX(int yDiff) {
        return new int[] {super.x1, super.x1 + WIDTH, xDiff(yDiff), super.x1 - yDiff};
    }

    public int[] calculateY() {
        return new int[] {super.y1, super.y1, super.y1 + HEIGHT, super.y1 + HEIGHT};
    }

    public int xDiff(int yDiff) {
        return super.x1 + WIDTH - yDiff;
    }
}
