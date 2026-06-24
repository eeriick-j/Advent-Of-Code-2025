package days.day07;

import aoc.core.DaySolver;
import aoc.days.day07.Day07;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day07Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day07.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day07.build(input());
        assertEquals(1690, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day07.build(input());
        assertEquals(221371496188107L, solver.solvePart2());
    }
}
