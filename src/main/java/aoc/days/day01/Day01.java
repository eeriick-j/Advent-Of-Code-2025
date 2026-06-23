package aoc.days.day01;

import aoc.core.DaySolver;
import aoc.days.day01.model.Rotation;

import java.util.List;

public class Day01 implements DaySolver {
    private final List<Rotation> rotations;

    public Day01(List<Rotation> rotations) {
        this.rotations = rotations;
    }

    @Override
    public Integer solvePart1() {
        ///Número de veces que el dial pasa por 0 para cada rotación completa
        Dial dial = new Dial();
        for (Rotation rotation : rotations) dial.rotateFull(rotation);
        return dial.numPointsToZero();
    }

    @Override
    public Integer solvePart2() {
        /// Número de veces que el dial pasa por 0 durante cada click individual para cada rotación
        Dial dial = new Dial();
        for (Rotation rotation : rotations) dial.rotateClickByClick(rotation);
        return dial.numClicksToZero();
    }
}
