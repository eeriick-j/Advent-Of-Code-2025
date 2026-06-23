package aoc;

public class Main {
    public static void main(String[] args) {
        for(int day=1; day<=12; day++) {
            System.out.println("Day " + day);
            DayRunner.run(day);
            System.out.println("-------------------------");
        }
    }
}