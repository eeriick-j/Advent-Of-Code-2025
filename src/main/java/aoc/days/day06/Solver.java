package aoc.days.day06;

import aoc.io.TXTFileReader;

import java.util.List;

public class Solver {

    public static void main(String[] args) {
        List<String> grid = InputParser.parse(new TXTFileReader().read("inputs/day06.txt"));
        System.out.println("First part: " + solvePart1(grid));
        System.out.println("Second part: " + solvePart2(grid));
    }

    public static long solvePart1(List<String> grid) {
        long sum = 0;
        int rows = grid.size();
        int cols = grid.stream().mapToInt(String::length).max().orElse(0);

        int col = 0;
        while (col < cols) {
            // Saltar columnas completamente vacías
            if (isSeparatorColumn(grid, col)) {
                col++;
                continue;
            }

            // Encontrar límites del bloque
            int start = col;
            int end = col;
            while (end + 1 < cols && !isSeparatorColumn(grid, end + 1)) {
                end++;
            }

            // Buscar operador
            char op = ' ';
            String lastRow = grid.get(rows - 1);

            for (int c = start; c <= end; c++) {
                char ch = c < lastRow.length() ? lastRow.charAt(c) : ' ';
                if (ch == '+' || ch == '*') {
                    op = ch;
                    break;
                }
            }

            if (op != ' ') {

                Long result = null;

                // Leer números por FILAS
                for (int row = 0; row < rows - 1; row++) {
                    String s = grid.get(row);
                    boolean hasDigit = false;
                    long value = 0;

                    for (int c = start; c <= end; c++) {
                        char ch = c < s.length() ? s.charAt(c) : ' ';

                        if (Character.isDigit(ch)) {
                            value = value * 10 + (ch - '0');
                            hasDigit = true;
                        }
                    }

                    if (hasDigit) {
                        if (result == null) result = value;
                        else if (op == '+') result += value;
                        else                result *= value;
                    }
                }
                if (result != null) sum += result;
            }
            col = end + 1;
        }
        return sum;
    }

    private static boolean isSeparatorColumn(List<String> grid, int col) {
        for (String row : grid) {
            char ch = col < row.length() ? row.charAt(col) : ' ';
            if (ch != ' ') return false;
        }
        return true;
    }

    public static long solvePart2(List<String> grid) {
        long sum = 0;
        int rows = grid.size();
        int cols = grid.stream().mapToInt(String::length).max().orElse(0);

        int col = 0;
        while (col < cols) {
            if (isSeparatorColumn(grid, col)) {
                col++;
                continue;
            }

            int start = col;
            int end = col;
            while (end + 1 < cols && !isSeparatorColumn(grid, end + 1)) end++;

            char op = ' ';
            String lastRow = grid.get(rows - 1);

            for (int c = start; c <= end; c++) {
                char ch = c < lastRow.length() ? lastRow.charAt(c) : ' ';
                if (ch == '+' || ch == '*') {
                    op = ch;
                    break;
                }
            }

            if (op != ' ') {
                Long result = null;

                // Leer números por COLUMNAS (derecha -> izquierda)
                for (int c = end; c >= start; c--) {
                    long value = 0;
                    boolean hasDigit = false;
                    for (int row = 0; row < rows - 1; row++) {
                        String s = grid.get(row);
                        char ch = c < s.length() ? s.charAt(c) : ' ';

                        if (Character.isDigit(ch)) {
                            value = value * 10 + (ch - '0');
                            hasDigit = true;
                        }
                    }
                    if (hasDigit) {
                        if (result == null) result = value;
                        else if (op == '+') result += value;
                        else                result *= value;
                    }
                }
                if (result != null) sum += result;
            }
            col = end + 1;
        }
        return sum;
    }
}