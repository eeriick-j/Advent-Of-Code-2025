package aoc.day07;

import java.util.Arrays;

public class InputParser {
    public static char[][] parse(String rawInput) {
        return Arrays.stream(rawInput.split("\\R"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
