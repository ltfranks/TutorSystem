import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * YoutubePlayer class provides a JFrame with buttons to play YouTube videos.
 */
public class YoutubePlayer {
    /**
     * Creates a new instance of the YoutubePlayer class.
     * Sets up a JFrame with buttons to play YouTube videos.
     */
    public YoutubePlayer() {
        JFrame frame = new JFrame("YouTube Player");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        frame.add(controlPanel, BorderLayout.NORTH);

        final JFXPanel jfxPanel = new JFXPanel();
        frame.add(jfxPanel, BorderLayout.CENTER);

        String[] videoIds = {
                "https://youtu.be/SWRDqTx8d4k",
                "https://youtu.be/Rn16ugyorX0",
                "https://youtu.be/v5p_SUfi710"
        };

        String[] buttonNames = {
                "Flowchart Tutorial",
                "For Loops Tutorial",
                "Methods Tutorial"
        };

        for (int i = 0; i < videoIds.length; i++) {
            String videoId = videoIds[i];
            JButton playButton = new JButton(buttonNames[i]);
            controlPanel.add(playButton);

            playButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            WebView webView = new WebView();
                            jfxPanel.setScene(new Scene(webView));
                            webView.getEngine().load(videoId);
                        }
                    });
                }
            });
        }
        frame.setVisible(true);
    }
}
