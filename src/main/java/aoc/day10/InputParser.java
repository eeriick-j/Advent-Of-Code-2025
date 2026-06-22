package aoc.day10;

import java.util.*;
import java.util.regex.*;

public class InputParser {
    private static final Pattern BRACKETS = Pattern.compile("\\[(.*?)\\]");
    private static final Pattern PARENTHESIS = Pattern.compile("\\(([^)]*)\\)");

    public static List<Machine> parse(String input) {
        List<Machine> machines = new ArrayList<>();
        String[] lines = input.split("\\R");
        for (String line : lines) if (!line.isBlank()) machines.add(toMachine(line));
        return machines;
    }

    private static Machine toMachine(String line) {
        Matcher mPattern = BRACKETS.matcher(line);
        if (!mPattern.find()) throw new IllegalStateException("No bracket pattern found: " + line);

        String pattern = mPattern.group(1);
        int n = pattern.length();

        // Inicio de la lectura por la derecha -> bit 0 (LSB)
        int target = 0;
        for (int i = 0; i < n; i++) if (pattern.charAt(i) == '#') target |= (1 << (n - 1 - i));

        Matcher mButtons = PARENTHESIS.matcher(line);
        List<Integer> buttons = new ArrayList<>();

        while (mButtons.find()) {
            String inside = mButtons.group(1).trim();
            if (inside.isEmpty()) continue;
            int mask = 0;
            for (String p : inside.split(",")) {
                if (!p.isBlank()) {
                    int pos = Integer.parseInt(p.trim());
                    mask |= (1 << pos);
                }
            }
            buttons.add(mask);
        }
        return new Machine(target, buttons.stream().mapToInt(i -> i).toArray());
    }
}