import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

/**
 * The MenuController class is responsible for handling menu-related events and performing corresponding actions.
 * It implements the ActionListener interface to handle action events.
 */
public class MenuController extends JPanel implements ActionListener {

    private RepositoryInterface repo = Repository.getR();
    Dotenv dotenv = Dotenv.configure().load();
    String apiKey = dotenv.get("MAPS_KEY");

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
            case "Youtube Tutorial" -> {
                new YoutubePlayer();
            }
            case "Ask ChatGPT" -> {
                ChatArea chatArea = new ChatArea();
                chatArea.setPreferredSize(new Dimension(400, 400));
                JDialog dialog = new JDialog();
                dialog.setTitle("Professor GPT");
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                dialog.getContentPane().setLayout(new BorderLayout());
                dialog.getContentPane().add(chatArea, BorderLayout.CENTER);
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);

            }
            case "Maps" -> {
                if (!apiKey.isEmpty()) {
                    new MapWindow();
                } else {
                    JOptionPane.showMessageDialog(null, "Feature cannot be accessed or utilized: \nNo API Key Provided", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new UnsupportedOperationException("Without the API Key, the feature cannot be accessed or utilized.");
                }

            }

            case "Check Weather" -> {
                new WeatherAPI();
            }
            case "Save" -> {
                try {
                    repo.setAll();
                    JFileChooser fileChooser = new JFileChooser();
                    int userSelection = fileChooser.showSaveDialog(null);
                    if (userSelection == JFileChooser.APPROVE_OPTION){
                        File fileToSave = fileChooser.getSelectedFile();
                        String filePath = fileToSave.getAbsolutePath();
                        LoadOrSave.saveObjects(repo.getShapes(), repo.getLines(), repo.getConnection(), filePath);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "Load" -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load Diagram");
                int userSelect = fileChooser.showOpenDialog(this);
                if (userSelect == JFileChooser.APPROVE_OPTION) {
                    try {
                        Object[] objects = LoadOrSave.loadObjects(repo.getFilePath());
                        System.out.println(objects.length);
                        Stack<Shape> loadedShapes = (Stack<Shape>) objects[0];
                        Stack<Line> loadedLines = (Stack<Line>) objects[1];
                        Stack<Shape> loadedConnections = (Stack<Shape>) objects[2];
                        repo.setLoadShapes(loadedShapes);
                        repo.setLoadLines(loadedLines);
                        repo.setLoadConnections(loadedConnections);
                    } catch (IOException | ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            case "Results" -> {
                System.out.println("Results");
                ResultsWindow resultsWindow = new ResultsWindow();
                JFrame progressFrame = new JFrame("Progress");
                progressFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                progressFrame.getContentPane().add(resultsWindow);
                progressFrame.pack();
                progressFrame.setVisible(true);
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
}
