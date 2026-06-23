package aoc.days.day12;

import aoc.days.day12.model.*;
import aoc.tasks.TXTFileReader;

import java.util.*;

public class Solver {

    public static void main(String[] args) {
        Puzzle puzzle = InputParser.parse(new TXTFileReader().read("inputs/day12.txt"));
        System.out.println("Part 1: " + solvePart1(puzzle.pieces(), puzzle.regions()));
    }

    private static int solvePart1(List<Piece> pieces, List<Region> regions) {
        int valid = 0;

        for (Region region : regions) {
            // 1. chequeo rápido de área (pruning barato)
            int requiredArea = 0;
            for (int i = 0; i < region.requirements().length; i++) {
                requiredArea += pieces.get(i).area() * region.requirements()[i];
            }
            if (requiredArea > region.area()) continue;

            // 2. construir opciones de placements por pieza
            Map<Piece, List<Placement>> options = new HashMap<>();

            for (Piece piece : pieces) {
                List<Placement> placements = new ArrayList<>();
                for (Piece variant : PieceTransformer.generateVariants(piece)) {
                    placements.addAll(generatePlacements(variant, region));
                }
                options.put(piece, placements);
            }

            // Iniciar backtraking desde las piezas más restrictivas (más grandes, menos placements)
            pieces.sort(Comparator.comparingInt(p -> options.get(p).size()));

            // 3. Algoritmo
            if (backtracking(0, pieces, options, new HashSet<>())) valid++;
        }

        return valid;
    }

    private static List<Placement> generatePlacements(Piece piece, Region region) {
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

    private static boolean backtracking(int index,
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