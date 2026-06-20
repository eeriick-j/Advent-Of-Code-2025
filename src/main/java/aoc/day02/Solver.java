package aoc.day02;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<IDRange> idRanges = InputParser.parse(RawInputReader.read("inputs/day02.txt"));
        System.out.println("Part 1: " + solvePart1(idRanges));
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
                int halfIndex = idStr.length() / 2;
                if(idStr.substring(0, halfIndex).equals(idStr.substring(halfIndex))){
                    sumInvalidIDS += id;
                }
            }
        }
        return sumInvalidIDS;
    }
}
