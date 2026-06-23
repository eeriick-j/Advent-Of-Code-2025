package aoc.days.day04;

import aoc.DayRegistry;
import aoc.core.DaySolver;

public class Day04 implements DaySolver {
    private final char[][] rollsOfPapers;

    public Day04(char[][] rollsOfPapers) {
        this.rollsOfPapers = rollsOfPapers;
    }

    public static DaySolver build(String input) {
        return new Day04(new Day04Parser().parse(input));
    }

    static {
        DayRegistry.register(4, Day04::build);
    }

    @Override
    public Integer solvePart1() {
        /// Número de rollos accesibles (los que tengan menos de 4 rollos adyacentes (8-position))
        int accessibleRolls = 0;

        for(int i=0; i<rollsOfPapers.length; i++){
            for(int j=0; j<rollsOfPapers[i].length; j++){
                if(isAccessible(rollsOfPapers, i, j)) accessibleRolls++;
            }
        }
        return accessibleRolls;
    }

    private boolean isAccessible(char[][] rollsOfPapers, int i, int j) {
        if(rollsOfPapers[i][j] != '@') return false;
        int numRollsAround = 0;

        for(int k=i-1; k<=i+1; k++){
            if(k < 0 || k >= rollsOfPapers.length) continue;
            for(int l=j-1; l<=j+1; l++){
                if(l < 0 || l >= rollsOfPapers[k].length || (i == k && j == l)) continue;
                if(rollsOfPapers[k][l] == '@') numRollsAround++;
            }
        }
        return numRollsAround < 4;
    }

    @Override
    public Integer solvePart2(){
        /// Número de rollos accesibles (los que tengan menos de 4 rollos adyacentes (8-position))
        /// Al terminar cada ronda, se eliminan los accesibles de esa ronda y nuevos rollos
        /// resultan accesibles
        int accessibleRolls = 0;

        while (true) {
            boolean[][] rollsToRemove = new boolean[rollsOfPapers.length][rollsOfPapers[0].length];
            int removed = 0;

            for(int i=0; i<rollsOfPapers.length; i++){
                for(int j=0; j<rollsOfPapers[i].length; j++){
                    if(isAccessible(rollsOfPapers, i, j)) {
                        accessibleRolls++;
                        rollsToRemove[i][j] = true;
                        removed++;
                    }
                }
            }

            // Parar si en la ronda no hubo ningún rollo accesible
            if(removed == 0) break;

            for(int i=0; i<rollsOfPapers.length; i++){
                for(int j=0; j<rollsOfPapers[i].length; j++){
                    if(rollsToRemove[i][j]) rollsOfPapers[i][j]= '.';
                }
            }
        }
        return accessibleRolls;
    }
}
