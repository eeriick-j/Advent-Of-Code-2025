package aoc.days.day02;

import aoc.io.TXTFileReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<IDRange> idRanges = InputParser.parse(new TXTFileReader().read("inputs/day02.txt"));
        System.out.println("Part 1: " + solvePart1(idRanges));
        System.out.println("Part 2: " + solvePart2(idRanges));
    }

    public static long solvePart1(List<IDRange> idRanges) {
        ///  Suma de IDs inválidos (los que tengan dos mitades idénticas)
        long sumInvalidIDS = 0;

        for (IDRange range: idRanges) {
            for(long id=range.lowerBound(); id<=range.upperBound(); id++){
                // 1) Si es impar, no puede dividirse en dos mitades -> ID válido
                String idStr = Long.toString(id);
                if(idStr.length() % 2 != 0) continue;

                // 2) ¿primera mitad == segunda mitad?
                if(bothHalvesEquals(idStr)) sumInvalidIDS += id;
            }
        }
        return sumInvalidIDS;
    }

    public static boolean bothHalvesEquals(String idStr){
        int halfIndex = idStr.length() / 2;
        return idStr.substring(0, halfIndex).equals(idStr.substring(halfIndex));
    }

    public static long solvePart2(List<IDRange> idRanges) {
        ///  Suma de IDs inválidos (los que tengan secuencias (dos o más) idénticas)
        long sumInvalidIDS = 0;
        for (IDRange range: idRanges) {
            for(long id=range.lowerBound(); id<=range.upperBound(); id++){
                if(hasTwoOrMoreEqualSequence(Long.toString(id))) sumInvalidIDS += id;
            }
        }
        return sumInvalidIDS;
    }

    public static boolean hasTwoOrMoreEqualSequence(String idStr){
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
