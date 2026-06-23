package aoc.days.day09;

import aoc.DayRegistry;
import aoc.core.DaySolver;
import aoc.days.day09.model.Point;
import aoc.days.day09.model.Polygon;
import aoc.days.day09.model.Segment;

import java.util.*;

public class Day09 implements DaySolver {
    private final List<Point> points;

    public Day09(List<Point> points) {
        this.points = points;
    }

    public static DaySolver build(String input) {
        return new Day09(new Day09Parser().parse(input));
    }

    static {
        DayRegistry.register(9, Day09::build);
    }

    @Override
    public Long solvePart1() {
        ///  Área máxima entre el rectángulo que forman dos puntos cualesquiera
        long maxArea = 0;
        for(int i=0; i<points.size(); i++) {
            for(int j=i+1; j<points.size(); j++) {
                maxArea = Math.max(maxArea, area(points.get(i), points.get(j)));
            }
        }
        return maxArea;
    }

    private long area(Point a, Point b) {
        long dx = Math.abs(a.x() - b.x() + 1);
        long dy = Math.abs(a.y() - b.y() + 1);
        return dx * dy;
    }

    @Override
    public Long solvePart2() {
        ///  Área máxima entre el rectángulo que forman dos puntos cualesquiera q
        ///  que estén dentro del polígono que forman todos los puntos
        Polygon polygon = new Polygon(points, buildSegments(points));

        long maxArea = 0;
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point a = points.get(i);
                Point b = points.get(j);

                if (polygon.containsRectangle(a, b)) {
                    long dx = Math.abs(a.x() - b.x()) + 1;
                    long dy = Math.abs(a.y() - b.y()) + 1;
                    maxArea = Math.max(maxArea, dx * dy);
                }
            }
        }
        return maxArea;
    }

    private List<Segment> buildSegments(List<Point> pts) {
        List<Segment> segments = new ArrayList<>();

        for (int i = 0; i < pts.size(); i++) {
            Point a = pts.get(i);
            Point b = pts.get((i + 1) % pts.size());
            segments.add(new Segment(a, b));
        }

        return segments;
    }
}
