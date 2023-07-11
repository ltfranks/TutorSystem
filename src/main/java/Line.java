import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Stack;

public abstract class Line implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    Stack<Shape> connect = new Stack<>();
    Line(Shape first, Shape second) {
        connect.push(first);
        connect.push(second);
    }
    public abstract void draw(Graphics g);

    public abstract int[] calculateCords(int startx, int starty, int endx, int endy);
    public abstract Point center(Shape x);
}
