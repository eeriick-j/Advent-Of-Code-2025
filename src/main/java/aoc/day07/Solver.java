package aoc.day07;

import tasks.RawInputReader;

import java.util.HashSet;
import java.util.Set;

public class Solver {
    public static void main(String[] args) {
        char[][] grid = InputParser.parse(RawInputReader.read("inputs/day07.txt"));
        System.out.println("First part: " + solvePart1(grid));
    }

    public static int solvePart1(char[][] grid) {
        final int start = new String(grid[0]).indexOf('S');
        Set<Integer> current = new HashSet<>();
        current.add(start);

        int splits = 0;
        for (int row = 1; row < grid.length; row++) {
            Set<Integer> next = new HashSet<>();
            for (int pos : current) {
                if (grid[row][pos] == '^') {
                    if (pos - 1 >= 0)               next.add(pos - 1);
                    if (pos + 1 < grid[row].length) next.add(pos + 1);
                    splits++;
                }
                else {
                    next.add(pos);
                }
            }
            current = next;
        }
        return splits;
    }
}
