package aoc24.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6a {
    // private static String filename = "./src/aoc24/day6/input.txt";
    private static String filename = "./src/aoc24/day6/example.txt";

    private final Set<VisitedPosition> visitedPositions = new HashSet<>();
    private final char[][] map;

    private int guardLocationX;
    private int guardLocationY;

    private Direction direction = Direction.NORTH;

    private Day6a(List<String> input) {
        map = new char[input.size()][input.get(0).length()];

        parse(input);

        visitedPositions.add(new VisitedPosition(guardLocationX, guardLocationY));

        while (true) {
            int nextX = guardLocationX + directionX();
            int nextY = guardLocationY + directionY();

            if (nextX < 0 || nextX > map[0].length || nextY < 0 || nextY > map.length) {
                break;
            }

            if (map[nextY][nextX] != '#') {
                guardLocationX = nextX;
                guardLocationY = nextY;
                visitedPositions.add(new VisitedPosition(nextX, nextY));
            } else {
                rotate();
            }
        }

        System.out.println("Distinct visited positions: " + visitedPositions.size());
    }

    private int directionX() {
        return switch (direction) {
        case EAST -> 1;
        case NORTH -> 0;
        case SOUTH -> 0;
        case WEST -> -1;
        };
    }

    private int directionY() {
        return switch (direction) {
        case EAST -> 0;
        case NORTH -> 1;
        case SOUTH -> -1;
        case WEST -> 0;
        };
    }

    private void rotate() {
        direction = switch (direction) {
        case EAST -> Direction.SOUTH;
        case NORTH -> Direction.EAST;
        case SOUTH -> Direction.WEST;
        case WEST -> Direction.NORTH;
        };
    }

    private void parse(List<String> input) {
        int i = input.size() - 1;
        int j = 0;

        for (int k = 0; k < input.size(); k++) {
            for (int l = 0; l < input.get(0).length(); l++) {
                char position = input.get(k).charAt(l);
                map[i][j] = position;

                if (position == '^') {
                    guardLocationX = j;
                    guardLocationY = i;
                }
                j++;
            }
            i--;
            j = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        new Day6a(Files.readAllLines(Path.of(filename)));
    }

    private record VisitedPosition(int x, int y) {

    }

    private enum Direction {
        NORTH, EAST, SOUTH, WEST
    }
}
