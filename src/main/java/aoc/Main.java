package aoc;

import aoc.days.day06.Day06;
import aoc.days.day06.InputParser;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> grid = InputParser.parse(new TXTFileReader().read("inputs/day06.txt"));
        Day06 day06 = new Day06(grid);
        System.out.println("First part: " + day06.solvePart1());
        System.out.println("Second part: " + day06.solvePart2());
    }
}