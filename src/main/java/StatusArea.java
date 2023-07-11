import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * The StatusArea updates the bottom status panel. The user is pudates with messages in this panel when a new shape,
 * line, save, load is created.
 *
 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public class StatusArea extends JPanel implements Observer {
	JTextArea status;

	/**
	 * Constructs a new StatusArea JPanel and sets up the JTextArea to display status information.
	 */
	public StatusArea() {
		status = new JTextArea("Status: ");
		status.setEditable(false);
		status.setBackground(Color.WHITE);
		Repository.getR().addObserver(this);
		status.setBorder(BorderFactory.createEmptyBorder());
		//status.setHorizontalAlignment(JTextArea.LEFT);
		status.setForeground(Color.BLACK);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		status.setText("Status: Shiv & Luke are 🐐's");
		add(status);

	}

	/**
	 * Updates the status based on changes in the model.
	 * @param o The Observable object that notified this observer.
	 * @param arg The argument passed by the Observable object.
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
		if (arg.equals("Shape")) {
			status.setText("Status: Shape Was Created");
		}
		else if (arg.equals("Line")) {
			status.setText("Status: Line Was Created");
		}
		else if (arg.equals("Save")) {
			status.setText("Status: File Was Saved");
		}
		else if (arg.equals("Load")) {
			status.setText("Status: File Was Loaded");
		}
	}
	
}
