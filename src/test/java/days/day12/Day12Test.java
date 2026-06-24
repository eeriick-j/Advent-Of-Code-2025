package days.day12;

import aoc.core.DaySolver;
import aoc.days.day12.Day12;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day12Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day12.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day12.build(input());
        assertEquals(408, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day12.build(input());
        assertEquals(null, solver.solvePart2());
    }
}
