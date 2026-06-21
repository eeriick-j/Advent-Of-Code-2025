package aoc.day08;

import tasks.RawInputReader;

import java.util.*;

public class Solver {
    public static void main(String[] args) {
        List<Box> boxes = InputParser.parse(RawInputReader.read("inputs/day08.txt"));
    }

    public static List<Edge> edges(List<Box> boxes){
        List<Edge> edges = new ArrayList<>();

        for(int i=0; i<boxes.size(); i++){
            Box a = boxes.get(i);
            for(int j=i+1; j<boxes.size(); j++){
                Box b = boxes.get(j);

                int dx = a.x() - b.x();
                int dy = a.y() - b.y();
                int dz = a.z() - b.z();

                int distance = dx*dx + dy*dy + dz*dz;
                edges.add(new Edge(i, j, distance));
            }
        }
        return edges;
    }
}
