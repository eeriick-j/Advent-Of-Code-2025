package aoc.day01;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Rotation> rotations = InputParser.parse(RawInputReader.read("day01/a.txt"));
        System.out.println(solvePart1(rotations));
        System.out.println(solvePart2(rotations));
    }

    public static int solvePart1(List<Rotation> rotations) {
        /**
         * Número de veces que el dial pasa por 0 para cada rotación completa
         * */
        int dialPointer = 50;
        int numPointsToZero = 0;

        for (Rotation rotation : rotations) {
            int sign = (rotation.direction() == 'L') ? -1 : 1;
            // Rotación completa
            dialPointer = (dialPointer + sign * rotation.times() + 100) % 100;
            if(dialPointer == 0) numPointsToZero++;
        }

        return numPointsToZero;
    }

    public static int solvePart2(List<Rotation> rotations) {
        /**
         * Número de veces que el dial pasa por 0 durante cada click individual para cada rotación
         * */
        int dialPointer = 50;
        int numPointsToZero = 0;

        for (Rotation rotation : rotations) {
            int sign = (rotation.direction() == 'L') ? -1 : 1;
            for(int t=0; t<rotation.times(); t++) {
                // Rotación de 1 click
                dialPointer = (dialPointer + sign + 100) % 100;
                if(dialPointer == 0) numPointsToZero++;
            }
        }

        return numPointsToZero;
    }
}
