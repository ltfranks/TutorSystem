import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class FillDecorator extends ShapeDecorator {

    public FillDecorator(Shape decoratedShape, int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        super(decoratedShape, x1, y1, WIDTH, HEIGHT, c);
    }

    @Override
    public void draw(Graphics g) {
        decoratedShape.draw(g);
        setFill(g);
    }

    @Override
    public void setLocation(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.WIDTH = w;
        this.HEIGHT = h;
        decoratedShape.setLocation(x, y, w, h);
    }


    private void setFill(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(3));
        g2d.fillOval(x1, y1, 100, 100);
        g2d.setColor(Color.WHITE);
        g2d.drawString("End", super.x1 + 35, super.y1 + 55);
    }
}
