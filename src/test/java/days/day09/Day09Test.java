package days.day09;

import aoc.core.DaySolver;
import aoc.days.day09.Day09;
import aoc.days.day09.Day09Parser;
import aoc.days.day09.model.Point;
import aoc.days.day09.model.Polygon;
import aoc.days.day09.model.Segment;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class Day09Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day09.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day09.build(input());
        assertEquals(4781235324L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day09.build(input());
        assertEquals(1566935900L, solver.solvePart2());
    }

    @Test
    public void parsesBasicInput() {
        Day09Parser parser = new Day09Parser();
        String input = "1,2\n" +
                       "3,4\n" +
                       "5,6";
        List<Point> points = parser.parse(input);

        assertEquals(3, points.size());
        assertEquals(new Point(1,2), points.get(0));
        assertEquals(new Point(3,4), points.get(1));
        assertEquals(new Point(5,6), points.get(2));
    }

    @Test
    public void pointInsideSimpleSquare() {
        Polygon polygon = new Polygon(List.of(
                new Segment(0,0,2,0),
                new Segment(2,0,2,2),
                new Segment(2,2,0,2),
                new Segment(0,2,0,0)
        ));

        assertTrue(polygon.containsRectangle(new Point(0,0), new Point(2,2)));
    }

    @Test
    public void pointOutsideShouldFail() {
        Polygon polygon = new Polygon(List.of(
                new Segment(0,0,2,0),
                new Segment(2,0,2,2),
                new Segment(2,2,0,2),
                new Segment(0,2,0,0)
        ));

        assertFalse(polygon.containsRectangle(new Point(3,3), new Point(4,4)));
    }

    @Test
    public void rectangleCrossingEdgeShouldFail() {
        Polygon polygon = new Polygon(List.of(
                new Segment(0,0,2,0),
                new Segment(2,0,2,2),
                new Segment(2,2,0,2),
                new Segment(0,2,0,0)
        ));

        assertFalse(polygon.containsRectangle(new Point(1,1), new Point(3,1)));
    }

    @Test
    public void rectangleCompletelyInsideShouldPass() {
        Polygon polygon = new Polygon(List.of(
                new Segment(0,0,10,0),
                new Segment(10,0,10,10),
                new Segment(10,10,0,10),
                new Segment(0,10,0,0)
        ));

        assertTrue(polygon.containsRectangle(new Point(2,2), new Point(5,5)));
    }

    @Test
    public void maxAreaPart1SimpleCase() {
        Day09 solver = new Day09(List.of(
                new Point(0,0),
                new Point(2,2)
        ));

        assertEquals(Long.valueOf(9), solver.solvePart1());
    }

    @Test
    public void maxAreaPart2SingleRectangleInsidePolygon() {
        List<Point> pts = List.of(
                new Point(0,0),
                new Point(4,0),
                new Point(4,4),
                new Point(0,4)
        );

        Day09 solver = new Day09(pts);
        assertEquals(Long.valueOf(25), solver.solvePart2());
    }
}
