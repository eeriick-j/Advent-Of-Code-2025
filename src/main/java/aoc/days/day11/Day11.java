package aoc.days.day11;

import aoc.DayRegistry;
import aoc.core.DaySolver;
import aoc.days.day11.algorithm.Search;
import aoc.days.day11.model.Graph;

import java.util.HashMap;

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
        return Search.dfs(graph, "you", "out", new HashMap<>());
    }

    @Override
    public Long solvePart2() {
        /// Todos los caminos posibles desde 'svr' a 'out' que pasen por 'dac' y 'fft'
        return Search.dfs(graph, "svr", false, false, new HashMap<>());
    }
}
