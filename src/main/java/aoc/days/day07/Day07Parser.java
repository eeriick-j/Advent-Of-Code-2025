package aoc.days.day07;

import aoc.core.InputParser;

import java.util.Arrays;

public class Day07Parser implements InputParser<char[][]> {
    @Override
    public char[][] parse(String rawInput) {
        return Arrays.stream(rawInput.split("\\R"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
