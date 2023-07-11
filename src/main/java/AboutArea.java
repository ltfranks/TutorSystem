import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * The AboutArea class displays information about the team.
 */
public class AboutArea {
	/**
	 * Displays team information in a dialog box.
	 */
	void aboutInfo() {
		JPanel panel = new JPanel(new BorderLayout());
		ImageIcon imageIcon = new ImageIcon("src/images/poly2.png");
		JLabel label = new JLabel(imageIcon);
		panel.add(label, BorderLayout.CENTER);
		JLabel description = new JLabel("Anthony Colin, Shiv Panchal, Luke Fanguna, Nathan Choi, Reza Mousakhani, Luke Franks");
		description.setFont(description.getFont().deriveFont(Font.BOLD));
		panel.add(description, BorderLayout.SOUTH);
		panel.setPreferredSize(new Dimension(600, 300));
		panel.setMaximumSize(new Dimension(600, 300));
		JOptionPane.showMessageDialog(null, panel, "Team Information", JOptionPane.PLAIN_MESSAGE);
	}

}
