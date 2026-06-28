package days.day01;

import aoc.core.DaySolver;
import aoc.days.day01.Day01;
import aoc.days.day01.Day01Parser;
import aoc.days.day01.Dial;
import aoc.days.day01.model.Rotation;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day01Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day01.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day01.build(input());
        assertEquals(1123, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day01.build(input());
        assertEquals(6695, solver.solvePart2());
    }

    @Test
    public void parseSeveralRotations() {
        String input = """
                L10
                R3
                L99
                """;

        List<Rotation> rotations = new Day01Parser().parse(input);

        assertEquals(3, rotations.size());
        assertEquals(new Rotation('L', 10), rotations.get(0));
        assertEquals(new Rotation('R', 3), rotations.get(1));
        assertEquals(new Rotation('L', 99), rotations.get(2));
    }

    @Test
    public void rotateFullDoesNotCountWhenNotEndingAtZero() {
        Dial dial = new Dial();
        dial.rotateFull(new Rotation('R', 10));

        assertEquals(0, dial.numPointsToZero());
    }

    @Test
    public void rotateFullCountsWhenEndingAtZero() {
        Dial dial = new Dial();
        dial.rotateFull(new Rotation('R', 50));

        assertEquals(1, dial.numPointsToZero());
    }

    @Test
    public void rotateFullCountsSeveralTimes() {
        Dial dial = new Dial();
        dial.rotateFull(new Rotation('R', 50));
        dial.rotateFull(new Rotation('R', 100));
        dial.rotateFull(new Rotation('L', 100));

        assertEquals(3, dial.numPointsToZero());
    }

    @Test
    public void rotateClickByClickCountsCrossingZero() {
        Dial dial = new Dial();
        dial.rotateClickByClick(new Rotation('R', 50));

        assertEquals(1, dial.numClicksToZero());
    }

    @Test
    public void rotateClickByClickCountsMultipleCrossings() {
        Dial dial = new Dial();
        dial.rotateClickByClick(new Rotation('R', 150));

        assertEquals(2, dial.numClicksToZero());
    }

    @Test
    public void rotateClickByClickCountsCrossingZeroLeft() {
        Dial dial = new Dial();
        dial.rotateClickByClick(new Rotation('L', 50));

        assertEquals(1, dial.numClicksToZero());
    }

    @Test
    public void solvePart1SmallExample() {
        String input = """
            R50
            R50
            L100
            """;

        DaySolver day = Day01.build(input);
        assertEquals(1, day.solvePart1());
    }

    @Test
    public void solvePart2SmallExample() {
        String input = """
            R150
            """;

        DaySolver day = Day01.build(input);
        assertEquals(2, day.solvePart2());
    }
}