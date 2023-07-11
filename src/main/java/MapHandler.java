import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The MapHandler class implements the ActionListener interface to handle map-related events.
 * It retrieves a zip code input, geocodes it to obtain coordinates, and displays a map in a JavaFX WebView.
 */
public class MapHandler implements ActionListener {
    private final JTextField zipCode;
    private final JFXPanel jfxPanel;

    /**
     Constructs a MapHandler object with the specified zip code text field and JavaFX panel.
     @param zipCode the JTextField object for entering the zip code
     @param jfxPanel the JFXPanel object for displaying the map
     */
    public MapHandler(JTextField zipCode, JFXPanel jfxPanel) {
        this.zipCode = zipCode;
        this.jfxPanel = jfxPanel;
    }

    /**
     * Handles the actionPerformed event triggered by the map-related action.
     * Retrieves the entered zip code, geocodes it to obtain coordinates,
     * and displays a map in a JavaFX WebView.
     * @param e the ActionEvent object representing the map-related event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String inputZipCode = zipCode.getText();
        if (inputZipCode.length() < 5){
            JOptionPane.showMessageDialog(null, "An error occurred: \nInvalid ZipCode", "Error", JOptionPane.ERROR_MESSAGE);

        }else{
            MapGeoCode geoCode = new MapGeoCode();
            String coordinates = geoCode.geocode(inputZipCode);

            String mapURL = "https://www.google.com/maps/search/public+wifi+places+near+me/@" + coordinates + ",12.75z?entry=ttu";

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    WebView webView = new WebView();
                    jfxPanel.setScene(new Scene(webView));
                    webView.getEngine().load(mapURL);
                }
            });
        }
    }
}

