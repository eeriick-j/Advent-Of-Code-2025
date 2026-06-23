package aoc.days.day01;

import aoc.days.day01.model.Rotation;

import java.util.List;

public class InputParser {
    public static List<Rotation> parse(String rawInput) {
        return rawInput.lines()
                .map(line -> new Rotation(line.charAt(0), Integer.parseInt(line.substring(1))))
                .toList();
    }
}
