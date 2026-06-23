package aoc.days.day12;

import aoc.days.day12.model.Cell;
import aoc.days.day12.model.Piece;
import aoc.days.day12.model.Puzzle;
import aoc.days.day12.model.Region;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static Puzzle parse(String rawInput) {
        String[] lines = rawInput.split("\\R");
        List<Piece> pieces = new ArrayList<>();
        List<Region> regions = new ArrayList<>();
        int i = 0;

        // Parseo de Pieces
        while (i < lines.length) {
            String line = lines[i].trim();
            if (line.isEmpty()) {
                i++;
                continue;
            }

            // Si empieza región, salimos del bloque de piezas
            if (line.contains("x") && line.contains(":")) break;

            // Inicio de pieza
            if (line.endsWith(":")) {
                int id = Integer.parseInt(line.replace(":", "").trim());
                i++;

                List<Cell> cells = new ArrayList<>();
                int y = 0;

                while (i < lines.length) {
                    String row = lines[i].trim();

                    // Fin pieza: línea vacía o inicio región
                    if (row.isEmpty() || (row.contains("x") && row.contains(":"))) break;

                    for (int x = 0; x < row.length(); x++) {
                        if (row.charAt(x) == '#') cells.add(new Cell(x, y));
                    }
                    y++;
                    i++;
                }
                pieces.add(new Piece(id, cells));
                continue;
            }
            i++;
        }

        // Parseo de regiones
        while (i < lines.length) {
            String line = lines[i].trim();
            i++;

            if (line.isEmpty()) continue;
            if (!line.contains(":")) continue;

            String[] parts = line.split(":");
            String[] dims = parts[0].split("x");

            int width = Integer.parseInt(dims[0].trim());
            int height = Integer.parseInt(dims[1].trim());

            int[] req = Arrays.stream(parts[1].trim().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            regions.add(new Region(width, height, req));
        }

        return new Puzzle(pieces, regions);
    }
}
