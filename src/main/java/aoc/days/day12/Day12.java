package aoc.days.day12;

import aoc.DayRegistry;
import aoc.core.DaySolver;
import aoc.days.day12.algorithm.Search;
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
            // El area de todas las piezas debe ser menor que el de la región
            if (!hasEnoughArea(pieces, region)) continue;
            // 2. Generar todas las opciones de colocación de todas las piezas dentro de la región
            Map<Piece, List<Placement>> placementOptions = Search.generateAllPlacementOptions(pieces, region);
            // Iniciar backtraking desde las piezas más restrictivas (más grandes, menos placements)
            pieces.sort(Comparator.comparingInt(p -> placementOptions.get(p).size()));
            if (Search.backtracking(0, pieces, placementOptions, new HashSet<>())) valid++;
        }
        return valid;
    }

    private static boolean hasEnoughArea(List<Piece> pieces, Region region) {
        int requiredArea = 0;
        for (int i = 0; i < region.requirements().length; i++) {
            requiredArea += pieces.get(i).area() * region.requirements()[i];
        }
        return requiredArea <= region.area();
    }

    @Override
    public Object solvePart2() {
        return null;
    }
}