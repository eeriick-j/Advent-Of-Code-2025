package aoc.day12;

import aoc.day12.model.Piece;
import aoc.day12.model.Puzzle;
import aoc.day12.model.Region;
import tasks.RawInputReader;

import java.util.Arrays;
import java.util.List;

public class Solver {
    public static void main(String[] args) {
        Puzzle puzzle = InputParser.parse(RawInputReader.read("inputs/day12.txt"));
        solvePart1(puzzle.pieces(), puzzle.regions());
    }

    public static void solvePart1(List<Piece> pieces, List<Region> regions) {
        // El área de los requerimientos de cualquier región no puede superar el área de la región
        int VALID = regions.size();
        for(Region region : regions){
            int requirementsRegionArea = 0;
            for(int i=0; i< region.requirements().length; i++) requirementsRegionArea += (pieces.get(i).area() * region.requirements()[i]);
            if(requirementsRegionArea > region.area()) VALID--;
        }
        System.out.println(VALID);
    }
}
