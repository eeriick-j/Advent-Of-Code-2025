package aoc.days.day02;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<IDRange> parse(String rawInput) {
        return Arrays.stream(rawInput.split(","))
                .map(InputParser::stringToIDRange)
                .toList();
    }

    private static IDRange stringToIDRange(String range) {
        int idx = range.indexOf('-');
        return new IDRange(
                Long.parseLong(range.substring(0, idx)),
                Long.parseLong(range.substring(idx + 1))
        );
    }
}
