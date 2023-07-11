
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.Stack;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    RepositoryInterface repo = Repository.getR();
    @Test
    public void shapePushedInStack(){
        repo.addShape(new Rectangle(50, 100, 50, 100, Color.BLACK));
        repo.addShape(new Circle(50, 100, 50, 100, Color.BLACK));
        repo.addShape(new Diamond(50, 100, 50, 100, Color.BLACK));

        assertEquals("Rectangle", repo.getShapes().get(0).getClass().getName());
        assertEquals("Circle", repo.getShapes().get(1).getClass().getName());
        assertEquals("Diamond", repo.getShapes().get(2).getClass().getName());
    }


    @Test
    public void shapeClearedPushedInStack(){
        repo.getShapes().clear();
        Stack<Shape> s = new Stack<>();
        assertEquals(s, repo.getShapes());
    }

    @Test
    public void decoratedShapePushedInStack(){
        Shape circle = new Circle(50, 100, 50, 100, Color.BLACK);
        repo.addShape(new FillDecorator(circle,50, 100, 50, 100, Color.BLACK));
        Shape rectangle = new Rectangle(50, 100, 50, 100, Color.BLACK);
        repo.addShape(new CallDecorator(rectangle,50, 100, 50, 100, Color.BLACK));
        repo.addShape(new VariableDecorate(rectangle,50, 100, 50, 100, Color.BLACK));

        assertEquals("FillDecorator", repo.getShapes().get(0).getClass().getName());
        assertEquals("CallDecorator", repo.getShapes().get(1).getClass().getName());
        assertEquals("VariableDecorate", repo.getShapes().get(2).getClass().getName());
    }

    @Test
    public void decoratedShapeClearedPushedInStack(){
        repo.getShapes().clear();
        Stack<Shape> s = new Stack<>();
        assertEquals(s, repo.getShapes());
    }

}