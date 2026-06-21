package aoc.day03;

import tasks.RawInputReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Bank> banks = InputParser.parse(RawInputReader.read("inputs/day03.txt"));
        System.out.println("Part 1: " + solvePart1(banks));
        System.out.println("Part 2: " + solvePart2(banks));
    }

    public static long solvePart1(List<Bank> banks) {
        /// Sumas de las mayores parejas de 2 dígitos (concatenados) para cada banco
        long sumVoltages = 0;
        for (Bank bank : banks) {
            List<Integer> best2 = maxSubsequence(bank.voltages(), 2);
            sumVoltages += toNumber(best2);
        }
        return sumVoltages;
    }


    public static long solvePart2(List<Bank> banks) {
        long sumVoltages = 0;
        for (Bank bank : banks) {
            List<Integer> best12 = maxSubsequence(bank.voltages(), 12);
            sumVoltages += toNumber(best12);
        }
        return sumVoltages;
    }

    private static List<Integer> maxSubsequence(List<Integer> nums, int k) {
        Deque<Integer> stack = new ArrayDeque<>();
        int toRemove = nums.size() - k;

        for (int num : nums) {
            while (toRemove > 0 && !stack.isEmpty() && stack.peekLast() < num) {
                stack.pollLast();
                toRemove--;
            }
            stack.addLast(num);
        }

        while (stack.size() > k) stack.pollLast();
        return new ArrayList<>(stack);
    }

    private static long toNumber(List<Integer> digits) {
        long result = 0;
        for (int d : digits) result = result * 10 + d;
        return result;
    }

}
