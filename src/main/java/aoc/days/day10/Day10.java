package aoc.days.day10;

import aoc.DayRegistry;
import aoc.core.DaySolver;
import aoc.days.day10.algorithm.Search;
import aoc.days.day10.model.Machine;

import java.util.*;

public class Day10 implements DaySolver {
    private final List<Machine> machines;

    public Day10(List<Machine> machines) {
        this.machines = machines;
    }

    public static DaySolver build(String input) {
        return new Day10(new Day10Parser().parse(input));
    }

    static {
        DayRegistry.register(10, Day10::build);
    }

    @Override
    public Long solvePart1() {
        long total = 0;
        for (Machine machine : machines) total += Search.bfs(machine);
        return total;
    }

    @Override
    public Long solvePart2() {
        long total = 0;
        for (Machine machine : machines) total += Search.dfs(machine);
        return total;
    }
}
