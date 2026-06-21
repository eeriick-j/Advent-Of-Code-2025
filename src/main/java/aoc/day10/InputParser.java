package aoc.day10;

import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static List<Machine> parse(String rawInput) {
        return rawInput.lines()
                .map(InputParser::parseLine)
                .toList();
    }

    private static Machine parseLine(String line) {
        String targetStr = line.substring(0, line.indexOf(']') + 1);
        return new Machine(targetToBits(targetStr), parseButtons(line));
    }

    private static long targetToBits(String string) {
        long target = 0;

        for (char c : string.toCharArray()) {
            if (c == '[' || c == ']') continue;
            target <<= 1;
            if (c == '#') target |= 1;
        }

        return target;
    }

    public static long[] parseButtons(String line) {
        List<Long> buttons = new ArrayList<>();

        int i = 0;
        while (i < line.length()) {

            while (i < line.length() && line.charAt(i) != '(') i++;
            if (i >= line.length()) break;
            i++;        // skipear '('

            long mask = 0;
            while (i < line.length() && line.charAt(i) != ')') {
                if (Character.isDigit(line.charAt(i))) {
                    int num = 0;

                    while (i < line.length() && Character.isDigit(line.charAt(i))) {
                        num = num * 10 + (line.charAt(i) - '0');
                        i++;
                    }

                    mask |= (1L << num);
                }
                else i++;
            }
            buttons.add(mask);
            i++;                // skipear ')'
        }

        long[] res = new long[buttons.size()];
        for (int j = 0; j < buttons.size(); j++) res[j] = buttons.get(j);
        return res;
    }
}