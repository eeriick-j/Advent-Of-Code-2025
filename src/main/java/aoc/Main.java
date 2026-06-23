package aoc;

import aoc.days.day04.Day04;
import aoc.io.TXTFileReader;

public class Main {
    public static void main(String[] args) {
        char[][] rollsOfPapers = aoc.days.day04.InputParser.parse(new TXTFileReader().read("inputs/day04.txt"));
        Day04 day04 = new Day04(rollsOfPapers);
        System.out.println("First part: " + day04.solvePart1());
        System.out.println("Second part: " + day04.solvePart2());
    }
}