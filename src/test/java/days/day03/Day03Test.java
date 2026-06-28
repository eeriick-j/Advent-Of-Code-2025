package days.day03;

import aoc.core.DaySolver;
import aoc.days.day03.Day03;
import aoc.days.day03.Day03Parser;
import aoc.days.day03.model.Bank;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day03Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day03.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day03.build(input());
        assertEquals(17324L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day03.build(input());
        assertEquals(171846613143331L, solver.solvePart2());
    }

    @Test
    public void parseSingleLine() {
        Day03Parser parser = new Day03Parser();

        List<Bank> banks = parser.parse("12345");

        assertEquals(1, banks.size());
        assertEquals(List.of(1,2,3,4,5), banks.getFirst().voltages());
    }

    @Test
    public void parseMultipleLines() {
        Day03Parser parser = new Day03Parser();

        List<Bank> banks = parser.parse("""
                123
                987
                """);

        assertEquals(2, banks.size());
        assertEquals(List.of(1,2,3), banks.get(0).voltages());
        assertEquals(List.of(9,8,7), banks.get(1).voltages());
    }

    @Test
    public void sizeReturnsNumberOfDigits() {
        Bank bank = new Bank(List.of(1,2,3,4));
        assertEquals(4, bank.size());
    }

    @Test
    public void solvePart1SimpleExample() {
        Day03 solver = new Day03(new Day03Parser().parse("""
            123
            987
            """));

        assertEquals(Long.valueOf(121), solver.solvePart1());
    }

    @Test
    public void solvePart1RemovesDigitsToMaximizeNumber() {
        Day03 solver = new Day03(new Day03Parser().parse("1924"));
        assertEquals(Long.valueOf(94), solver.solvePart1());
    }

    @Test
    public void solvePart1AlreadyOptimalSequence() {
        Day03 solver = new Day03(new Day03Parser().parse("9876"));
        assertEquals(Long.valueOf(98), solver.solvePart1());
    }

    @Test
    public void solvePart1IncreasingSequence() {
        Day03 solver = new Day03(new Day03Parser().parse("1234"));
        assertEquals(Long.valueOf(34), solver.solvePart1());
    }

    @Test
    public void solvePart2KeepsWholeSequenceWhenAlreadyLength12() {
        Day03 solver = new Day03(new Day03Parser().parse("123456789012"));
        assertEquals(Long.valueOf(123456789012L), solver.solvePart2());
    }

    @Test
    public void solvePart2RemovesOneDigitToMaximizeNumber() {
        Day03 solver = new Day03(new Day03Parser().parse("1111111111119"));
        assertEquals(Long.valueOf(111111111119L), solver.solvePart2());
    }
}
