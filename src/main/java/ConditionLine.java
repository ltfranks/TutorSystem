import java.awt.*;
import java.util.Stack;
/**
 * The ConditionLine class represents a decorator for a line that adds condition information.
 * It extends the LineDecorator class.
 */
public class ConditionLine extends LineDecorator {
    Stack<Shape> connect = new Stack<Shape>();
    Line decoratedLine;
    int count;
    /**
     * Constructs a ConditionLine object with the specified decorated line, shapes, and count.
     * @param decoratedLine The decorated line object.
     * @param first The first shape connected by the line.
     * @param second The second shape connected by the line.
     * @param count The count used to determine the condition.
     */
    public ConditionLine(Line decoratedLine,Shape first, Shape second, int count) {
        super(decoratedLine,first, second);
        this.decoratedLine = decoratedLine;
        this.count = count;
        connect.push(first);
        connect.push(second);
    }

    /**
     * Calculates the coordinates of the line based on the start and end points.
     * @param startx The x-coordinate of the start point.
     * @param starty The y-coordinate of the start point.
     * @param endx The x-coordinate of the end point.
     * @param endy The y-coordinate of the end point.
     * @return An array containing the calculated x and y coordinates.
     */
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
     * Calculates the center point of a shape.
     * @param x The shape for which to calculate the center point.
     * @return The center point of the shape.
     */
    @Override
    public void draw(Graphics g)
    {
        decoratedLine.draw(g);
        String cond;
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

        if (count % 2 == 0)
            cond = "True";
        else
            cond = "False";

        g.drawString(cond, midX, midY);
    }
    public Point center(Shape x) {
        return new Point(x.x1+50, x.y1+50);
    }
}
