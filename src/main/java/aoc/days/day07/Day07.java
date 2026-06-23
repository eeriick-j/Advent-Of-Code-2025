package aoc.days.day07;

import aoc.core.DaySolver;

import java.util.HashSet;
import java.util.Set;

public class Day07 implements DaySolver {
    private final char[][] grid;

    public Day07(char[][] grid) {
        this.grid = grid;
    }

    @Override
    public Integer solvePart1() {
        ///  Número de veces que un rayo inicial se divide contra obstáculos
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
                else next.add(pos);
            }
            current = next;
        }
        return splits;
    }

    @Override
    public Long solvePart2() {
        ///  Número de paths distintos posibles (árbol)
        final int start = new String(grid[0]).indexOf('S');

        // Hay 1 forma de estar en la posición inicial, las demás posiciones tiene 0 formas
        long[] current = new long[grid[0].length];
        current[start] = 1;

        for (int row = 1; row < grid.length; row++) {
            // Caminos de la siguiente fila
            long[] next = new long[grid[row].length];

            for (int col = 0; col < current.length; col++) {
                long ways = current[col];
                if (ways == 0) continue;

                if (grid[row][col] == '^') {
                    if (col - 1 >= 0)           next[col - 1] += ways;
                    if (col + 1 < next.length)  next[col + 1] += ways;
                }
                else next[col] += ways;
            }
            current = next;
        }
        // Suma de formas de llegar a cada fila v
        long result = 0;
        for (long v : current) result += v;
        return result;
    }
}