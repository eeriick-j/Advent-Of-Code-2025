package aoc;

import aoc.core.InputParser;
import aoc.days.day03.Day03Parser;
import aoc.days.day03.Day03Solver;
import aoc.days.day03.model.Bank;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        InputParser<List<Bank>> parser = new Day03Parser();
        FileReader reader = new TXTFileReader();

        List<Bank> banks = parser.parse(reader.read("inputs/day03.txt"));
        Day03Solver day03 = new Day03Solver(banks);
        System.out.println("First part: " + day03.solvePart1());
        System.out.println("Second part: " + day03.solvePart2());
    }
}