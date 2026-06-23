package aoc;

import aoc.core.DaySolver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class DayRegistry {
    private static final Map<Integer, Supplier<DaySolver>> DAYS = new HashMap<>();

    public static void register(int day, Supplier<DaySolver> supplier) {
        DAYS.put(day, supplier);
    }

    public static DaySolver get(int day) {
        Supplier<DaySolver> supplier = DAYS.get(day);
        if (supplier == null) throw new IllegalArgumentException("Day not registered: " + day);
        return supplier.get();
    }
}
