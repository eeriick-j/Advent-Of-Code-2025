package aoc;

import aoc.days.day09.Day09;
import aoc.days.day09.InputParser;
import aoc.days.day09.model.Point;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point> points = InputParser.parse(new TXTFileReader().read("inputs/day09.txt"));
        Day09 day09 = new Day09(points);
        System.out.println("First part: " + day09.solvePart1());
        System.out.println("Second part: " + day09.solvePart2());
    }
}