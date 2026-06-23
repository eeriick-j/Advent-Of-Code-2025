package aoc.days.day04;

import aoc.core.InputParser;

import java.util.Arrays;

public class Day04Parser implements InputParser<char[][]> {
    @Override
    public char[][] parse(String rawInput) {
        return Arrays.stream(rawInput.split("\\R"))
                .map(String::toCharArray)
                .toArray(char[][]::new);
    }
}
