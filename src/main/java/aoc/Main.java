package aoc;


import aoc.core.InputParser;
import aoc.days.day01.Day01Solver;
import aoc.days.day01.Day01Parser;
import aoc.days.day01.model.Rotation;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputParser<List<Rotation>> parser = new Day01Parser();
        FileReader reader = new TXTFileReader();

        List<Rotation> rotations = parser.parse(reader.read("inputs/day01.txt"));
        Day01Solver day01 = new Day01Solver(rotations);
        System.out.println("First part: " + day01.solvePart1());
        System.out.println("Second part: " + day01.solvePart2());
    }
}