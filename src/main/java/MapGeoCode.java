import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The MapGeoCode class provides methods for geocoding a given zip code using the Bing Maps API.
 * It retrieves the latitude and longitude coordinates associated with the zip code.
 **/
public class MapGeoCode {
    /**
     Geocodes the specified zip code and returns the latitude and longitude coordinates as a string.
     @param zipCode the zip code to geocode
     @return a string representing the latitude and longitude coordinates in the format "latitude,longitude",
     **/
    public String geocode(String zipCode) {
        Dotenv dotenv = Dotenv.configure().load();
        try {
            String apiKey = dotenv.get("MAPS_KEY");

            String geocodeUrl = String.format("https://dev.virtualearth.net/REST/v1/Locations?postalCode=%s&key=%s", zipCode, apiKey);
            URL geocodeUrlObject = new URL(geocodeUrl);
            HttpURLConnection geocodeConnection = (HttpURLConnection) geocodeUrlObject.openConnection();
            geocodeConnection.setRequestMethod("GET");

            BufferedReader geocodeReader = new BufferedReader(new InputStreamReader(geocodeConnection.getInputStream()));
            StringBuilder geocodeResponse = new StringBuilder();
            String geocodeLine;
            while ((geocodeLine = geocodeReader.readLine()) != null) {
                geocodeResponse.append(geocodeLine);
            }
            geocodeReader.close();

            JsonObject geocodeJsonObject = JsonParser.parseString(geocodeResponse.toString()).getAsJsonObject();
            JsonArray resourceSets = geocodeJsonObject.getAsJsonArray("resourceSets");
            JsonObject firstResourceSet = resourceSets.get(0).getAsJsonObject();
            JsonArray resources = firstResourceSet.getAsJsonArray("resources");
            JsonObject firstResource = resources.get(0).getAsJsonObject();
            JsonObject point = firstResource.getAsJsonObject("point");
            JsonArray coordinates = point.getAsJsonArray("coordinates");
            String latitude = coordinates.get(0).getAsString();
            String longitude = coordinates.get(1).getAsString();

            return latitude + "," + longitude;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
