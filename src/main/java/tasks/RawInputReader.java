package tasks;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RawInputReader {
    public static String read(String path) {
        InputStream is = RawInputReader.class.getClassLoader().getResourceAsStream(path);
        if (is == null) throw new IllegalArgumentException("File not found: " + path);

        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
