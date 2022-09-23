package utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {

    public static String readString(String filePath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
