package aoc.days.day06;

import aoc.core.InputParser;

import java.util.List;

public class Day06Parser implements InputParser<List<String>> {
    @Override
    public List<String> parse(String rawInput) {
        return rawInput.lines().toList();
    }
}
