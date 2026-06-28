package days.day04;

import aoc.core.DaySolver;
import aoc.days.day04.Day04;
import aoc.days.day04.Day04Parser;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
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

    @Test
    public void parsesMatrixCorrectly() {
        String input =
                "@.\n" +
                        ".@";

        char[][] result = new Day04Parser().parse(input);

        assertEquals(2, result.length);
        assertArrayEquals(new char[]{'@','.'}, result[0]);
        assertArrayEquals(new char[]{'.','@'}, result[1]);
    }

    @Test
    public void singleRollIsAccessible() {
        DaySolver solver = Day04.build("@");
        assertEquals(1, solver.solvePart1());
    }

    @Test
    public void singleRollRemovedInPart2() {
        DaySolver solver = Day04.build("@");
        assertEquals(1, solver.solvePart2());
    }

    @Test
    public void isolatedRollsAreAccessible() {
        String input = "@.@\n" +
                        "...\n" +
                        "@.@";

        DaySolver solver = Day04.build(input);
        assertEquals(4, solver.solvePart1());
    }

    @Test
    public void block2x2AllAccessible() {
        String input = "@@\n" +
                       "@@";

        DaySolver solver = Day04.build(input);
        assertEquals(4, solver.solvePart1());
    }

    @Test
    public void full3x3OnlyCornersAccessible() {
        String input = "@@@\n" +
                       "@@@\n" +
                       "@@@";

        DaySolver solver = Day04.build(input);
        assertEquals(4, solver.solvePart1());
    }

    @Test
    public void part2RemovesRollsInSeveralRounds() {
        String input = "@@@\n" +
                       "@@@\n" +
                       "@@@";

        DaySolver solver = Day04.build(input);
        assertEquals(9, solver.solvePart2());
    }

    @Test
    public void emptyWarehouse() {
        String input = "...\n" +
                       "...";

        DaySolver solver = Day04.build(input);
        assertEquals(0, solver.solvePart1());
        assertEquals(0, solver.solvePart2());
    }

    @Test
    public void cornerRollHasNoNeighbours() {
        DaySolver solver = Day04.build(
                  "@.\n" +
                        ".."
        );
        assertEquals(1, solver.solvePart1());
    }












}