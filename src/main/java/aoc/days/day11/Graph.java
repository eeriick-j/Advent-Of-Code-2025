package aoc.days.day11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, List<String>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addEdge(String from, String to) {
        adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
    }

    public List<String> neighbors(String node) {
        return adjacencyList.getOrDefault(node, List.of());
    }

    public Map<String, List<String>> getAdjacencyList() {
        return adjacencyList;
    }
}