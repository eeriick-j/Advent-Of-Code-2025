package aoc.day06;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        Pair pair = InputParser.parse(RawInputReader.read("inputs/day06.txt"));
        System.out.println("First part: " + solvePart1(pair));
    }

    public static long solvePart1(Pair pair) {
        ///  Suma de operaciones de cada columna
        List<List<Integer>> values = pair.values();
        List<Character> operators = pair.operators();
        long sum = 0;

        for (int col = 0; col < values.getFirst().size(); col++) {
            long result = values.getFirst().get(col);
            for (int row = 1; row < values.size(); row++) {
                switch (operators.get(col)) {
                    case '+': result += values.get(row).get(col); break;
                    case '-': result -= values.get(row).get(col); break;
                    case '*': result *= values.get(row).get(col); break;
                    case '/': result /= values.get(row).get(col); break;
                }
            }
            sum += result;
        }
        return sum;
    }

    public static long solvePart2(Pair pair) {
        ///  Suma de operaciones de cada columna de dígitos para columna
        List<List<Integer>> values = pair.values();
        List<Character> operators = pair.operators();
        long sum = 0;

        for (int col=0; col < values.getFirst().size(); col++) {
            buildNumbersFromColumn(values, col);
        }
        return sum;
    }

    private static void buildNumbersFromColumn(List<List<Integer>> values, int col) {

    }
}
