package aoc.day10;

import tasks.RawInputReader;

import java.util.*;

public class Solver {

    public static void main(String[] args) {
        List<Machine> machines = InputParser.parse(RawInputReader.read("inputs/day10.txt"));
        System.out.println("Part 1: " + solve(machines));
    }

    public static int solve(List<Machine> machines) {
        int total = 0;
        for (Machine m : machines) {
            total += minPresses(m.target(), m.buttons());
        }
        return total;
    }

    // Meet in the middle
    static int minPresses(long target, long[] buttons) {

        int n = buttons.length;
        int mid = n / 2;

        long[] A = Arrays.copyOfRange(buttons, 0, mid);
        long[] B = Arrays.copyOfRange(buttons, mid, n);

        Map<Long, Integer> left = new HashMap<>();

        for (int mask = 0; mask < (1 << A.length); mask++) {
            long xor = 0;
            int cnt = 0;

            for (int i = 0; i < A.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    xor ^= A[i];
                    cnt++;
                }
            }

            left.merge(xor, cnt, Math::min);
        }

        int best = Integer.MAX_VALUE;

        for (int mask = 0; mask < (1 << B.length); mask++) {
            long xor = 0;
            int cnt = 0;

            for (int i = 0; i < B.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    xor ^= B[i];
                    cnt++;
                }
            }

            long need = target ^ xor;

            if (left.containsKey(need)) {
                best = Math.min(best, cnt + left.get(need));
            }
        }

        return best;
    }
}