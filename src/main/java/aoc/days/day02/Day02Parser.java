package aoc.days.day02;

import aoc.core.InputParser;
import aoc.days.day02.model.IDRange;

import java.util.Arrays;
import java.util.List;

public class Day02Parser implements InputParser<List<IDRange>> {
    @Override
    public List<IDRange> parse(String rawInput) {
        return Arrays.stream(rawInput.split(","))
                .map(Day02Parser::stringToIDRange)
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
