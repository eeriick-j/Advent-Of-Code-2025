package aoc.day02;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<IDRange> idRanges = InputParser.parse(RawInputReader.read("inputs/day02.txt"));
        for (IDRange idRange : idRanges) System.out.println(idRange);
    }
}
