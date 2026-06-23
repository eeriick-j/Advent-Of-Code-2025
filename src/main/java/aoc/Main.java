package aoc;

import aoc.days.day12.Day12;
import aoc.days.day12.InputParser;
import aoc.days.day12.model.Puzzle;
import aoc.io.TXTFileReader;

public class Main {
    public static void main(String[] args) {
        Puzzle puzzle = InputParser.parse(new TXTFileReader().read("inputs/day12.txt"));
        Day12 day12 = new Day12(puzzle);
        System.out.println("First part: " + day12.solvePart1());
        System.out.println("Second part: " + day12.solvePart2());
    }
}