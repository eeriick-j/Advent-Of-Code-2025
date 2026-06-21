package aoc.day08;

import java.util.List;

public class InputParser {
    public static List<Box> parse(String rawInput){
        return rawInput.lines()
                .map(line -> line.split(","))
                .map(split -> new Box(toInt(split[0]), toInt(split[1]), toInt(split[2])))
                .toList();
    }

    private static int toInt(String string){
        return Integer.parseInt(string);
    }
}
