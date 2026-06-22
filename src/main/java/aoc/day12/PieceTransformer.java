package aoc.day12;

import aoc.day12.model.Cell;
import aoc.day12.model.Piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PieceTransformer {
    public static Piece rotate90(Piece p) {
        List<Cell> rotated = p.cells().stream()
                .map(c -> new Cell(-c.y(), c.x()))
                .toList();

        return new Piece(p.id(), normalize(rotated));
    }

    public static Piece flipX(Piece p) {
        List<Cell> flipped = p.cells().stream()
                .map(c -> new Cell(-c.x(), c.y()))
                .toList();

        return new Piece(p.id(), normalize(flipped));
    }

    public static List<Cell> normalize(List<Cell> cells) {
        int minX = cells.stream().mapToInt(Cell::x).min().orElse(0);
        int minY = cells.stream().mapToInt(Cell::y).min().orElse(0);

        return cells.stream()
                .map(c -> new Cell(c.x() - minX, c.y() - minY))
                .toList();
    }

    public static Set<Piece> generateVariants(Piece p) {
        Set<Piece> variants = new HashSet<>();
        Piece current = p;

        for (int r = 0; r < 4; r++) {
            current = rotate90(current);
            variants.add(current);
            variants.add(flipX(current));
        }

        return variants;
    }
}
