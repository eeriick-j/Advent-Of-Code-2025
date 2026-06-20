package aoc.day01;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Rotation> rotations = InputParser.parse(RawInputReader.read("day01/a.txt"));
        for (Rotation rotation : rotations) {
            System.out.println(rotation);
        }
    }
}
