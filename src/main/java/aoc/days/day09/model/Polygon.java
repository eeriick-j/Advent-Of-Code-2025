package aoc.days.day09.model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private final List<Point> points;
    private final List<Segment> segments;

    public Polygon(List<Point> points, List<Segment> segments) {
        this.points = points;
        this.segments = segments;
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

    public boolean containsRectangle(Point a, Point b) {
        long x1 = Math.min(a.x(), b.x());
        long x2 = Math.max(a.x(), b.x());
        long y1 = Math.min(a.y(), b.y());
        long y2 = Math.max(a.y(), b.y());

        // Las esquinas deben estar dentro
        if (!(isInside(x1,y1) && isInside(x1,y2) &&
                isInside(x2,y1) && isInside(x2,y2))) {
            return false;
        }

        // Ningún segmento debe cruzar el interior del rectángulo
        for (Segment s : segments) {
            long sx1 = s.x1(), sy1 = s.y1();
            long sx2 = s.x2(), sy2 = s.y2();

            if (sx1 == sx2) { // vertical
                long x = sx1;
                long ymin = Math.min(sy1, sy2);
                long ymax = Math.max(sy1, sy2);

                if (x1 < x && x < x2 &&
                        Math.max(y1, ymin) < Math.min(y2, ymax)) {
                    return false;
                }
            } else { // horizontal
                long y = sy1;
                long xmin = Math.min(sx1, sx2);
                long xmax = Math.max(sx1, sx2);

                if (y1 < y && y < y2 &&
                        Math.max(x1, xmin) < Math.min(x2, xmax)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isInside(long x, long y) {
        // Si está en el borde, está dentro
        for (Segment s : segments) if (onSegment(x, y, s)) return true;

        // 2. Ray casting
        int crossings = 0;
        for (Segment s : segments) {
            if (s.x1() == s.x2()) { // vertical
                long minY = Math.min(s.y1(), s.y2());
                long maxY = Math.max(s.y1(), s.y2());

                if (y > minY && y <= maxY && x < s.x1()) crossings++;
            }
        }
        return (crossings & 1) == 1;
    }

    private boolean onSegment(long x, long y, Segment s) {
        if (s.x1() == s.x2()) {
            return x == s.x1()
                    && y >= Math.min(s.y1(), s.y2())
                    && y <= Math.max(s.y1(), s.y2());
        }
        return y == s.y1()
                && x >= Math.min(s.x1(), s.x2())
                && x <= Math.max(s.x1(), s.x2());
    }
}
