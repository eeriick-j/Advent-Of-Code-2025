package aoc.days.day11.algorithm;

import aoc.days.day11.model.Graph;

import java.util.Map;

public class Search {
    public static long dfs(Graph graph, String node, String end, Map<String, Long> memo) {
        if (node.equals(end)) return 1;
        if (memo.containsKey(node)) return memo.get(node);

        long total = 0;
        for (String next : graph.neighbors(node)) total += dfs(graph, next, end, memo);

        memo.put(node, total);
        return total;
    }

    // Estado del DFS
    private record State(String node, boolean hasDac, boolean hasFft) {}

    public static long dfs(Graph graph, String node, boolean hasDac, boolean hasFft, Map<State, Long> memo) {
        // Actualizar estado
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
