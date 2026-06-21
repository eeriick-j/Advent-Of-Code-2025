package aoc.day09;

import java.util.List;

public class InputParser {
    public static List<Point> parse(String rawInput){
        return rawInput.lines()
                .map(line -> line.split(","))
                .map(split -> new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])))
                .toList();
    }
}
