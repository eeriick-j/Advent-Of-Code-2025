package aoc;

import aoc.days.day03.Day03;
import aoc.days.day03.InputParser;
import aoc.days.day03.model.Bank;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Bank> banks = InputParser.parse(new TXTFileReader().read("inputs/day03.txt"));
        Day03 day03 = new Day03(banks);
        System.out.println("Part 1: " + day03.solvePart1());
        System.out.println("Part 2: " + day03.solvePart2());
    }
}