package aoc.day08;

import tasks.RawInputReader;

import java.util.*;

public class Solver {

    public static void main(String[] args) {
        List<Box> boxes = InputParser.parse(RawInputReader.read("inputs/day08.txt"));
        System.out.println("Part 1: " + solvePart1(boxes));
    }

    public static long solvePart1(List<Box> boxes) {

        List<Pair> edges = buildEdges(boxes);
        edges.sort(Comparator.comparingDouble(Pair::distance));

        UnionFind dsu = new UnionFind(boxes.size());

        int connections = 0;

        for (Pair p : edges) {
            if (connections == 1000) break;

            if (dsu.union(p.a().id(), p.b().id())) {
                connections++;
            }
            System.out.println("Conexiones reales hechas: " + connections);
        }

        Map<Integer, Integer> compSize = new HashMap<>();

        for (int i = 0; i < boxes.size(); i++) {
            int root = dsu.find(i);
            compSize.merge(root, 1, Integer::sum);
        }

        List<Integer> sizes = new ArrayList<>(compSize.values());
        sizes.sort(Comparator.reverseOrder());

        return (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
    }

    private static List<Pair> buildEdges(List<Box> boxes) {
        List<Pair> edges = new ArrayList<>();

        for (int i = 0; i < boxes.size(); i++) {
            Box a = boxes.get(i);

            for (int j = i + 1; j < boxes.size(); j++) {
                Box b = boxes.get(j);

                long dx = a.x() - b.x();
                long dy = a.y() - b.y();
                long dz = a.z() - b.z();

                double dist = dx * dx + dy * dy + dz * dz;

                edges.add(new Pair(a, b, dist));
            }
        }

        return edges;
    }
}