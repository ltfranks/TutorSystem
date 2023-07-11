import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Stack;

/**
 * This is the controller. This class represents all the controls of the entire program overall.
 * This class Inherits the properties and methods of ActionListener, MouseListener and MouseMotionListener.
 *
 * @author Anthony Colin, Shiv Panchal, Luke Fanguna, Nathan Choi, Reza Mousakhani, Luke Franks
 */
public class Controller extends JPanel implements ActionListener, MouseListener, MouseMotionListener {
    private Color color = Color.WHITE;
    private int x1, y1;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private Point prevPoint;
    private int count = 0;
    private String shape;
    private RepositoryInterface repo = Repository.getR();


    /**
     * Method below is inherited from ActionListener. It gets inputs from checkboxes which user operates With.
     * It sets to different operations using getActionCommand().
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String[] shapeArr = {"Begin", "End", "Call a method", "Instruction", "Input or Output", "Variable Declaration", "Condition"};
        for (String shape : shapeArr) {
            if (action.equals(shape)) {
                repo.setShape(shape);
            }
        }

        switch (action) {
            case "Undo" -> undo();
            case "Clear" -> clear();
            case "About" -> {
                AboutArea ab = new AboutArea();
                ab.aboutInfo();
            }
            case "Save" -> {
                try {
                    repo.setAll();
                    LoadOrSave.saveObjects(repo.getShapes(), repo.getLines(), repo.getConnection(), repo.getFilePath());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "Load" -> {
                loadShapes();
            }
            case "Hint" -> {

            }
        }
    }

    private void loadShapes() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Diagram");
        int userSelect = fileChooser.showOpenDialog(this);
        if (userSelect == JFileChooser.APPROVE_OPTION) {
            try {
                Object[] objects = LoadOrSave.loadObjects(repo.getFilePath());
                Stack<Shape> loadedShapes = (Stack<Shape>) objects[0];
                Stack<Line> loadedLines = (Stack<Line>) objects[1];
                repo.setLoadShapes(loadedShapes);
                repo.setLoadLines(loadedLines);
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * This method undoes the line and shapes that are drawn on the panel.
     */
    void undo() {
        if (!repo.getLines().isEmpty() || !repo.getShapes().isEmpty()) {
            if (!repo.getLines().isEmpty()) {
                repo.getLines().pop();
            }
            repo.getShapes().pop();
        }
        repaint();
    }

    /**
     * This method clears the lines and shapes that are drawn panel.
     */
    void clear() {
        if (!repo.getLines().isEmpty() || !repo.getShapes().isEmpty()) {
            if (!repo.getLines().isEmpty()) {
                repo.getLines().clear();
            }
            repo.getShapes().clear();
        }
        repaint();
    }



    /**
     * This method is called whenever mouse is clicked. When a mouse is clicked, it draws the shape according to
     * Selected shape from menu. When a mouse is clicked twice on two different shapes, it draws line between those
     * Shapes.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        boolean exist = false;
        x1 = e.getX() - WIDTH / 2;
        y1 = e.getY() - HEIGHT / 2;
        Point p = new Point(e.getX(), e.getY());
        shape = repo.getShape();

        if (e.getClickCount() == 1) {
            for (Shape sh : repo.getShapes()) {
                if (sh.contains(p)) {
                    exist = true;
                    break;
                }
            }

            if (!exist && !shape.isEmpty()) {
                createShape();
                repaint();
            }

            if (!shape.equals("Begin") && !shape.equals("End")) {
                for (Shape sp : repo.getShapes()) {
                    if (sp.contains(p) && !sp.isTextDefined() && !exist) {
                        JFrame frame = new JFrame();
                        String result = JOptionPane.showInputDialog(frame, "Enter expression:");
                        sp.setText(result);
                        repaint();
                        sp.setTextDefined(true);
                    }
                    sp.setTextDefined(false);
                }
            }
        }

        for (Shape sp : repo.getShapes()) {
            if (e.getClickCount() == 2 && sp.contains(p)) {
                repo.addC(sp);
                if (repo.getC().size() > 2) {
                    Shape shape = repo.getC().pop();
                    repo.clearC();
                    repo.getC().push(shape);
                }
                createLines();
            }
        }
        repaint();
    }

    private void createLines() {
        //removing if more than two shapes
        //Something
        String first = repo.getC().get(0).toString();
        first = first.substring(0,first.indexOf("@"));
        if (first.equals("Diamond") && repo.getC().size() == 2) {
            Line line = new ConditionLine(new RegularLine(repo.getC().get(0), repo.getC().get(1)),repo.getC().get(0), repo.getC().get(1),count);
            repo.addLine(line);
            repo.setLine(line);
            count++;
        }
        else if (!first.toString().equals("Diamond") && repo.getC().size() == 2) {
            Line line = new RegularLine(repo.getC().get(0), repo.getC().get(1));
            repo.addLine(line);
            repo.setLine(line);
        }

    }
    private void createShape()
    {
        int qNum = new AnswerController().getCurrentQuestionIndex();
        if (shape.equals("Begin")) {
            repo.incrementFlowchartAns("q" + String.valueOf(qNum), shape);
            createBegin();
        } else if (shape.equals("End")) {
            repo.incrementFlowchartAns("q" + String.valueOf(qNum), shape);
            createEnd();
        } else if (shape.equals("Call a method")) {
            repo.incrementFlowchartAns("q" + String.valueOf(qNum), shape);
            createCall();
        } else if (shape.equals("Instruction")) {
            repo.incrementFlowchartAns("q" + String.valueOf(qNum), shape);
            createInstruction();
        } else if (shape.equals("Input or Output")) {
            repo.incrementFlowchartAns("q" + String.valueOf(qNum), shape);
            createIO();
        } else if (shape.equals("Variable Declaration")) {
            repo.incrementFlowchartAns("q" + String.valueOf(qNum), shape);
            createVD();
        } else if (shape.equals("Condition")) {
            repo.incrementFlowchartAns("q" + String.valueOf(qNum), shape);
            count = 0;
            createCond();
        }
    }

    private void createCond()
    {
        repo.addShape(new Diamond(x1-50, y1-50, x1 + 100, y1 + 100, color));
    }

    private void createVD()
    {
        Shape variableRect = new VariableDecorate(new Rectangle(x1 - 50, y1 - 50, x1 + 100, y1 + 100, color)
                , x1 - 50, y1 - 50, x1 + 100, y1 + 100, color);
        repo.addShape(variableRect);
    }

    private void createIO()
    {
        repo.addShape(new Parallelogram(x1 - 50, y1 - 50, x1 + 100, y1 + 100, color));
    }

    private void createInstruction()
    {
        repo.addShape(new Rectangle(x1 - 50, y1 - 50, x1 + 100, y1 + 100, color));
    }

    private void createCall()
    {
        Shape rectangle = new Rectangle(x1 - 50, y1 - 50, x1 + 100, y1 + 100, color);
        Shape callMethod = new CallDecorator(rectangle, x1 - 50, y1 - 50, x1 + 100, y1 + 100, color);
        repo.addShape(callMethod);
    }

    private void createEnd()
    {
        Shape filledCircle = new FillDecorator(new Circle(x1 - 50, y1 - 50, x1 + 100, y1 + 100, color)
        , x1 - 50, y1 - 50, x1 + 100, y1 + 100, color);
        repo.addShape(filledCircle);
    }

    private void createBegin()
    {
        repo.addShape(new Circle(x1 - 50, y1 - 50, x1 + 100, y1 + 100, color));
    }

    /**
     * This method is called whenever mouse is pressed. This is checking if the mouse pressed inside the shape or not.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        for (Shape shape : repo.getShapes()) {
            if (shape.contains(p)) {
                shape.setFlag(true);
                prevPoint = e.getPoint();
            }
        }
    }

    /**
     * This method is called whenever the mouse is released.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (Shape shape : repo.getShapes()) {
            shape.setFlag(false);
        }
    }

    /**
     * This method is called whenever the mouse enters.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * This method is called whenever the mouse exits.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * This method is called whenever the mouse is dragged. This method allows the detects if the click is inside the
     * Shape or not, and allows to move/drag the shape around with mouse.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        for (Shape shape : repo.getShapes()) {
            if (shape.getFlag()) {
                int deltaX = e.getX() - prevPoint.x;
                int deltaY = e.getY() - prevPoint.y;
                shape.setLocation(shape.getX() + deltaX, shape.getY() + deltaY, shape.WIDTH, shape.HEIGHT);
                if (shape instanceof FillDecorator) {
                    ((FillDecorator) shape).setLocation(shape.getX(), shape.getY(), shape.WIDTH, shape.HEIGHT);
                } else if (shape instanceof ShapeDecorator) {
                    ((ShapeDecorator) shape).setLocation(shape.getX(), shape.getY(), shape.WIDTH, shape.HEIGHT);
                }
            }
        }
        prevPoint = e.getPoint();
        repaint();
    }


    /**
     * This method is called whenever the mouse is moved.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
