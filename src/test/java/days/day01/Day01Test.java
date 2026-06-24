package days.day01;

import aoc.core.DaySolver;
import aoc.days.day01.Day01;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day01Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day01.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day01.build(input());
        assertEquals(1123, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day01.build(input());
        assertEquals(6695, solver.solvePart2());
    }
}