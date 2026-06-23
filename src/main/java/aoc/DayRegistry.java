package aoc;

import aoc.core.DaySolver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class DayRegistry {
    private static final Map<Integer, Function<String, DaySolver>> DAYS = new HashMap<>();

    public static void register(int day, Function<String, DaySolver> factory) {
        DAYS.put(day, factory);
    }

    public static DaySolver create(int day, String input) {
        return DAYS.get(day).apply(input);
    }
}