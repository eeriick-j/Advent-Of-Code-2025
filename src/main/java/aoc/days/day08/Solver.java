package aoc.days.day08;

import aoc.io.TXTFileReader;

import java.util.*;

public class Solver {
    public static void main(String[] args) {
        List<Box> boxes = InputParser.parse(new TXTFileReader().read("inputs/day08.txt"));
        System.out.println("Part 1: " + solvePart1(boxes));
        System.out.println("Part 2: " + solvePart2(boxes));
    }

    private static long solvePart1(List<Box> boxes) {
        int n = boxes.size();

        // 1. Crear todas las parejas
        List<Pair> pairs = generateAllPairsFrom(boxes);

        // 2. Ordenar por distancia
        pairs.sort(Comparator.comparingDouble(Pair::distance));

        // 3. DSU
        DSU dsu = new DSU(n);

        // Box -> index
        Map<Box, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) index.put(boxes.get(i), i);

        // 4. Procesar 1000 conexiones
        int limit = Math.min(1000, pairs.size());

        for (int i = 0; i < limit; i++) {
            Pair p = pairs.get(i);
            int a = index.get(p.a());
            int b = index.get(p.b());
            if (dsu.find(a) != dsu.find(b)) dsu.union(a, b);
        }

        // 5. Contar tamaños de componentes
        Map<Integer, Integer> compSize = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            compSize.put(root, compSize.getOrDefault(root, 0) + 1);
        }

        // 6. Top 3
        List<Integer> sizes = new ArrayList<>(compSize.values());
        sizes.sort(Collections.reverseOrder());

        return (long) sizes.get(0) * sizes.get(1) * sizes.get(2);
    }

    private static List<Pair> generateAllPairsFrom(List<Box> boxes) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            for (int j = i + 1; j < boxes.size(); j++) {
                Box a = boxes.get(i);
                Box b = boxes.get(j);
                pairs.add(new Pair(a, b, distance(a, b)));
            }
        }
        return pairs;
    }

    private static double distance(Box a, Box b) {
        long dx = a.x() - b.x();
        long dy = a.y() - b.y();
        long dz = a.z() - b.z();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    private static long solvePart2(List<Box> boxes) {
        int n = boxes.size();

        // 1. Crear todas las parejas
        List<Pair> pairs = generateAllPairsFrom(boxes);

        // 2. Ordenar por distancia
        pairs.sort(Comparator.comparingDouble(Pair::distance));

        // 3. DSU
        DSU dsu = new DSU(n);

        // Box -> index
        Map<Box, Integer> index = new HashMap<>();
        for (int i = 0; i < n; i++) index.put(boxes.get(i), i);

        int components = n;
        Box lastA = null;
        Box lastB = null;

        // 4. Kruskal completo
        for (Pair p : pairs) {
            int a = index.get(p.a());
            int b = index.get(p.b());

            if (dsu.find(a) != dsu.find(b)) {
                dsu.union(a, b);
                components--;

                lastA = p.a();
                lastB = p.b();

                if (components == 1) break;
            }
        }
        // 5. Resultado final: producto de X de la última conexión efectiva
        return (long) lastA.x() * lastB.x();
    }
}
