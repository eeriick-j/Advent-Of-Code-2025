package days.day07;

import aoc.core.DaySolver;
import aoc.days.day07.Day07;
import aoc.days.day07.Day07Parser;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
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

    @Test
    public void parseGrid() {
        String input = "S..\n" +
                        ".^.\n" +
                        "...";
        char[][] expected = {
                {'S', '.', '.'},
                {'.', '^', '.'},
                {'.', '.', '.'}
        };
        assertArrayEquals(expected, new Day07Parser().parse(input));
    }

    @Test
    public void part1WithoutSplits() {
        String input = "S..\n" +
                       "...\n" +
                       "...";

        Day07 solver = (Day07) Day07.build(input);
        assertEquals(Integer.valueOf(0), solver.solvePart1());
    }

    @Test
    public void part1OneSplit() {
        String input = ".S.\n" +
                       ".^.\n" +
                       "...";

        Day07 solver = (Day07) Day07.build(input);
        assertEquals(Integer.valueOf(1), solver.solvePart1());
    }

    @Test
    public void part1MultipleSplits() {
        String input = "..S..\n" +
                       "..^..\n" +
                       ".^.^.\n" +
                       ".....";

        Day07 solver = (Day07) Day07.build(input);
        assertEquals(Integer.valueOf(3), solver.solvePart1());
    }

    @Test
    public void part1SplitAtLeftBorder() {
        String input = "S..\n" +
                       "^..\n" +
                       "...";

        Day07 solver = (Day07) Day07.build(input);
        assertEquals(Integer.valueOf(1), solver.solvePart1());
    }

    @Test
    public void part2SinglePath() {
        String input = "S..\n" +
                       "...\n" +
                       "...";

        Day07 solver = (Day07) Day07.build(input);
        assertEquals(Long.valueOf(1), solver.solvePart2());
    }

    @Test
    public void part2OneSplitProducesTwoPaths() {
        String input = ".S.\n" +
                       ".^.\n" +
                       "...";

        Day07 solver = (Day07) Day07.build(input);
        assertEquals(Long.valueOf(2), solver.solvePart2());
    }

    @Test
    public void part2MultipleSplitsProduceFourPaths() {
        String input =
                "..S..\n" +
                        "..^..\n" +
                        ".^.^.\n" +
                        ".....";

        Day07 solver = (Day07) Day07.build(input);
        assertEquals(Long.valueOf(4), solver.solvePart2());
    }
}
