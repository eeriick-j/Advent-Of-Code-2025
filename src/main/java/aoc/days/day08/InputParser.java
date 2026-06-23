package aoc.days.day08;

import aoc.days.day08.model.Box;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<Box> parse(String input) {
        String[] lines = input.split("\\R");
        List<Box> boxes = new ArrayList<>();

        for (int id = 0; id < lines.length; id++) {
            List<Integer> boxItems = Arrays.stream(lines[id].split(","))
                    .map(Integer::parseInt)
                    .toList();
            boxes.add(new Box(id, boxItems.get(0), boxItems.get(1), boxItems.get(2)));
        }

        return boxes;
    }
}
