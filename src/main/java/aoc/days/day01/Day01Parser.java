package aoc.days.day01;

import java.util.ArrayList;
import java.util.List;

public class Day01Parser {
    public static List<Rotation> parse(String input){
        List<Rotation> rotations = new ArrayList<>();
        String[] lines = input.split("\\R");
        for (String line : lines) rotations.add(new Rotation(line.charAt(0), Integer.parseInt(line.substring(1))));
        return rotations;
    }
}
