package aoc.day08;

import java.util.*;

public class Graph{
    public static Map<Integer, List<Integer>> buildEmptyGraph(List<Box> boxesPositions){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int i = 0; i < boxesPositions.size(); i++) graph.put(i, new ArrayList<>());
        return graph;
    }

    public static boolean existsPath(Map<Integer, List<Integer>> graph, int IDa, int IDb) {
        return dfs(graph, IDa, IDb, new HashSet<>());
    }

    private static boolean dfs(Map<Integer, List<Integer>> graph, int current, int target, Set<Integer> seen) {
        if(current==target) return true;
        seen.add(current);
        for(Integer next: graph.get(current)){
            if(!seen.contains(next))
                if(dfs(graph, next, target, seen))
                    return true;
        }
        return false;
    }

    private static void dfsFillCircuit(Map<Integer, List<Integer>> graph, int current, Set<Integer> visitedNodes,
                                       Set<Integer> circuit){
        visitedNodes.add(current);
        circuit.add(current);
        for(int neighbor : graph.get(current)){
            if(!visitedNodes.contains(neighbor)){
                dfsFillCircuit(graph, neighbor, visitedNodes, circuit);
            }
        }
    }

    public static List<Set<Integer>> getCircuits(Map<Integer, List<Integer>> graph) {
        Set<Integer> visitedNodes = new HashSet<>();
        List<Set<Integer>> circuits = new ArrayList<>();

        for(int node : graph.keySet()){
            if(!visitedNodes.contains(node)){
                Set<Integer> circuit = new HashSet<>();
                dfsFillCircuit(graph, node, visitedNodes, circuit);
                circuits.add(circuit);
            }
        }
        return circuits;
    }
}
