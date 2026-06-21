package aoc.day03;

import java.util.List;

public record Bank(List<Integer> voltages) {
    public int size(){
        return voltages.size();
    }
}
