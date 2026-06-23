package aoc.days.day05;

import aoc.days.day02.IDRange;

import java.util.List;

public record Pair(List<IDRange> idRanges, List<Long> ids) {}
