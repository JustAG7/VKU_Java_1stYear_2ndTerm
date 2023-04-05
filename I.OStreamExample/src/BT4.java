import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class BT4 {
    public static void main(String[] args) {
        Path source = Paths.get("/home/justa/HTML-CSS-VKU#1");
        Path destination = Paths.get("/home/justa/Documents");

        try {
            Files.walk(source)
                    .forEach(sourcePath -> {
                        Path destPath = destination.resolve(source.relativize(sourcePath));
                        try {
                            Files.copy(sourcePath, destPath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Folder copied successfully.");
        } catch (IOException e) {
            System.err.println("Failed to copy folder: " + e.getMessage());
        }
    }
}
