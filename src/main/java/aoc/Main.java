package aoc;

import aoc.days.day02.Day02;
import aoc.days.day02.InputParser;
import aoc.days.day02.model.IDRange;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<IDRange> idRanges = InputParser.parse(new TXTFileReader().read("inputs/day02.txt"));
        Day02 day02 = new Day02(idRanges);
        System.out.println("Part 2: " + day02.solvePart1());
        System.out.println("Part 2: " + day02.solvePart2());
    }
}