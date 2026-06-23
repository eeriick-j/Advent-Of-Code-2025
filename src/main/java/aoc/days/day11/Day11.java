package aoc.days.day11;

import aoc.DayRegistry;
import aoc.core.DaySolver;
import aoc.days.day11.model.Graph;

import java.util.HashMap;
import java.util.Map;

public class Day11 implements DaySolver {
    private final Graph graph;

    public Day11(Graph graph) {
        this.graph = graph;
    }

    public static DaySolver build(String input) {
        return new Day11(new Day11Parser().parse(input));
    }

    static {
        DayRegistry.register(11, Day11::build);
    }

    @Override
    public Long solvePart1() {
        /// Todos los caminos posibles desde 'you' a 'out'
        return dfs(graph, "you", "out", new HashMap<>());
    }

    private long dfs(Graph graph, String node, String end, Map<String, Long> memo) {
        if (node.equals(end)) return 1;
        if (memo.containsKey(node)) return memo.get(node);

        long total = 0;
        for (String next : graph.neighbors(node)) total += dfs(graph, next, end, memo);

        memo.put(node, total);
        return total;
    }

    @Override
    public Long solvePart2() {
        /// Todos los caminos posibles desde 'svr' a 'out' que pasen por 'dac' y 'fft'
        return dfs(graph, "svr", false, false, new HashMap<>());
    }

    // Estado del DFS
    private record State(String node, boolean hasDac, boolean hasFft) {}

    private long dfs(Graph graph, String node, boolean hasDac, boolean hasFft, Map<State, Long> memo) {
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
