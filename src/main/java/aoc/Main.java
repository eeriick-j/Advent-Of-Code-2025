package aoc;

import aoc.days.day10.Day10;
import aoc.days.day10.InputParser;
import aoc.days.day10.model.Machine;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Machine> machines = InputParser.parse(new TXTFileReader().read("inputs/day10.txt"));
        Day10 day10 = new Day10(machines);
        System.out.println("First part: " + day10.solvePart1());
        System.out.println("Second part: " + day10.solvePart2());
    }
}