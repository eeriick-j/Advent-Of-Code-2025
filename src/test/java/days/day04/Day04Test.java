package days.day04;

import aoc.core.DaySolver;
import aoc.days.day04.Day04;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day04Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day04.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day04.build(input());
        assertEquals(1569, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day04.build(input());
        assertEquals(9280, solver.solvePart2());
    }
}
