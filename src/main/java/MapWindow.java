import javafx.embed.swing.JFXPanel;
import javax.swing.*;
import java.awt.*;

/**
 * The MapWindow class represents a window for displaying Google Maps.
 * It provides a user interface for entering a zip code and searching for a location on the map.
 */
public class MapWindow extends JFrame {

    /**
     * Constructs a MapWindow object.
     * Sets up the window title, size, layout, and components.
     */
    public MapWindow() {
        super("Google Maps");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel, BorderLayout.NORTH);

        final JFXPanel jfxPanel = new JFXPanel();
        add(jfxPanel, BorderLayout.CENTER);

        JLabel zipLabel = new JLabel("ZipCode: ");
        Font currentFont = zipLabel.getFont().deriveFont(Font.BOLD, 12);
        zipLabel.setFont(currentFont);
        controlPanel.add(zipLabel);

        JTextField zipCode = new JTextField();
        zipCode.setPreferredSize(new Dimension(150, 20));
        controlPanel.add(zipCode);

        JButton searchButton = new JButton("Search");
        controlPanel.add(searchButton);

        searchButton.addActionListener(new MapHandler(zipCode, jfxPanel));

        setVisible(true);
    }
}