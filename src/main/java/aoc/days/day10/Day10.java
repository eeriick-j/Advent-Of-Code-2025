package aoc.days.day10;

import aoc.core.DaySolver;
import aoc.days.day10.model.Machine;

import java.util.*;

public class Day10 implements DaySolver {
    private final List<Machine> machines;

    public Day10(List<Machine> machines) {
        this.machines = machines;
    }

    @Override
    public Long solvePart1() {
        long total = 0;
        for (Machine m : machines) total += solveMachine(m);
        return total;
    }

    private int solveMachine(Machine m) {
        int target = m.target();
        int[] buttons = m.buttons();
        return bfs(target, buttons);
    }

    private int bfs(int target, int[] buttons) {
        int maxState = 1 << 20;
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

    @Override
    public Long solvePart2() {
        long total = 0;
        for (Machine m : machines) total += solveMachinePart2(m);
        return total;
    }

    private long solveMachinePart2(Machine m) {
        int[] buttons = m.buttons();
        int[] target = m.voltages();

        Map<String, Long> memo = new HashMap<>();
        return dfs(buttons, target, memo);
    }

    private long dfs(int[] buttons, int[] cur, Map<String, Long> memo) {
        String key = Arrays.toString(cur);
        if (memo.containsKey(key)) return memo.get(key);

        boolean allZero = true;
        for (int v : cur) {
            if (v != 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) return 0;

        int n = cur.length;
        int B = buttons.length;
        long best = Long.MAX_VALUE;

        // todos los subconjuntos de botones
        for (int mask = 0; mask < (1 << B); mask++) {
            int[] applied = new int[n];
            int used = Integer.bitCount(mask);

            // aplicar subset
            for (int i = 0; i < B; i++) {
                if ((mask & (1 << i)) != 0) {
                    int b = buttons[i];
                    for (int j = 0; j < n; j++) if (((b >> j) & 1) == 1) applied[j]++;
                }
            }

            boolean valid = true;

            // comprobar paridad y no negativos
            for (int j = 0; j < n; j++) {
                int diff = cur[j] - applied[j];
                if (diff < 0 || (diff & 1) != 0) {
                    valid = false;
                    break;
                }
            }
            if (!valid) continue;

            int[] reduced = new int[n];
            for (int j = 0; j < n; j++) reduced[j] = (cur[j] - applied[j]) / 2;

            long sub = dfs(buttons, reduced, memo);
            if (sub != Long.MAX_VALUE) best = Math.min(best, used + 2L * sub);
        }
        memo.put(key, best);
        return best;
    }
}
