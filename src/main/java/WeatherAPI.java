import io.github.cdimascio.dotenv.Dotenv;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherAPI extends JFrame {
    Dotenv dotenv = Dotenv.configure().load();

    private final String API_KEY = dotenv.get("WEATHER_KEY");
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=Tokyo,jp&units=imperial&appid=" + API_KEY;

    private final JLabel temperatureLabel;
    private final JLabel imageLabel;

    /**
     * Constructs a WeatherAPI object.
     * Sets up the window properties, layout, and components.
     * Retrieves and displays weather data upon initialization.
     */
    public WeatherAPI() {
        setTitle("Weather App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new FlowLayout());

        temperatureLabel = new JLabel();
        add(temperatureLabel);

        imageLabel = new JLabel();
        add(imageLabel);

        updateWeatherData();

        setVisible(true);
    }

    private void updateWeatherData() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String temperature = parseTemperature(response.toString());

                SwingUtilities.invokeLater(() -> temperatureLabel.setText("Temperature: " + temperature + "°F"));

                // Load and set the image based on the temperature
                ImageIcon imageIcon;
                if (Double.parseDouble(temperature) < 50) {
                    imageIcon = new ImageIcon("src/images/cold.png");
                } else if (Double.parseDouble(temperature) > 80) {
                    imageIcon = new ImageIcon("src/images/hot.png");
                } else {
                    imageIcon = new ImageIcon("src/images/good.png");
                }
                imageLabel.setIcon(imageIcon);
            } else {
                System.out.println("Error: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String parseTemperature(String response) {
        int start = response.indexOf("\"temp\":") + 7;
        int end = response.indexOf(",", start);

        if (start != -1 && end != -1) {
            String temperature = response.substring(start, end);
            double fahrenheit = Double.parseDouble(temperature);
            return String.format("%.2f", fahrenheit);
        }

        return "N/A";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeatherAPI::new);
    }
}
