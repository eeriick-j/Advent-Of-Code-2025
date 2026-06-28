package days.day02;

import aoc.core.DaySolver;
import aoc.days.day02.Day02;
import aoc.days.day02.Day02Parser;
import aoc.days.day02.model.IDRange;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day02Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day02.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day02.build(input());
        assertEquals(35367539282L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day02.build(input());
        assertEquals(45814076230L, solver.solvePart2());
    }

    @Test
    public void parsesSingleRange() {
        List<IDRange> ranges = new Day02Parser().parse("10-20");

        assertEquals(1, ranges.size());
        assertEquals(new IDRange(10, 20), ranges.getFirst());
    }

    @Test
    public void parsesMultipleRanges() {
        List<IDRange> ranges = new Day02Parser().parse("1-2,5-8,20-30");

        assertEquals(List.of(
                new IDRange(1, 2),
                new IDRange(5, 8),
                new IDRange(20, 30)
        ), ranges);
    }

    @Test
    public void oddLengthIdsAreValid() {
        Day02 solver = new Day02(List.of(new IDRange(111, 111)));
        assertEquals(Long.valueOf(0), solver.solvePart1());
    }

    @Test
    public void identicalHalvesAreInvalid() {
        Day02 solver = new Day02(List.of(new IDRange(1212, 1212)));
        assertEquals(Long.valueOf(1212), solver.solvePart1());
    }

    @Test
    public void differentHalvesAreValid() {
        Day02 solver = new Day02(List.of(new IDRange(1234, 1234)));
        assertEquals(Long.valueOf(0), solver.solvePart1());
    }

    @Test
    public void sumsOnlyInvalidIds() {
        Day02 solver = new Day02(List.of(new IDRange(1212, 1214)));
        assertEquals(Long.valueOf(1212), solver.solvePart1());
    }

    @Test
    public void repeatedPatternIsInvalid() {
        Day02 solver = new Day02(List.of(new IDRange(1212, 1212)));
        assertEquals(Long.valueOf(1212), solver.solvePart2());
    }

    @Test
    public void repeatedSingleDigitPatternIsInvalid() {
        Day02 solver = new Day02(List.of(new IDRange(1111, 1111)));
        assertEquals(Long.valueOf(1111), solver.solvePart2());
    }

    @Test
    public void nonRepeatedPatternIsValid() {
        Day02 solver = new Day02(List.of(new IDRange(1234, 1234)));
        assertEquals(Long.valueOf(0), solver.solvePart2());
    }

    @Test
    public void sumsAllInvalidIds() {
        Day02 solver = new Day02(List.of(new IDRange(1212, 1213)));
        assertEquals(Long.valueOf(1212), solver.solvePart2());
    }
}
