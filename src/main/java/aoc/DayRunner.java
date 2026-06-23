package aoc;

import aoc.core.DaySolver;
import aoc.io.FileReader;
import aoc.io.TXTFileReader;

public class DayRunner {
    private static final FileReader reader = new TXTFileReader();

    public static void run(int day) {
        forceLoadOnJVM();
        String input = reader.read("inputs/day%02d.txt".formatted(day));

        DaySolver solver = DayRegistry.create(day, input);
        System.out.println("Part 1: " + solver.solvePart1());
        System.out.println("Part 2: " + solver.solvePart2());
    }

    public static void forceLoadOnJVM() {
        // To ensure that static blocks works
        final int NUM_DAYS = 12;
        try {
            for (int i= 1; i<=NUM_DAYS; i++) Class.forName(String.format("aoc.days.day%02d.Day%02d", i, i));
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
