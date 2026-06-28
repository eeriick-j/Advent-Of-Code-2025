package days.day08;

import aoc.core.DaySolver;
import aoc.days.day08.DSU;
import aoc.days.day08.Day08;
import aoc.days.day08.Day08Parser;
import aoc.days.day08.model.Box;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Day08Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day08.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day08.build(input());
        assertEquals(67488L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day08.build(input());
        assertEquals(3767453340L, solver.solvePart2());
    }

    @Test
    public void parsesBoxesCorrectly() {
        String input = """
                1,2,3
                4,5,6
                7,8,9
                """;
        List<Box> boxes = new Day08Parser().parse(input);

        assertEquals(3, boxes.size());
        assertEquals(new Box(0,1,2,3), boxes.get(0));
        assertEquals(new Box(1,4,5,6), boxes.get(1));
        assertEquals(new Box(2,7,8,9), boxes.get(2));
    }

    @Test
    public void unionConnectsElements() {
        DSU dsu = new DSU(4);
        dsu.union(0, 1);

        assertEquals(dsu.find(0), dsu.find(1));
        assertNotEquals(dsu.find(0), dsu.find(2));
    }

    @Test
    public void transitiveUnionWorks() {
        DSU dsu = new DSU(5);
        dsu.union(0, 1);
        dsu.union(1, 2);

        assertEquals(dsu.find(0), dsu.find(2));
    }

    @Test
    public void unionAlreadyConnectedDoesNothing() {
        DSU dsu = new DSU(3);
        dsu.union(0, 1);
        int root = dsu.find(0);
        dsu.union(1, 0);

        assertEquals(root, dsu.find(0));
        assertEquals(root, dsu.find(1));
    }

    @Test
    public void part2SimpleExample() {
        String input = """
            0,0,0
            1,0,0
            10,0,0
            """;

        Day08 solver = (Day08) Day08.build(input);
        assertEquals(Long.valueOf(10), solver.solvePart2());
    }
}
