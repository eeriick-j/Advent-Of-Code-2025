package aoc;

import aoc.days.day01.Day01;
import aoc.days.day01.InputParser;
import aoc.days.day01.model.Rotation;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Rotation> rotations = InputParser.parse(new TXTFileReader().read("inputs/day01.txt"));
        Day01 day01 = new Day01(rotations);
        System.out.println("Part 1: " + day01.solvePart1());
        System.out.println("Part 2: " + day01.solvePart2());
    }
}