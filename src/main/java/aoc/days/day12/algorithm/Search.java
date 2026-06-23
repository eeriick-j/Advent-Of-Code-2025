package aoc.days.day12.algorithm;

import aoc.days.day12.PieceTransformer;
import aoc.days.day12.model.Cell;
import aoc.days.day12.model.Piece;
import aoc.days.day12.model.Placement;
import aoc.days.day12.model.Region;

import java.util.*;

public class Search {
    public static List<Placement> generatePlacements(Piece piece, Region region) {
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

    public static Map<Piece, List<Placement>> generateAllPlacementOptions(List<Piece> pieces, Region region) {
        Map<Piece, List<Placement>> placementOptions = new HashMap<>();
        for (Piece piece : pieces) {
            List<Placement> placements = new ArrayList<>();
            // Para cada las variantes de una pieza (original, rotada Xº, reflejada, combos...)
            for (Piece variant : PieceTransformer.generateVariants(piece)) {
                // Generar todas las colocaciones de la variante que esa región permita, sin salirse
                placements.addAll(Search.generatePlacements(variant, region));
            }
            placementOptions.put(piece, placements);
        }
        return placementOptions;
    }

    public static boolean backtracking(int index,
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

    private static boolean collides(Placement p, Set<Cell> occupied) {
        for (Cell c : p.cells()) if (occupied.contains(c)) return true;
        return false;
    }

    private static void add(Set<Cell> occupied, Placement p) {
        occupied.addAll(p.cells());
    }

    private static void remove(Set<Cell> occupied, Placement p) {
        p.cells().forEach(occupied::remove);
    }
}
