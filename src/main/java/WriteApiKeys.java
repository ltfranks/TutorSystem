import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteApiKeys {

    private Repository repo = Repository.getR();
    /**
     * Creates an environment file containing key-value pairs for various APIs.
     * If the file already exists, it will be overwritten.
     * The file is created in the current directory with the name ".env".
     * The key-value pairs are retrieved from the Repository object.
     *
     * @throws IOException If an I/O error occurs while creating or writing to the file.
     */
    public void createEnvFile() throws IOException {
        String filepath = ".env";
        File envFile = new File(filepath);

        if (!envFile.exists()) {
            envFile.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(envFile))) {
            writer.write("GPT_KEY=" + repo.getGptkey());
            writer.newLine();
            writer.write("MAPS_KEY=" + repo.getMapsKey());
            writer.newLine();
            writer.write("WEATHER_KEY=" + repo.getWeatherKey());
            writer.newLine();
            writer.write("YOUTUBE_KEY=" + repo.getYoutubeKey());
        }
    }
}
