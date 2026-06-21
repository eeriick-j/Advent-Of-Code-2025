package aoc.day04;

import tasks.RawInputReader;

public class Solver {
    public static void main(String[] args) {
        char[][] rollsOfPapers = InputParser.parse(RawInputReader.read("inputs/day04.txt"));
        System.out.println("First part: " + solve(rollsOfPapers));
    }

    public static int solve(char[][] rollsOfPapers) {
        int accessibleRolls = 0;

        for(int i=0; i<rollsOfPapers.length; i++){
            for(int j=0; j<rollsOfPapers[i].length; j++){
                if(isAccessible(rollsOfPapers, i, j)) accessibleRolls++;
            }
        }
        return accessibleRolls;
    }

    private static boolean isAccessible(char[][] rollsOfPapers, int i, int j) {
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
}
