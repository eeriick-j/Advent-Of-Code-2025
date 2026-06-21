package aoc.day09;

import tasks.RawInputReader;

import java.util.*;

public class Solver {
    public static void main(String[] args) {
        List<Point> points = InputParser.parse(RawInputReader.read("inputs/day09.txt"));
        System.out.println("Part 1: " + solvePart1(points));
        System.out.println("Part 2: " + solvePart2(points));
    }

    public static long solvePart1(List<Point> points) {
        ///  Área máxima entre el rectángulo que forman dos puntos cualesquiera
        long maxArea = 0;
        for(int i=0; i<points.size(); i++) {
            for(int j=i+1; j<points.size(); j++) {
                maxArea = Math.max(maxArea, area(points.get(i), points.get(j)));
            }
        }
        return maxArea;
    }

    private static long area(Point a, Point b) {
        long dx = Math.abs(a.x() - b.x() + 1);
        long dy = Math.abs(a.y() - b.y() + 1);
        return dx * dy;
    }


}
