package aoc.day10;

import tasks.RawInputReader;

import java.util.Arrays;
import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Machine> machines = InputParser.parse(RawInputReader.read("inputs/day10.txt"));
        for (Machine machine : machines) {
            System.out.println(Arrays.toString(machine.voltages()));
        }
        System.out.println(solvePart1(machines));
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


}