package aoc.days.day05;

import aoc.core.DaySolver;
import aoc.days.day05.model.IDRange;
import aoc.days.day05.model.Pair;

import java.util.*;

public class Day05Solver implements DaySolver {
    private final Pair pair;

    public Day05Solver(Pair pair) {
        this.pair = pair;
    }

    @Override
    public Long solvePart1(){
        ///  Número de ingredientes frescos (los que su id esté incluido en algún rango de idRanges)
        return numIngredientsFresh(pair.idRanges(), pair.ids());
    }

    private static long numIngredientsFresh(List<IDRange> idRanges, List<Long> ids){
        return ids.stream()
                .filter(id ->
                        idRanges.stream()
                                .anyMatch(idRange ->
                                        id >= idRange.lowerBound() && id <= idRange.upperBound()))
                .count();
    }

    @Override
    public Long solvePart2(){
        /// Número de ingredientes frescos (todos los ids de idRanges)

        // Ordenar los rangos por lowerBound
        List<IDRange> sorted = pair.idRanges().stream()
                .sorted(Comparator.comparingLong(IDRange::lowerBound))
                .toList();

        return merge(sorted).stream()
                // Restar los límites de cada rango da el número de ids dentro del rango
                .mapToLong(r -> r.upperBound() - r.lowerBound() + 1)
                .sum();
    }

    private static List<IDRange> merge(List<IDRange> ranges) {
        // Fusionar rangos previamente ordenados
        //      *) solapados -> [10-14], [12-18] --> [10-18]
        //      *) contiguos -> [10-14], [14-18] --> [10-18]
        List<IDRange> result = new ArrayList<>();
        result.add(ranges.getFirst());

        for (int i = 1; i < ranges.size(); i++) {
            IDRange current = ranges.get(i);
            IDRange last = result.getLast();

            if (overlappedRange(current, last)) {
                result.set(
                        result.size() - 1,
                        new IDRange(
                                last.lowerBound(),
                                Math.max(last.upperBound(), current.upperBound())
                        ));
            } else result.add(current);
        }
        return result;
    }

    private static boolean overlappedRange(IDRange current, IDRange last) {
        return current.lowerBound() <= last.upperBound() + 1;
    }
}
