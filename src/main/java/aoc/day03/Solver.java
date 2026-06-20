package aoc.day03;

import tasks.RawInputReader;

public class Solver {
    public static void main(String[] args) {
        for(Bank bank: InputParser.parse(RawInputReader.read("inputs/day03.txt"))){
            System.out.println(bank);
        }
    }
}
