package days.day06;

import aoc.core.DaySolver;
import aoc.days.day06.Day06;
import aoc.days.day06.Day06Parser;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day06Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day06.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day06.build(input());
        assertEquals(4878670269096L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day06.build(input());
        assertEquals(8674740488592L, solver.solvePart2());
    }

    @Test
    public void parseSplitsLines() {
        Day06Parser parser = new Day06Parser();
        List<String> result = parser.parse("""
                abc
                def
                ghi
                """);

        assertEquals(List.of("abc", "def", "ghi"), result);
    }

    @Test
    public void solvePart1AddsIndependentBlocks() {
        String input = """
            1 2
            3 4
            + +
            """;

        Day06 solver = (Day06) Day06.build(input);
        assertEquals(Long.valueOf(10), solver.solvePart1());
    }

    @Test
    public void solvePart1MultipliesRows() {
        String input = """
            2
            3
            *
            """;

        Day06 solver = (Day06) Day06.build(input);
        assertEquals(Long.valueOf(6), solver.solvePart1());
    }

    @Test
    public void solvePart1ReadsMultiDigitNumbers() {
        String input = """
            12
            34
            ++
            """;

        Day06 solver = (Day06) Day06.build(input);
        assertEquals(Long.valueOf(46), solver.solvePart1());
    }

    @Test
    public void solvePart2ReadsColumnsRightToLeft() {
        String input = """
            12
            34
            ++
            """;

        Day06 solver = (Day06) Day06.build(input);
        assertEquals(Long.valueOf(37), solver.solvePart2());
    }

    @Test
    public void solvePart1IgnoresBlockWithoutOperator() {
        String input = """
            12
            34

            """;

        Day06 solver = (Day06) Day06.build(input);
        assertEquals(Long.valueOf(0), solver.solvePart1());
    }

    @Test
    public void solvePart1SeparatesBlocksUsingEmptyColumns() {
        String input = """
            1 5
            2 6
            + +
            """;

        Day06 solver = (Day06) Day06.build(input);
        assertEquals(Long.valueOf(14), solver.solvePart1());
    }
}
