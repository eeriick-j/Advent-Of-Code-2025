package aoc.days.day03;

import aoc.core.InputParser;
import aoc.days.day03.model.Bank;

import java.util.List;

public class Day03Parser implements InputParser<List<Bank>> {
    @Override
    public List<Bank> parse(String rawInput) {
        return rawInput.lines()
                .map(Day03Parser::toBank)
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
