package aoc.day10;

import tasks.RawInputReader;

import java.util.*;

public class Solver {
    public static void main(String[] args) {
        List<Machine> machines = InputParser.parse(RawInputReader.read("inputs/day10.txt"));
        System.out.println(solvePart1(machines));
        System.out.println(solvePart2(machines));
    }

    public static long solvePart1(List<Machine> machines) {
        long total = 0;
        for (Machine m : machines) total += solveMachine(m);
        return total;
    }

    private static int solveMachine(Machine m) {
        int target = m.target();
        int[] buttons = m.buttons();
        return bfs(target, buttons);
    }

    private static int bfs(int target, int[] buttons) {
        int maxState = 1 << 20;         // ajusta según nº de luces reales
        boolean[] visited = new boolean[maxState];
        int[] dist = new int[maxState];
        int[] queue = new int[maxState];

        int head = 0, tail = 0;
        queue[0] = 0;
        visited[0] = true;

        while (head <= tail) {
            int state = queue[head++];
            int d = dist[state];
            if (state == target) return d;

            for (int b : buttons) {
                int next = state ^ b;
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = d + 1;
                    queue[++tail] = next;
                }
            }
        }
        return -1;
    }

    public static long solvePart2(List<Machine> machines) {
        long total = 0;
        for (Machine m : machines) total += solveMachinePart2(m);
        return total;
    }

    private static int solveMachinePart2(Machine m) {
        int[][] buttons = expandButtons(m.buttons());
        int[] target = m.voltages();
        return bfsStateSpace(target, buttons);
    }

    // BFS en espacio de estados completo
    private static int bfsStateSpace(int[] target, int[][] buttons) {
        Queue<int[]> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        int[] start = new int[target.length];

        q.add(start);
        visited.add(encode(start));
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] state = q.poll();
                if (Arrays.equals(state, target)) return steps;
                for (int[] b : buttons) {
                    int[] next = state.clone();
                    apply(next, b);
                    String key = encode(next);
                    if (visited.add(key)) q.add(next);
                }
            }
            steps++;
        }
        return -1;
    }

    private static void apply(int[] state, int[] mask) {
        for (int i : mask) state[i]++;
    }

    private static String encode(int[] state) {
        return Arrays.toString(state);
    }

    // bitmask -> índices
    private static int[][] expandButtons(int[] buttons) {
        int[][] res = new int[buttons.length][];
        for (int i = 0; i < buttons.length; i++) {
            int mask = buttons[i];
            List<Integer> idx = new ArrayList<>();
            int bit = 0;
            while (mask != 0) {
                if ((mask & 1) == 1) idx.add(bit);
                mask >>= 1;
                bit++;
            }
            res[i] = idx.stream().mapToInt(x -> x).toArray();
        }
        return res;
    }
}