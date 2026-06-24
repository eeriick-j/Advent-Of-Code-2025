package days.day10;

import aoc.core.DaySolver;
import aoc.days.day10.Day10;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day10Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day10.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day10.build(input());
        assertEquals(527L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day10.build(input());
        assertEquals(19810L, solver.solvePart2());
    }
}
