import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * This is the line class that helps create lines between two shapes
 *
 * @author Anthony Colin, Shiv Panchal, Luke Fanguna, Nathan Choi, Reza Mousakhani, Luke Franks
 */
public class RegularLine extends Line implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    Stack<Shape> connect = new Stack<Shape>();

    /**
     * This is the constructor that helps initialize variables for the Line class
     *
     * @param first  shape to create the connection
     * @param second shape to create the connection
     */
    RegularLine(Shape first, Shape second)
    {
        super(first,second);
        connect.push(first);
        connect.push(second);
    }

    /**
     * This is the draw class that uses Graphics g to draw a line with an arrow head in the middle to show direction.
     * Also, it checks if the connection starts with a diamond and if it does, we add text to it
     *
     * @param g the Graphics object to draw on
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        Point start = center(connect.get(0));
        Point end = center(connect.get(1));
        int[] cords = calculateCords(start.x,start.y,end.x,end.y);
        int startx  = cords[0],starty = cords[1], endx = cords[2],endy = cords[3];

        g.drawLine(startx, starty, endx, endy);

        // Calculate the midpoint and angle of the line segment
        int midX = (startx + endx) / 2, midY = (starty + endy) / 2;
        double angle = Math.atan2(endy - starty, endx - startx);

        // Calculate the coordinates of the arrowhead vertices
        int arrowSize = 10, arrowAngle = 30;
        double arrowLength = arrowSize / Math.sin(Math.toRadians(arrowAngle));
        int arrowX1 = (int) (endx - arrowLength * Math.cos(angle - Math.toRadians(arrowAngle)));
        int arrowY1 = (int) (endy - arrowLength * Math.sin(angle - Math.toRadians(arrowAngle)));
        int arrowX2 = (int) (endx - arrowLength * Math.cos(angle + Math.toRadians(arrowAngle)));
        int arrowY2 = (int) (endy - arrowLength * Math.sin(angle + Math.toRadians(arrowAngle)));

        // Draw the arrowhead polygon
        int[] arrowX = {endx, arrowX1, arrowX2};
        int[] arrowY = {endy, arrowY1, arrowY2};
        g.fillPolygon(arrowX, arrowY, 3);

        g.drawString(" ", midX, midY);
    }

    public int[] calculateCords(int startx, int starty, int endx, int endy)
    {
        if (starty+50 < endy && starty-50 > endy)
        {
            endx = endx;
            endy = endy;
        }
        else if (starty+50 < endy)
        {
            endy = endy-40;
        }
        else
        {
            endy = endy+40;
        }
        if (startx + 50 > endx && startx - 50 < endx)
        {
            if (starty + 50 < endy)
                starty = starty + 50;
            else if (starty - 50 > endy)
                starty = starty - 50;
        }
        else if (startx + 50 < endx)
        {
            startx = startx + 50;
        }
        else if (startx - 50 > endx)
        {
            startx = startx - 50;
        }
        int[] cords = {startx,starty,endx,endy};
        return cords;
    }

    /**
     * This helps the line to point to the center of the shape
     *
     * @param x this is the shape object which we are making the center of
     * @return return a point object that is the center of the shapes
     */
    public Point center(Shape x) {
        return new Point(x.x1+50, x.y1+50);
    }
}
