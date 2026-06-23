package aoc.days.day09;

import aoc.core.InputParser;
import aoc.days.day09.model.Point;

import java.util.List;

public class Day09Parser implements InputParser<List<Point>> {
    @Override
    public List<Point> parse(String rawInput){
        return rawInput.lines()
                .map(line -> line.split(","))
                .map(split -> new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])))
                .toList();
    }
}
