package aoc.day05;

import aoc.day02.IDRange;
import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        Pair pair = InputParser.parse(RawInputReader.read("inputs/day05.txt"));
        System.out.println("First part: " + solvePart1(pair));
    }

    public static long solvePart1(Pair pair){
        ///  Número de ingredientes frescos (los que su id esté incluido en algún rango de idRanges)
        return numIngredientsFresh(pair.idRanges(), pair.ids());
    }

    public static long numIngredientsFresh(List<IDRange> idRanges, List<Long> ids){
        long numIngredientsFresh = 0;
        for(long id: ids){
            for(IDRange idRange: idRanges){
                if(id >= idRange.lowerBound() && id <= idRange.upperBound()) {
                    numIngredientsFresh++;
                    break;                  // No contar más de una vez el mismo 'id' si es válido
                }
            }
        }
        return numIngredientsFresh;
    }
}
