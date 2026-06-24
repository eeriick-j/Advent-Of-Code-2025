package days.day08;

import aoc.core.DaySolver;
import aoc.days.day08.Day08;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day08Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day08.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day08.build(input());
        assertEquals(67488L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day08.build(input());
        assertEquals(3767453340L, solver.solvePart2());
    }
}
