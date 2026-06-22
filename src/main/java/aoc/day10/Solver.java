package aoc.day10;

import tasks.RawInputReader;

import java.util.Arrays;
import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Machine> machines = InputParser.parse(RawInputReader.read("inputs/day10.txt"));
        for (Machine machine : machines) {
            System.out.println(machine.target() + ":" + Arrays.toString(machine.buttons()));
        }
    }
}