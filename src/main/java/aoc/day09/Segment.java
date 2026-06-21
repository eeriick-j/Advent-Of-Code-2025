package aoc.day09;

public class Segment {
    long x1, y1, x2, y2;
    public Segment(Point a, Point b) {
        x1 = a.x(); y1 = a.y();
        x2 = b.x(); y2 = b.y();
    }
}

