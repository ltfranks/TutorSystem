import java.awt.*;
import java.util.ArrayList;

/**
 * The Rectangle class represents a rectangle shape that extends from the Shape class. It inherits the properties
 * and methods of the Shape class and overrides the draw method to draw a rectangle using the given coordinates and
 * color.

 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public class Rectangle extends Shape {

    /**
     * Constructor for creating a Diamond object with the specified x1, y1, WIDTH, HEIGHT, and color values.
     * @param x1 the x-coordinate of the top left corner of the diamond.
     * @param y1 the y-coordinate of the top left corner of the diamond.
     * @param WIDTH the width of the diamond.
     * @param HEIGHT the height of the diamond.
     * @param c the color of the diamond.
     */
    public Rectangle (int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        super(x1, y1, WIDTH, HEIGHT, c);
    }

    /**
     * Draws the rectangle on a Graphics object.
     * @param g the Graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(super.x1, super.y1, 100, 100);
        g.setColor(Color.BLACK);
        textToNewLine(g);
    }

    public void textToNewLine(Graphics g){
        FontMetrics fm = g.getFontMetrics();
        int x = super.x1 + 10; // Start drawing text at x = x1 + 35
        int y = super.y1 + fm.getAscent() + 35; // Start drawing text at y = y1 + ascent + 45
        int maxWidth = 88; // Maximum width of text that will fit inside the shape

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
}
