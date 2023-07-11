import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 * The WorkingArea class extends JPanel and allows the lines and shapes drawn. The Controller
 * is added so that the actions with the mouse can be handled.
 *
 *  @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public class WorkingArea extends JPanel{

	RepositoryInterface repo = Repository.getR();

	/**
	 * Constructs a new WorkingArea object, sets its background color, and adds
	 * a Controller as a MouseListener and MouseMotionListener.
	 */
	public WorkingArea() {
		Controller controller = new Controller();
		setBackground(Color.decode("#ADD8E6"));
		addMouseListener(controller);
		addMouseMotionListener(controller);
	}

	/**
	 * Overrides the paintComponent method of JPanel to draw the shapes and lines
	 * contained in the Repository.
	 * @param g the Graphics object used to draw
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (Shape drawing : repo.getShapes()) {
			drawing.draw(g);
		}

		for(Line line : repo.getLines()){
			line.draw(g);
		}
		repaint();
	}
}