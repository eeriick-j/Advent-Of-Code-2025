package aoc.days.day09;

import aoc.core.DaySolver;
import aoc.days.day09.model.Point;
import aoc.days.day09.model.Segment;

import java.util.*;

public class Day09Solver implements DaySolver {
    private final List<Point> points;

    public Day09Solver(List<Point> points) {
        this.points = points;
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

    private List<Segment> buildSegments(List<Point> pts) {
        List<Segment> segments = new ArrayList<>();

        for (int i = 0; i < pts.size(); i++) {
            Point a = pts.get(i);
            Point b = pts.get((i + 1) % pts.size());
            segments.add(new Segment(a, b));
        }

        return segments;
    }

    private boolean isValidRectangle(Point a, Point b, List<Segment> segs) {
        long x1 = Math.min(a.x(), b.x());
        long x2 = Math.max(a.x(), b.x());
        long y1 = Math.min(a.y(), b.y());
        long y2 = Math.max(a.y(), b.y());

        // Las 4 esquinas deben estar dentro
        if (!(isInside(x1,y1,segs)
                && isInside(x1,y2,segs)
                && isInside(x2,y1,segs)
                && isInside(x2,y2,segs)))
            return false;

        // Ningún segmento de la frontera puede atravesar el interior
        for (Segment s : segs) {
            long sx1 = s.x1(), sy1 = s.y1();
            long sx2 = s.x2(), sy2 = s.y2();

            if (sx1 == sx2) { // vertical
                long x = sx1;
                long ymin = Math.min(sy1, sy2);
                long ymax = Math.max(sy1, sy2);
                if (x1 < x && x < x2 && Math.max(y1, ymin) < Math.min(y2, ymax)) return false;
            }
            else { // horizontal
                long y = sy1;
                long xmin = Math.min(sx1, sx2);
                long xmax = Math.max(sx1, sx2);
                if (y1 < y && y < y2 && Math.max(x1, xmin) < Math.min(x2, xmax)) return false;
            }
        }
        return true;
    }

    private boolean isInside(long x, long y, List<Segment> segs) {
        for (Segment s : segs) if (onSegment(x,y,s)) return true;
        int crossings = 0;
        for (Segment s : segs) {
            if (s.x1() == s.x2()) {
                long minY = Math.min(s.y1(), s.y2());
                long maxY = Math.max(s.y1(), s.y2());
                if (y > minY && y <= maxY && x < s.x1()) crossings++;
            }
        }
        return (crossings & 1) == 1;
    }

    private boolean onSegment(long x, long y, Segment s) {
        long x1 = s.x1();
        long y1 = s.y1();
        long x2 = s.x2();
        long y2 = s.y2();

        // Segmento vertical
        if (x1 == x2) return x == x1 && y >= Math.min(y1, y2) && y <= Math.max(y1, y2);

        // Segmento horizontal
        return y == y1 && x >= Math.min(x1, x2) && x <= Math.max(x1, x2);
    }
}
