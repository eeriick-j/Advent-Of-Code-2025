package aoc.day01;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Rotation> rotations = InputParser.parse(RawInputReader.read("day01/a.txt"));
        System.out.println(solvePart1(rotations));
    }

    public static int solvePart1(List<Rotation> rotations) {
        int dialPointer = 50;
        int numPointsTozero = 0;

        for (Rotation rotation : rotations) {
            if (rotation.direction() == 'L') dialPointer = (dialPointer - rotation.times() + 100) % 100;
            else dialPointer = (dialPointer + rotation.times()) % 100;
            if(dialPointer == 0) numPointsTozero++;
        }

        return numPointsTozero;
    }

    public static int solvePart2(List<Rotation> rotations) {return 0;}
}
