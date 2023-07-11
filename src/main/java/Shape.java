import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

/**
 * The Shape class is an abstract class representing a shape to be drawn on a graphics object. It provides methods to
 * set the position, size, color and text of the shape, as well as to check if a point is inside the shape, move the
 * shape, and retrieve information about the shape. This class implements the Decorated interface, allowing shapes to be
 *
 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public abstract class Shape implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    int x1;
    int y1;

    int WIDTH;
    int HEIGHT;
    Color c;
    public String text;
    private boolean flag;
    private boolean isTextDefined;

    /**
     * Constructs a new Shape object with the specified position, size and color.
     *
     * @param x1     the x-coordinate of the shape's top-left corner
     * @param y1     the y-coordinate of the shape's top-left corner
     * @param WIDTH  the width of the shape
     * @param HEIGHT the height of the shape
     * @param c      the color of the shape
     */
    public Shape(int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        this.x1 = x1;
        this.y1 = y1;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.c = c;
        text = "";
    }

    /**
     * Draws the shape on the specified graphics object.
     *
     * @param g the graphics object to draw the shape on
     */
    public abstract void draw(Graphics g);

    /**
     * Checks if the specified point is inside the shape.
     *
     * @param point the point to check
     * @return true if the point is inside the shape, false otherwise
     */
    public boolean contains(Point point) {
        return point.getX() >= x1 && point.getX() <= WIDTH && point.getY() >= y1 && point.getY() <= HEIGHT;
    }

    /**
     * Moves the shape to the specified position.
     *
     * @param x1 the x-coordinate of the new position
     * @param y1 the y-coordinate of the new position
     */
    public void move(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
     * Sets the position of the shape to the specified coordinates.
     *
     * @param x the x-coordinate of the new position
     * @param y the y-coordinate of the new position
     */
    public void setLocation(int x, int y, int WIDTH, int HEIGHT) {
        this.x1 = x;
        this.y1 = y;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
    }

    /**
     * Returns the x-coordinate of the shape's top-left corner.
     *
     * @return the x-coordinate of the shape's top-left corner
     */
    public int getX() {
        return x1;
    }

    /**
     * Returns the y-coordinate of the shape's top-left corner.
     *
     * @return the y-coordinate of the shape's top-left corner
     */
    public int getY() {
        return y1;
    }
    
    public int getWIDTH() {
    	return WIDTH;
    }
    
    public int getHEIGHT() {
    	return HEIGHT;
    }

    /**
     * Returns the flag value of the shape.
     *
     * @return the flag value of the shape
     */
    public boolean getFlag() {
        return flag;
    }

    /**
     * Sets the flag value of the Shape object.
     *
     * @param flag a boolean value to set the flag variable of the Shape object.
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    /**
     * Checks if the text is defined for the Shape object.
     *
     * @return a boolean value indicating if the text is defined for the Shape object.
     */
    public boolean isTextDefined() {
        return isTextDefined;
    }

    /**
     * Sets the text defined value of the Shape object.
     *
     * @param textDefined a boolean value to set the isTextDefined variable of the Shape object.
     */
    public void setTextDefined(boolean textDefined) {
        isTextDefined = textDefined;
    }

    /**
     * Sets the text of the Shape object.
     *
     * @param text a string value to set the text variable of the Shape object.
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets the text of the Shape object.
     *
     * @return the text variable of the Shape object as a string.
     */
    public String getText() {
        return text;
    }
    
}
