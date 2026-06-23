package aoc.days.day02;

import aoc.core.DaySolver;
import aoc.days.day02.model.IDRange;

import java.util.List;

public class Day02 implements DaySolver {
    private final List<IDRange> idRanges;

    public Day02(List<IDRange> idRanges) {
        this.idRanges = idRanges;
    }

    @Override
    public Long solvePart1() {
        ///  Suma de IDs inválidos (los que tengan dos mitades idénticas)
        long sumInvalidIDS = 0;

        for (IDRange range: idRanges) {
            for(long id=range.lowerBound(); id<=range.upperBound(); id++){
                // 1) Si es impar, no puede dividirse en dos mitades -> ID válido
                String idStr = Long.toString(id);
                if(idStr.length() % 2 != 0) continue;

                // 2) ¿primera mitad == segunda mitad?
                if(equalHalves(idStr)) sumInvalidIDS += id;
            }
        }
        return sumInvalidIDS;
    }

    private static boolean equalHalves(String idStr){
        int halfIndex = idStr.length() / 2;
        return idStr.substring(0, halfIndex).equals(idStr.substring(halfIndex));
    }

    @Override
    public Long solvePart2() {
        ///  Suma de IDs inválidos (los que tengan secuencias (dos o más) idénticas)
        long sumInvalidIDS = 0;
        for (IDRange range: idRanges) {
            for(long id=range.lowerBound(); id<=range.upperBound(); id++){
                if(hasTwoOrMoreEqualSequence(Long.toString(id))) sumInvalidIDS += id;
            }
        }
        return sumInvalidIDS;
    }

    private static boolean hasTwoOrMoreEqualSequence(String idStr){
        /// s            = 1212
        /// s+s          = 12121212
        /// indexOf(...) = 4
        /*
         Si hay algún bloque repetido, aparecerá desplazado en el string duplicado antes
         del final original (s.length()).
         Duplicarlo crea todas las posibles rotaciones
         */
        return (idStr + idStr).indexOf(idStr, 1) != idStr.length();
    }
}
