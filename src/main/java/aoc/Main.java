package aoc;

import aoc.days.day07.Day07;
import aoc.days.day07.InputParser;
import aoc.io.TXTFileReader;


public class Main {
    public static void main(String[] args) {
        char[][] grid = InputParser.parse(new TXTFileReader().read("inputs/day07.txt"));
        Day07 day07 = new Day07(grid);
        System.out.println("First part: " + day07.solvePart1());
        System.out.println("Second part: " + day07.solvePart2());
    }
}