package aoc.tasks;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TXTFileReader implements FileReader{
    @Override
    public String read(String route) {
        InputStream is = TXTFileReader.class.getClassLoader().getResourceAsStream(route);
        if (is == null) throw new IllegalArgumentException("File not found: " + route);

        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
