import java.awt.*;

public class Circle extends Shape {

    /**
     * The Circle class represents a circle shape that extends from the Shape class. It inherits the properties
     * and methods of the Shape class and overrides the draw method to draw a circle using the given coordinates and
     * color.
     *
     * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
     */
    public Circle(int x1, int y1, int WIDTH, int HEIGHT, Color c) {
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
        g2d.drawOval(super.x1, super.y1, 100, 100);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Begin", super.x1 + 35, super.y1 + 55);
    }
}