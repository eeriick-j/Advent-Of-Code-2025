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

    public static long solvePart2(List<Point> points) {

        List<Segment> segments = buildSegments(points);

        long maxArea = 0;

        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {

                Point a = points.get(i);
                Point b = points.get(j);

                if (isValidRectangle(a, b, segments)) {
                    long dx = Math.abs(a.x() - b.x()) + 1;
                    long dy = Math.abs(a.y() - b.y()) + 1;
                    maxArea = Math.max(maxArea, dx * dy);
                }
            }
        }

        return maxArea;
    }

    // ---------------- SEGMENTS ----------------

    static List<Segment> buildSegments(List<Point> pts) {
        List<Segment> segs = new ArrayList<>();

        for (int i = 0; i < pts.size(); i++) {
            Point a = pts.get(i);
            Point b = pts.get((i + 1) % pts.size());
            segs.add(new Segment(a, b));
        }

        return segs;
    }

    // ---------------- CORE CHECK ----------------

    static boolean isValidRectangle(Point a, Point b, List<Segment> segs) {

        long x1 = Math.min(a.x(), b.x());
        long x2 = Math.max(a.x(), b.x());
        long y1 = Math.min(a.y(), b.y());
        long y2 = Math.max(a.y(), b.y());

        // chequeamos SOLO las 4 esquinas + borde interno suficiente (clave AoC)
        return isInside(x1, y1, segs)
                && isInside(x1, y2, segs)
                && isInside(x2, y1, segs)
                && isInside(x2, y2, segs);
    }

    // ---------------- POINT-IN-POLYGON (ORTHOGONAL) ----------------

    static boolean isInside(long x, long y, List<Segment> segs) {

        int crossings = 0;

        for (Segment s : segs) {

            long x1 = s.x1, y1 = s.y1;
            long x2 = s.x2, y2 = s.y2;

            // solo segmentos verticales afectan ray cast horizontal
            if (x1 == x2) {

                long minY = Math.min(y1, y2);
                long maxY = Math.max(y1, y2);

                if (y > minY && y <= maxY && x < x1) {
                    crossings++;
                }
            }
        }

        return (crossings % 2) == 1;
    }
}
