package aoc.days.day05;

import aoc.core.InputParser;
import aoc.days.day05.model.IDRange;
import aoc.days.day05.model.Pair;

import java.util.ArrayList;
import java.util.List;

public class Day05Parser implements InputParser<Pair> {
    @Override
    public Pair parse(String input) {
        List<IDRange> ranges = new ArrayList<>();
        List<Long> ids = new ArrayList<>();

        input.lines()
                .filter(line -> !line.isEmpty())
                .forEach(line -> add(line, ranges, ids));

        return new Pair(ranges, ids);
    }

    private void add(String line, List<IDRange> ranges, List<Long> ids) {
        int i = line.indexOf('-'); // retorna -1 si no encuentra '-' en la línea (es decir, no es rango sino ID)
        if (i >= 0) {
            ranges.add(new IDRange(
                    Long.parseLong(line.substring(0, i)),
                    Long.parseLong(line.substring(i + 1))));
        } else {
            ids.add(Long.parseLong(line));
        }
    }
}
