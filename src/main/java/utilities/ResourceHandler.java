package utilities;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public final class ResourceHandler {
    static {
        Path path = Path.of("src", "main", "resources").toAbsolutePath();
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ignore) {
            }
        }
    }

    private ResourceHandler() {
    }

    public static void saveData(String resourceName, Object object) {
        try (FileOutputStream fileStream = new FileOutputStream(getResourcePath(resourceName));
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(object);
        } catch (IOException ignore) {
        }
    }

    public static Optional<Object> loadData(String resourceName) {
        try (FileInputStream fileStream = new FileInputStream(getResourcePath(resourceName));
             ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            return Optional.of(objectStream.readObject());
        } catch (IOException | ClassNotFoundException ignore) {
            return Optional.empty();
        }
    }

    private static String getResourcePath(String resourceName) {
        return Path.of("src", "main", "resources", resourceName).toAbsolutePath().toString();
    }
}
