package aoc.days.day12.model;

public record Region(int width, int height, int[] requirements) {
    public int area(){
        return width * height;
    }
}
