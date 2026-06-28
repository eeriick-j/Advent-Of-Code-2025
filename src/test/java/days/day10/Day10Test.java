package days.day10;

import aoc.core.DaySolver;
import aoc.days.day10.Day10;
import aoc.days.day10.Day10Parser;
import aoc.days.day10.algorithm.Search;
import aoc.days.day10.model.Machine;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Day10Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day10.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day10.build(input());
        assertEquals(527L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day10.build(input());
        assertEquals(19810L, solver.solvePart2());
    }

    @Test
    public void bfsSimpleToggleGraphReachesTarget() {
        Machine m = new Machine(1, new int[]{1}, new int[]{});
        assertEquals(1, Search.bfs(m));
    }

    @Test
    public void bfsUnreachableReturnsMinusOne() {
        Machine m = new Machine(2, new int[]{1}, new int[]{});
        assertEquals(-1, Search.bfs(m));
    }

    @Test
    public void dfsSingleBitExactReductionReturnsOneStep() {
        Machine m = new Machine(0, new int[]{1}, new int[]{1});
        assertEquals(1L, Search.dfs(m));
    }

    @Test
    public void parseValidLineBuildsMachineCorrectly() {
        String input = "[#.#] (0,1) (2) {3,4}";
        Day10Parser parser = new Day10Parser();
        List<Machine> machines = parser.parse(input);

        assertEquals(1, machines.size());
        assertEquals(1 | 4, machines.getFirst().target());
        assertArrayEquals(new int[]{1 | 2, 4}, machines.getFirst().buttons());
        assertArrayEquals(new int[]{3, 4}, machines.getFirst().voltages());
    }

    @Test
    public void parseEmptyLinesAreIgnored() {
        String input = "\n[#](0){1}\n\n";

        Day10Parser parser = new Day10Parser();
        List<Machine> machines = parser.parse(input);
        assertEquals(1, machines.size());
    }

    @Test(expected = IllegalStateException.class)
    public void parseMissingBracketsThrows() {
        String input = "(0,1){1}";

        new Day10Parser().parse(input);
    }

    @Test
    public void solvePart1SingleMachine() {
        Machine m = new Machine(1, new int[]{1}, new int[]{});

        DaySolver solver = new Day10(List.of(m));
        assertEquals(1L, solver.solvePart1());
    }

    @Test
    public void solvePart2SingleMachine() {
        Machine m = new Machine(0, new int[]{1}, new int[]{1});

        DaySolver solver = new Day10(List.of(m));
        assertEquals(1L, solver.solvePart2());
    }
}
