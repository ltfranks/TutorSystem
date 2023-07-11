import java.awt.*;
import javax.swing.*;
/**
 * The Menu class extends JMenuBar and provides a graphical user interface for a menu bar.
 * It consists of various menu items. Moreover, the class provides functionalities to save
 * and load diagrams, set action listeners, and icons for menu items.
 *
 * @author Reza Mousakhani, Anthony Colin, Luke Fanguna, Luke Franks, Nathan Choi, & Shiv Panchal
 */
public class Menu extends JMenuBar {
	/**
	 * Creates a new Menu object with File, Help, New and Edit menus, and their respective menu items.
	 * Additionally, it sets the appropriate action listeners for each menu item and creates
	 * the necessary icons for display.
	 */
	public Menu() {
		MenuController mc = new MenuController();

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenu helpMenu = new JMenu("Help");
		JMenu shapeMenu = new JMenu("Shape");
		JMenu editMenu = new JMenu("Edit");
		JMenu resultsMenu = new JMenu("Results");

		JCheckBoxMenuItem save = new JCheckBoxMenuItem("Save");
		JCheckBoxMenuItem load = new JCheckBoxMenuItem("Load");
		JCheckBoxMenuItem about = new JCheckBoxMenuItem("About");
		Image aboutIcon = new ImageIcon("src/images/info.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
		ImageIcon newAboutIcon = new ImageIcon(aboutIcon);
		about.setIcon(newAboutIcon);
		JCheckBoxMenuItem undo = new JCheckBoxMenuItem("Undo");
		JCheckBoxMenuItem clear = new JCheckBoxMenuItem("Clear");
		JCheckBoxMenuItem results = new JCheckBoxMenuItem("Results");

		JCheckBoxMenuItem chatGPT = new JCheckBoxMenuItem("Ask ChatGPT");
		ImageIcon icon = new ImageIcon("src/images/chatGPT.png");
		chatGPT.setIcon(icon);

		JCheckBoxMenuItem weatherAPI = new JCheckBoxMenuItem("Check Weather");
		ImageIcon weatherIcon = getScaledImageIcon("src/images/weatherIcon.png", icon);
		weatherAPI.setIcon(weatherIcon);

		JCheckBoxMenuItem maps = new JCheckBoxMenuItem("Maps");
		Image mapIcon = new ImageIcon("src/images/maps.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH);
		ImageIcon newMapIcon = new ImageIcon(mapIcon);
		maps.setIcon(newMapIcon);
		JCheckBoxMenuItem youtubePlayer = new JCheckBoxMenuItem("Youtube Tutorial");
		ImageIcon youtubeIcon = new ImageIcon("src/images/youtube.png");
		youtubePlayer.setIcon(youtubeIcon);

		String[] itemNames = {"Begin", "End", "Call a method", "Instruction", "Input or Output", "Variable Declaration", "Condition"};
		String[] itemIcons = {"src/images/begin.png", "src/images/end.png", "src/images/call.png", "src/images/instruction.png", "src/images/inputOutput.png", "src/images/variable.png", "src/images/condition.png"};

		ButtonGroup group = new ButtonGroup();

		for (int i = 0; i < itemNames.length; i++) {
			JCheckBoxMenuItem item = new JCheckBoxMenuItem(itemNames[i], new ImageIcon(itemIcons[i]));
			item.setHorizontalTextPosition(JCheckBoxMenuItem.RIGHT);
			item.setHorizontalAlignment(JCheckBoxMenuItem.LEFT);
			item.setIcon(new ImageIcon(new ImageIcon(itemIcons[i]).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
			shapeMenu.add(item);
			item.addActionListener(mc);
			group.add(item);
		}

		helpMenu.add(about);
		helpMenu.add(chatGPT);
		helpMenu.add(weatherAPI);
		helpMenu.add(maps);
		helpMenu.add(youtubePlayer);

		fileMenu.add(save);
		fileMenu.add(load);

		editMenu.add(undo);
		editMenu.add(clear);

		resultsMenu.add(results);

		menuBar.add(fileMenu);
		menuBar.add(shapeMenu);
		menuBar.add(editMenu);
		menuBar.add(resultsMenu);
		menuBar.add(helpMenu);

		about.addActionListener(mc);
		undo.addActionListener(mc);
		clear.addActionListener(mc);
		chatGPT.addActionListener(mc);
		weatherAPI.addActionListener(mc);
    	maps.addActionListener(mc);
		youtubePlayer.addActionListener(mc);
		save.addActionListener(mc);
		load.addActionListener(mc);
		results.addActionListener(mc);

		group.add(undo);
		group.add(clear);
		group.add(about);
		group.add(chatGPT);
		group.add(weatherAPI);
    group.add(maps);
		group.add(youtubePlayer);
		group.add(save);
		group.add(load);
		group.add(results);

		setBorder(BorderFactory.createEtchedBorder());
		add(menuBar);

		setBorder(BorderFactory.createEtchedBorder());
		add(menuBar);



	}

	public ImageIcon getScaledImageIcon(String filePath, ImageIcon referenceIcon) {
		ImageIcon originalIcon = new ImageIcon(filePath);
		Image scaledImage = originalIcon.getImage().getScaledInstance(referenceIcon.getIconWidth(), referenceIcon.getIconHeight(), Image.SCALE_SMOOTH);
		return new ImageIcon(scaledImage);
	}
}
