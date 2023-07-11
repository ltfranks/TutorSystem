import java.awt.*;
import java.util.Stack;

public class LineDecorator extends Line {
    Stack<Shape> connect = new Stack<Shape>();
    Line decoratedLine;
    LineDecorator(Line decoratedLine,Shape first, Shape second) {
        super(first, second);
        this.decoratedLine = decoratedLine;
        connect.push(first);
        connect.push(second);
    }

    public int[] calculateCords(int startx, int starty, int endx, int endy)
    {
        return this.decoratedLine.calculateCords(startx,starty,endx,endy);
    }

    @Override
    public void draw(Graphics g) {
        this.decoratedLine.draw(g);
    }
    public Point center(Shape x) {
        return this.decoratedLine.center(x);
    }
}
