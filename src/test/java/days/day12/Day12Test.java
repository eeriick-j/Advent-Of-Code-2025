package days.day12;

import aoc.core.DaySolver;
import aoc.days.day12.Day12;
import aoc.days.day12.Day12Parser;
import aoc.days.day12.PieceTransformer;
import aoc.days.day12.algorithm.Search;
import aoc.days.day12.model.Cell;
import aoc.days.day12.model.Piece;
import aoc.days.day12.model.Puzzle;
import aoc.days.day12.model.Region;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Day12Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day12.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day12.build(input());
        assertEquals(408, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day12.build(input());
        assertNull(solver.solvePart2());
    }

    @Test
    public void parseShouldBuildPiecesAndRegions() {
        String input =
                """
                1:
                ##
                #.

                2:
                ##
                
                2x2: 1 1
                """;
        Puzzle puzzle = new Day12Parser().parse(input);

        assertEquals(2, puzzle.pieces().size());
        assertEquals(1, puzzle.regions().size());
        assertEquals(2, puzzle.regions().getFirst().width());
        assertEquals(2, puzzle.regions().getFirst().height());
    }

    @Test
    public void parseShouldExtractCellsCorrectly() {
        String input =
                """
                1:
                ##
                #.
                
                2x2: 1 1
                """;
        Puzzle puzzle = new Day12Parser().parse(input);
        var piece = puzzle.pieces().getFirst();

        assertEquals(3, piece.cells().size());
    }

    @Test
    public void rotate90ShouldRotateCorrectly() {
        Piece p = new Piece(1, List.of(new Cell(0, 0), new Cell(1, 0)));
        Piece rotated = PieceTransformer.rotate90(p);

        assertTrue(rotated.cells().contains(new Cell(0, 0)));
        assertTrue(rotated.cells().contains(new Cell(0, 1)));
        assertEquals(2, rotated.cells().size());
    }

    @Test
    public void flipXShouldMirrorCorrectly() {
        Piece p = new Piece(1, List.of(new Cell(0, 0), new Cell(1, 0)));
        Piece flipped = PieceTransformer.flipX(p);

        assertTrue(flipped.cells().contains(new Cell(0, 0)));
        assertTrue(flipped.cells().contains(new Cell(-1, 0)) || flipped.cells().contains(new Cell(1, 0)));
    }

    @Test
    public void normalizeShouldShiftToOrigin() {
        List<Cell> input = List.of(new Cell(2, 3), new Cell(3, 4));
        List<Cell> normalized = PieceTransformer.normalize(input);

        assertTrue(normalized.contains(new Cell(0, 0)));
        assertTrue(normalized.contains(new Cell(1, 1)));
    }

    @Test
    public void generateVariantsShouldProduceMultipleTransformations() {
        Piece p = new Piece(1, List.of(new Cell(0, 0), new Cell(1, 0)));
        var variants = PieceTransformer.generateVariants(p);

        assertFalse(variants.isEmpty());
        assertTrue(variants.size() >= 2);
    }

    @Test
    public void generatePlacementsShouldReturnValidPositions() {
        Piece piece = new Piece(1, List.of(
                new Cell(0, 0),
                new Cell(1, 0)
        ));
        Region region = new Region(3, 2, new int[]{});
        var placements = Search.generatePlacements(piece, region);

        assertFalse(placements.isEmpty());

        placements.forEach(p ->
                p.cells().forEach(c -> {
                    assertTrue(c.x() >= 0 && c.x() < 3);
                    assertTrue(c.y() >= 0 && c.y() < 2);
                })
        );
    }

    @Test
    public void backtrackingShouldSolveSimpleFit() {
        Region region = new Region(2, 1, new int[]{1});
        Piece piece = new Piece(1, List.of(new Cell(0, 0), new Cell(1, 0)));

        var options = Search.generateAllPlacementOptions(List.of(piece), region);
        boolean result = Search.backtracking(0, List.of(piece), options, new HashSet<>());

        assertTrue(result);
    }

    @Test
    public void backtrackingShouldFailWhenImpossible() {
        Piece piece = new Piece(1, List.of(
                new Cell(0, 0),
                new Cell(1, 0),
                new Cell(2, 0)
        ));
        Region region = new Region(2, 1, new int[]{1});

        var options = Search.generateAllPlacementOptions(List.of(piece), region);
        boolean result = Search.backtracking(0, List.of(piece), options, new java.util.HashSet<>());

        assertFalse(result);
    }

    @Test
    public void solvePart1_shouldRunWithoutCrash() {
        String input =
                """
                1:
                #
                
                2:
                #
                
                1x1: 1 1
                """;

        DaySolver solver = Day12.build(input);
        assertEquals(0, solver.solvePart1());
    }

    @Test
    public void solvePart2_shouldBeNull() {
        String input =
                """
                1:
                #
                
                1x1: 1 1
                """;

        DaySolver solver = Day12.build(input);
        assertNull(solver.solvePart2());
    }
}
