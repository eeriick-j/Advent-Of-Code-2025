package aoc.days.day03;

import aoc.DayRegistry;
import aoc.core.DaySolver;
import aoc.days.day03.model.Bank;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Day03 implements DaySolver {
    private final List<Bank> banks;

    public Day03(List<Bank> banks) {
        this.banks = banks;
    }

    public static DaySolver build(String input) {
        return new Day03(new Day03Parser().parse(input));
    }

    static {
        DayRegistry.register(3, Day03::build);
    }

    @Override
    public Long solvePart1() {
        /// Sumas de las mayores parejas de 2 dígitos (concatenados) para cada banco
        long sumVoltages = 0;
        for (Bank bank : banks) {
            List<Integer> best2 = maxSubsequence(bank.voltages(), 2);
            sumVoltages += toNumber(best2);
        }
        return sumVoltages;
    }

    @Override
    public Long solvePart2() {
        long sumVoltages = 0;
        for (Bank bank : banks) {
            List<Integer> best12 = maxSubsequence(bank.voltages(), 12);
            sumVoltages += toNumber(best12);
        }
        return sumVoltages;
    }

    private List<Integer> maxSubsequence(List<Integer> nums, int k) {
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

    private long toNumber(List<Integer> digits) {
        long result = 0;
        for (int d : digits) result = result * 10 + d;
        return result;
    }
}
