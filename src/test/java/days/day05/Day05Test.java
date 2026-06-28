package days.day05;

import aoc.core.DaySolver;
import aoc.days.day05.Day05;
import aoc.days.day05.Day05Parser;
import aoc.days.day05.model.IDRange;
import aoc.days.day05.model.Pair;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day05Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day05.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day05.build(input());
        assertEquals(821L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day05.build(input());
        assertEquals(344771884978261L, solver.solvePart2());
    }

    @Test
    public void parseSeparatesRangesAndIds() {
        String input = """
                10-20
                35
                40-50
                7
                """;

        Pair pair = new Day05Parser().parse(input);

        assertEquals(
                List.of(
                        new IDRange(10,20),
                        new IDRange(40,50)
                ),
                pair.idRanges()
        );
        assertEquals(List.of(35L,7L), pair.ids());
    }

    @Test
    public void parseIgnoresEmptyLines() {
        String input = """

                1-3

                5

                """;

        Pair pair = new Day05Parser().parse(input);
        assertEquals(1, pair.idRanges().size());
        assertEquals(1, pair.ids().size());
    }

    @Test
    public void countsIdsInsideRanges() {
        String input = """
                10-20
                30-40
                5
                10
                15
                20
                21
                35
                50
                """;

        assertEquals(4L, Day05.build(input).solvePart1());
    }

    @Test
    public void boundariesBelongToRange() {
        String input = """
                100-200
                100
                200
                99
                201
                """;

        assertEquals(2L, Day05.build(input).solvePart1());
    }

    @Test
    public void countsIndependentRanges() {
        String input = """
            1-3
            10-12
            """;

        assertEquals(6L, Day05.build(input).solvePart2());
    }

    @Test
    public void mergesOverlappingRanges() {
        String input = """
            10-15
            13-20
            """;

        assertEquals(11L, Day05.build(input).solvePart2());
    }

    @Test
    public void mergesAdjacentRanges() {
        String input = """
            1-5
            6-10
            """;

        assertEquals(10L, Day05.build(input).solvePart2());
    }

    @Test
    public void mergesSeveralRangesIntoOne() {
        String input = """
            1-3
            2-5
            6-8
            8-10
            """;

        assertEquals(10L, Day05.build(input).solvePart2());
    }

    @Test
    public void sortsRangesBeforeMerging() {
        String input = """
            20-30
            1-5
            4-10
            """;

        assertEquals(21L, Day05.build(input).solvePart2());
    }
}
