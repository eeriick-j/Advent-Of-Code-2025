package aoc.day12.model;

import java.util.List;

public record Piece(int id, List<Cell> cells) {
    public int area(){
        return cells.size();
    }
}
