import java.awt.Color;
import java.awt.Graphics;

/**
 * An abstract class representing a shape decorator.
 * Extends the Shape class and provides functionality to decorate shapes.
 */
public abstract class ShapeDecorator extends Shape {
    protected Shape decoratedShape;

    /**
     * Constructs a ShapeDecorator object with the specified parameters.
     *
     * @param decoratedShape The shape to be decorated.
     * @param x1             The x-coordinate of the shape.
     * @param y1             The y-coordinate of the shape.
     * @param WIDTH          The width of the shape.
     * @param HEIGHT         The height of the shape.
     * @param c              The color of the shape.
     */
    public ShapeDecorator(Shape decoratedShape, int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        super(x1, y1, WIDTH, HEIGHT, c);
        this.decoratedShape = decoratedShape;
    }

    /**
     * Draws the decorated shape using the provided graphics object.
     *
     * @param g The graphics object to draw the shape.
     */
    @Override
    public void draw(Graphics g) {
        decoratedShape.draw(g);
    }

    /**
     * Retrieves the x-coordinate of the decorated shape.
     *
     * @return The x-coordinate of the shape.
     */
    @Override
    public int getX() {
    	return decoratedShape.getX();
    }

    /**
     * Retrieves the y-coordinate of the decorated shape.
     *
     * @return The y-coordinate of the shape.
     */
    @Override
    public int getY() {
    	return decoratedShape.getY();
    }

    /**
     * Retrieves the width of the decorated shape.
     *
     * @return The width of the shape.
     */
    @Override
    public int getWIDTH() {
    	return decoratedShape.getWIDTH();
    }

    /**
     * Retrieves the height of the decorated shape.
     *
     * @return The height of the shape.
     */
    @Override
    public int getHEIGHT() {
    	return decoratedShape.getHEIGHT();
    }

    /**
     * Sets the location and size of the decorated shape using the specified parameters.
     *
     * @param x      The new x-coordinate of the shape.
     * @param y      The new y-coordinate of the shape.
     * @param WIDTH  The new width of the shape.
     * @param HEIGHT The new height of the shape.
     */
    @Override
    public void setLocation(int x, int y, int WIDTH, int HEIGHT) {
    	decoratedShape.setLocation(x, y, WIDTH, HEIGHT);
    }
}

    
