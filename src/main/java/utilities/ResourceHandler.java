package utilities;

import java.io.*;
import java.nio.file.Path;

@SuppressWarnings("CallToPrintStackTrace")
public final class ResourceHandler {
    private ResourceHandler() {}

    public static void saveData(String filename, Object object) {
        try (FileOutputStream fileStream = new FileOutputStream(getResourcePath(filename));
             ObjectOutputStream objectStream = new ObjectOutputStream(fileStream)) {
            objectStream.writeObject(object);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Object loadData(String filename) {
        try (FileInputStream fileStream = new FileInputStream(getResourcePath(filename));
             ObjectInputStream objectStream = new ObjectInputStream(fileStream)) {
            return objectStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private static String getResourcePath(String fileName) {
        return Path.of("src", "main", "resources", fileName)
                .toAbsolutePath().toString();
    }
}
