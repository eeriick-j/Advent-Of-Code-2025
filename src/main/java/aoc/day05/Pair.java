package aoc.day05;

import aoc.day02.IDRange;

import java.util.List;

public record Pair(List<IDRange> idRanges, List<Long> ids) {}
