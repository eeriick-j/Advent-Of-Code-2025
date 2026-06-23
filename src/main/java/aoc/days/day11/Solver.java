package aoc.days.day11;

import aoc.tasks.TXTFileReader;

import java.util.HashMap;
import java.util.Map;

public class Solver {
    public static void main(String[] args) {
        Graph graph = InputParser.parse(new TXTFileReader().read("inputs/day11.txt"));
        System.out.println("Part 1: " + solvePart1(graph));
        System.out.println("Part 2: " + solvePart2(graph));
    }

    public static long solvePart1(Graph graph) {
        /// Todos los caminos posibles desde 'you' a 'out'
        return dfs(graph, "you", "out", new HashMap<>());
    }

    private static long dfs(Graph graph, String node, String end, Map<String, Long> memo) {
        if (node.equals(end)) return 1;
        if (memo.containsKey(node)) return memo.get(node);

        long total = 0;
        for (String next : graph.neighbors(node)) total += dfs(graph, next, end, memo);

        memo.put(node, total);
        return total;
    }

    public static long solvePart2(Graph graph) {
        /// Todos los caminos posibles desde 'svr' a 'out' que pasen por 'dac' y 'fft'
        return dfs(graph, "svr", false, false, new HashMap<>());
    }

    // Estado del DFS
    record State(String node, boolean hasDac, boolean hasFft) {}

    private static long dfs(Graph graph, String node, boolean hasDac, boolean hasFft, Map<State, Long> memo) {
        // actualizar estado
        if (node.equals("dac")) hasDac = true;
        if (node.equals("fft")) hasFft = true;
        if (node.equals("out")) return (hasDac && hasFft) ? 1 : 0;

        State state = new State(node, hasDac, hasFft);
        if (memo.containsKey(state)) return memo.get(state);

        long total = 0;
        for (String next : graph.neighbors(node)) total += dfs(graph, next, hasDac, hasFft, memo);

        memo.put(state, total);
        return total;
    }
}
