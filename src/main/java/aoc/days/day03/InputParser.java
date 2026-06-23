package aoc.days.day03;

import java.util.List;

public class InputParser {
    public static List<Bank> parse(String rawInput) {
        return rawInput.lines()
                .map(InputParser::toBank)
                .toList();
    }

    private static Bank toBank(String line){
        return new Bank(
                line.chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList()
        );
    }
}
