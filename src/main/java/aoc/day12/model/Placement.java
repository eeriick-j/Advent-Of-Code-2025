package aoc.day12.model;

import java.util.List;

public record Placement(Piece piece, int offsetX, int offsetY, List<Cell> cells) {}