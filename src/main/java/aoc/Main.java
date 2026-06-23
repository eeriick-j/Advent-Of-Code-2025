package aoc;

import aoc.days.day05.Day05;
import aoc.days.day05.InputParser;
import aoc.days.day05.model.Pair;
import aoc.io.TXTFileReader;

public class Main {
    public static void main(String[] args) {
        Pair pair = InputParser.parse(new TXTFileReader().read("inputs/day05.txt"));
        Day05 day05 = new Day05(pair);
        System.out.println("First part: " + day05.solvePart1());
        System.out.println("Second part: " + day05.solvePart2());
    }
}