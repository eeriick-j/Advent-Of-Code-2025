package aoc.day03;

import tasks.RawInputReader;

import java.util.List;

public class Solver {
    public static void main(String[] args) {
        List<Bank> banks = InputParser.parse(RawInputReader.read("inputs/day03.txt"));
        System.out.println("Part 1: " + solvePart1(banks));
    }

    public static long solvePart1(List<Bank> banks) {
        /// Sumas de las mayores parejas de 2 dígitos (concatenados) para cada banco
        long sumVoltages = 0;
        for (Bank bank : banks) {
            int bestPair = 0;
            for(int i=0; i<bank.size(); i++) {
                for(int j=i+1; j<bank.size(); j++) {
                    int currentPair = bank.voltages().get(i) * 10 +  bank.voltages().get(j);
                    bestPair = Math.max(bestPair, currentPair);
                }
            }
            sumVoltages += bestPair;
        }
        return sumVoltages;
    }
}
