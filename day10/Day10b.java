package aoc24.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Day10b {
    // private static String filename = "./src/aoc24/day10/input.txt";
    private static String filename = "./src/aoc24/day10/example.txt";

    private static final int[] dx = new int[] { 1, 0, -1, 0 };
    private static final int[] dy = new int[] { 0, -1, 0, 1 };

    private final int[][] grid;

    private final List<Path> paths = new ArrayList<>();

    private Day10b(List<String> input) {
        grid = new int[input.size() + 2][input.get(0).length() + 2];

        init(input);
        parse(input);

        process();

        System.out.println("Paths: " + paths.size());
    }

    private void init(List<String> input) {
        for (int i = 0; i < input.size() + 2; i++) {
            for (int j = 0; j < input.get(0).length() + 2; j++) {
                grid[i][j] = -1;
            }
        }
    }

    private void parse(List<String> input) {
        for (int i = 1; i < input.size() + 1; i++) {
            for (int j = 1; j < input.get(0).length() + 1; j++) {
                grid[i][j] = input.get(i - 1).charAt(j - 1) - 48;
            }
        }
    }

    private void process() {
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (grid[i][j] == 0) {
                    search(i, j, i, j, 1);
                }
            }
        }
    }

    private void search(int originY, int originX, int y, int x, int nextHeight) {
        for (int k = 0; k < 4; k++) {
            int nextY = y + dy[k];
            int nextX = x + dx[k];

            if (grid[nextY][nextX] == nextHeight) {
                if (nextHeight == 9) {
                    paths.add(new Path(originY, originX, nextY, nextX));
                } else {
                    search(originY, originX, nextY, nextX, nextHeight + 1);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Day10b(Files.readAllLines(java.nio.file.Path.of(filename)));
    }

    private record Path(int originY, int originX, int destinationY, int destinationX) {

    }
}
