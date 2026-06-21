package aoc.day06;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static Pair parse(String rawInput) {
        List<String> lines = rawInput.lines().toList();

        List<List<Integer>> values = lines.stream()
                .filter(line -> line.matches(".*\\d.*"))
                .map(line ->
                        Arrays.stream(line.trim().split("\\s+"))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        List<Character> operators = Arrays.stream(lines.getLast().trim().split("\\s+"))
                .map(s -> s.charAt(0))
                .toList();

        return new Pair(values, operators);
    }
}
