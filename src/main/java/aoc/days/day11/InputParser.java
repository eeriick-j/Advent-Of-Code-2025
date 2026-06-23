package aoc.days.day11;

import aoc.days.day11.model.Graph;

public class InputParser {
    public static Graph parse(String rawInput) {
        Graph graph = new Graph();
        String[] lines = rawInput.split("\\n");

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(":");
            String from = parts[0].trim();
            String[] tos = parts[1].trim().split("\\s+");
            for (String to : tos) graph.addEdge(from, to);
        }

        return graph;
    }
}