package days.day11;

import aoc.core.DaySolver;
import aoc.days.day11.Day11;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day11Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day11.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day11.build(input());
        assertEquals(688L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day11.build(input());
        assertEquals(293263494406608L, solver.solvePart2());
    }
}
