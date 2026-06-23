package aoc;

import aoc.core.InputParser;
import aoc.days.day02.Day02Parser;
import aoc.days.day02.Day02Solver;
import aoc.days.day02.model.IDRange;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputParser<List<IDRange>> parser = new Day02Parser();
        FileReader reader = new TXTFileReader();

        List<IDRange> idRanges = parser.parse(reader.read("inputs/day02.txt"));
        Day02Solver day01 = new Day02Solver(idRanges);
        System.out.println("First part: " + day01.solvePart1());
        System.out.println("Second part: " + day01.solvePart2());
    }
}