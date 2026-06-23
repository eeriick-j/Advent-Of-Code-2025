package aoc.core;

public interface InputParser<T> {
    T parse(String rawInput);
}
