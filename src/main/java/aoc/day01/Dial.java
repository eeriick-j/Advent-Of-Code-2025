package aoc.day01;

public class Dial {
    private int pointer = 50;
    private int numPointsToZero = 0;
    private int numClicksToZero = 0;

    public void rotateFull(Rotation rotation) {
        int sign = (rotation.direction() == 'L') ? -1 : 1;
        pointer = (pointer + sign * rotation.times() + 100) % 100;
        if(pointer == 0) numPointsToZero++;
    }

    public void rotateClickByClick(Rotation rotation) {
        int sign = (rotation.direction() == 'L') ? -1 : 1;
        for(int t=0; t<rotation.times(); t++) {
            pointer = (pointer + sign + 100) % 100;
            if(pointer == 0) numPointsToZero++;
        }
    }

    public int numPointsToZero() {
        return numPointsToZero;
    }

    public int numClicksToZero() {
        return numClicksToZero;
    }
}
