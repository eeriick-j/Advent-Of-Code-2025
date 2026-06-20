package aoc.day01;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Rotation> rotations = InputParser.parse(RawInputReader.read("day01/a.txt"));
        System.out.println("Part 1: " + solvePart1(rotations));
        System.out.println("Part 2: " + solvePart2(rotations));
    }

    public static int solvePart1(List<Rotation> rotations) {
        /**
         * Número de veces que el dial pasa por 0 para cada rotación completa
         * */
        Dial dial = new Dial();
        for (Rotation rotation : rotations) dial.rotateFull(rotation);
        return dial.numPointsToZero();
    }

    public static int solvePart2(List<Rotation> rotations) {
        /**
         * Número de veces que el dial pasa por 0 durante cada click individual para cada rotación
         * */
        Dial dial = new Dial();
        for (Rotation rotation : rotations) dial.rotateClickByClick(rotation);
        return dial.numClicksToZero();
    }
}
