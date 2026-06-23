package aoc.days.day03.model;

import java.util.List;

public record Bank(List<Integer> voltages) {
    public int size(){
        return voltages.size();
    }
}
