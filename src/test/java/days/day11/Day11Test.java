package days.day11;

import aoc.core.DaySolver;
import aoc.days.day11.Day11;
import aoc.days.day11.Day11Parser;
import aoc.days.day11.algorithm.Search;
import aoc.days.day11.model.Graph;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Day11Test {
    private static final FileReader reader = new TXTFileReader();
    private static final String INPUT_PATH = "inputs/day11.txt";

    private String input() {
        return reader.read(INPUT_PATH);
    }

    @Test
    public void solvePart1Correct() {
        DaySolver solver = Day11.build(input());
        assertEquals(688L, solver.solvePart1());
    }

    @Test
    public void solvePart2Correct() {
        DaySolver solver = Day11.build(input());
        assertEquals(293263494406608L, solver.solvePart2());
    }

    @Test
    public void parseBasicInputBuildsGraph() {
        String input = "A: B C\n" +
                       "B: D\n";

        Day11Parser parser = new Day11Parser();
        Graph graph = parser.parse(input);

        assertEquals(2, graph.neighbors("A").size());
        assertTrue(graph.neighbors("A").contains("B"));
        assertTrue(graph.neighbors("A").contains("C"));
    }

    @Test
    public void parseIgnoresEmptyLines() {
        String input = "A: B\n\n\nB: C\n";
        Day11Parser parser = new Day11Parser();
        Graph graph = parser.parse(input);

        assertEquals(1, graph.neighbors("A").size());
        assertEquals(1, graph.neighbors("B").size());
    }

    @Test
    public void addEdgeCreatesAdjacency() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        List<String> neighbors = graph.neighbors("A");

        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
    }

    @Test
    public void neighbors_unknownNode_returnsEmptyList() {
        Graph graph = new Graph();
        List<String> neighbors = graph.neighbors("x");

        assertNotNull(neighbors);
        assertTrue(neighbors.isEmpty());
    }

    @Test
    public void dfsSimpleLinearPathCountsOne() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");

        long result = Search.dfs(graph, "A", "C", new HashMap<>());

        assertEquals(1L, result);
    }

    @Test
    public void dfsBranchingGraphCountsAllPaths() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "D");

        long result = Search.dfs(graph, "A", "D", new HashMap<>());
        assertEquals(2L, result);
    }

    @Test
    public void dfsNoPathReturnsZero() {
        Graph graph = new Graph();
        graph.addEdge("A", "B");

        long result = Search.dfs(graph, "A", "Z", new HashMap<>());
        assertEquals(0L, result);
    }

    @Test
    public void dfsRequiresDacAndFftToReachOut() {
        Graph graph = new Graph();
        graph.addEdge("svr", "dac");
        graph.addEdge("dac", "mid");
        graph.addEdge("mid", "fft");
        graph.addEdge("fft", "out");

        long result = Search.dfs(graph, "svr", false, false, new HashMap<>());
        assertEquals(1L, result);
    }

    @Test
    public void dfsMissingFftReturnsZero() {
        Graph graph = new Graph();
        graph.addEdge("svr", "dac");
        graph.addEdge("dac", "out");

        long result = Search.dfs(graph, "svr", false, false, new HashMap<>());
        assertEquals(0L, result);
    }

    @Test
    public void dfs_usesGraphNeighbors_correctly() {
        Graph graph = mock(Graph.class);
        when(graph.neighbors("A")).thenReturn(List.of("B", "C"));
        when(graph.neighbors("B")).thenReturn(List.of("D"));
        when(graph.neighbors("C")).thenReturn(List.of("D"));
        when(graph.neighbors("D")).thenReturn(List.of());

        assertEquals(2L, Search.dfs(graph, "A", "D", new HashMap<>()));
        verify(graph, atLeastOnce()).neighbors("A");
    }
}
