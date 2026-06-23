package aoc;

import aoc.days.day11.Day11;
import aoc.days.day11.Graph;
import aoc.days.day11.InputParser;
import aoc.io.TXTFileReader;

public class Main {
    public static void main(String[] args) {
        Graph graph = InputParser.parse(new TXTFileReader().read("inputs/day11.txt"));
        Day11 day11 = new Day11(graph);
        System.out.println("First part: " + day11.solvePart1());
        System.out.println("Second part: " + day11.solvePart2());
    }
}