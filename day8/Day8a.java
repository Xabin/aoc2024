package aoc24.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Day8a {
    // private static String filename = "./src/aoc24/day8/input.txt";
    private static String filename = "./src/aoc24/day8/example.txt";

    private Set<Point> antinodes = new HashSet<>();
    private Map<String, List<Point>> antennas = new HashMap<>();

    private final char[][] grid;

    private Day8a(List<String> input) {
        grid = new char[input.size()][input.get(0).length()];

        parse(input);

        for (Entry<String, List<Point>> entry : antennas.entrySet()) {
            String antenna = entry.getKey();
            List<Point> points = entry.getValue();

            for (int i = 0; i < points.size() - 1; i++) {
                Point firstAntenna = points.get(i);

                for (int j = 1; j < points.size(); j++) {
                    Point secondAntenna = points.get(j);

                    int distanceX = firstAntenna.x - secondAntenna.x;
                    int distanceY = firstAntenna.y - secondAntenna.y;

                    checkAntenna(antenna, firstAntenna, distanceX, distanceY);
                    checkAntenna(antenna, secondAntenna, distanceX, distanceY);
                }
            }
        }

        System.out.println("Antinodes: " + antinodes.size());
    }

    private void checkAntenna(String antenna, Point antennaLocation, int distanceX, int distanceY) {
        int plusX = antennaLocation.x + distanceX;
        int plusY = antennaLocation.y + distanceY;

        if (!outsideGrid(plusX, plusY) && !antenna.equals("" + grid[plusY][plusX])) {
            antinodes.add(new Point(plusX, plusY));
        }

        int minusX = antennaLocation.x - distanceX;
        int minusY = antennaLocation.y - distanceY;

        if (!outsideGrid(minusX, minusY) && !antenna.equals("" + grid[minusY][minusX])) {
            antinodes.add(new Point(minusX, minusY));
        }
    }

    private boolean outsideGrid(int x, int y) {
        if (x < 0 || x >= grid[0].length) {
            return true;
        }

        return y < 0 || y >= grid.length;
    }

    private void parse(List<String> input) {
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                char character = input.get(i).charAt(j);
                grid[i][j] = character;
                String antenna = "" + character;

                if (!antenna.equals(".")) {
                    antennas.computeIfAbsent(antenna, unused -> new ArrayList<>()).add(new Point(j, i));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Day8a(Files.readAllLines(Path.of(filename)));
    }

    private record Point(int x, int y) {

    }
}
