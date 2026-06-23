package aoc;

import aoc.days.day08.Day08;
import aoc.days.day08.InputParser;
import aoc.days.day08.model.Box;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Box> boxes = InputParser.parse(new TXTFileReader().read("inputs/day08.txt"));
        Day08 day08 = new Day08(boxes);
        System.out.println("First part: " + day08.solvePart1());
        System.out.println("Second part: " + day08.solvePart2());
    }
}