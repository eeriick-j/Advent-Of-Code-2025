package aoc.days.day12;

import aoc.DayRegistry;
import aoc.core.DaySolver;
import aoc.days.day12.model.*;

import java.util.*;

public class Day12 implements DaySolver {
    private final Puzzle puzzle;

    public Day12(Puzzle puzzle) {
        this.puzzle = puzzle;
    }

    public static DaySolver build(String input) {
        return new Day12(new Day12Parser().parse(input));
    }

    static {
        DayRegistry.register(12, Day12::build);
    }

    @Override
    public Integer solvePart1() {
        List<Piece> pieces = puzzle.pieces();
        List<Region> regions = puzzle.regions();
        int valid = 0;

        for (Region region : regions) {
            // 1. Chequeo rápido de área (pruning)
            int requiredArea = 0;
            for (int i = 0; i < region.requirements().length; i++) {
                requiredArea += pieces.get(i).area() * region.requirements()[i];
            }
            if (requiredArea > region.area()) continue;

            // 2. Construir opciones de placements por pieza
            Map<Piece, List<Placement>> variants = new HashMap<>();
            for (Piece piece : pieces) {
                List<Placement> placements = new ArrayList<>();
                for (Piece variant : PieceTransformer.generateVariants(piece)) {
                    placements.addAll(generatePlacements(variant, region));
                }
                variants.put(piece, placements);
            }
            // Iniciar backtraking desde las piezas más restrictivas (más grandes, menos placements)
            pieces.sort(Comparator.comparingInt(p -> variants.get(p).size()));

            // 3. Iniciar backtracking
            if (backtracking(0, pieces, variants, new HashSet<>())) valid++;
        }
        return valid;
    }

    private List<Placement> generatePlacements(Piece piece, Region region) {
        List<Placement> placements = new ArrayList<>();
        int width = region.width();
        int height = region.height();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                List<Cell> cells = new ArrayList<>();
                boolean ok = true;
                for (Cell c : piece.cells()) {
                    int nx = x + c.x();
                    int ny = y + c.y();
                    if (nx < 0 || ny < 0 || nx >= width || ny >= height) {
                        ok = false;
                        break;
                    }
                    cells.add(new Cell(nx, ny));
                }
                if (ok) placements.add(new Placement(piece, x, y, cells));
            }
        }
        return placements;
    }

    private boolean backtracking(int index,
                         List<Piece> pieces,
                         Map<Piece, List<Placement>> options,
                         Set<Cell> occupied) {
        if (index == pieces.size()) return true;
        Piece piece = pieces.get(index);

        for (Placement placement : options.get(piece)) {
            if (!collides(placement, occupied)) {
                add(occupied, placement);
                if (backtracking(index + 1, pieces, options, occupied)) return true;
                remove(occupied, placement);
            }
        }
        return false;
    }

    private boolean collides(Placement p, Set<Cell> occupied) {
        for (Cell c : p.cells()) if (occupied.contains(c)) return true;
        return false;
    }

    private void add(Set<Cell> occupied, Placement p) {
        occupied.addAll(p.cells());
    }

    private void remove(Set<Cell> occupied, Placement p) {
        p.cells().forEach(occupied::remove);
    }

    @Override
    public Object solvePart2() {
        return null;
    }
}