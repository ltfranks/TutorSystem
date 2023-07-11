import java.awt.*;
import java.util.ArrayList;

/**
 * The CallDecorator class draws the lines onto a rectangle for the call object using a decorator.
 *
 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public class CallDecorator extends ShapeDecorator {

    public CallDecorator(Shape decoratedShape, int x1, int y1, int WIDTH, int HEIGHT, Color c) {
        super(decoratedShape, x1, y1, WIDTH, HEIGHT, c);
    }

    @Override
    public void draw(Graphics g) {
        decoratedShape.draw(g);
        drawCallLines(g);
    }

    @Override
    public void setLocation(int x, int y, int w, int h) {
        this.x1 = x;
        this.y1 = y;
        this.WIDTH = w;
        this.HEIGHT = h;
        decoratedShape.setLocation(x, y, w, h);
    }


    private void drawCallLines(Graphics g) {
        int x1 = getX();
        int y1 = getY();
        int height = 100;
        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2));
        g.drawLine(x1 + 10, y1, x1 + 10, y1 + height);
        g.drawLine(x1 + 90, y1, x1 + 90, y1 + height);
        g2d.setStroke(oldStroke);

        FontMetrics fm = g2d.getFontMetrics();
        int x = super.x1 + 20;
        int y = super.y1 + fm.getAscent() + 35;
        int maxWidth = 68;

        String[] lines = text.split("\n");
        for (String line : lines) {
            int lineWidth = 0;
            StringBuilder currentLine = new StringBuilder();
            for (char c : line.toCharArray()) {
                int charWidth = fm.charWidth(c);
                if (lineWidth + charWidth > maxWidth) {

                    g2d.drawString(currentLine.toString().trim(), x, y);
                    y += fm.getHeight();
                    lineWidth = 0;
                    currentLine = new StringBuilder();
                }
                lineWidth += charWidth;
                currentLine.append(c);
            }

            g2d.drawString(currentLine.toString().trim(), x, y);
            y += fm.getHeight();
        }
    }
}