package aoc.day09.model;

public record Segment(long x1, long y1, long x2, long y2) {
    public Segment(Point a, Point b) {
        this(a.x(), a.y(), b.x(), b.y());
    }
}

