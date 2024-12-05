package aoc24.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4a {
    private static final int[] xm = new int[] { 1, 1, 0, -1, -1, -1, 0, 1 };
    private static final int[] xa = new int[] { 2, 2, 0, -2, -2, -2, 0, 2 };
    private static final int[] xs = new int[] { 3, 3, 0, -3, -3, -3, 0, 3 };
    private static final int[] ym = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };
    private static final int[] ya = new int[] { 0, 2, 2, 2, 0, -2, -2, -2 };
    private static final int[] ys = new int[] { 0, 3, 3, 3, 0, -3, -3, -3 };

    // private static String filename = "./src/aoc24/day4/input.txt";
    private static String filename = "./src/aoc24/day4/example.txt";

    private final char[][] letters;

    private int numberOfXmas = 0;

    private Day4a(List<String> input) {
        letters = new char[input.size() + 6][input.get(0).length() + 6];

        init(input);
        parse(input);

        process(input);

        System.out.println("numberOfXmas: " + numberOfXmas);
    }

    private void init(List<String> input) {
        for (int i = 0; i < input.size() + 6; i++) {
            for (int j = 0; j < input.get(0).length() + 6; j++) {
                letters[i][j] = '.';
            }
        }
    }

    private void parse(List<String> input) {
        for (int i = 3; i < input.size() + 3; i++) {
            for (int j = 3; j < input.get(0).length() + 3; j++) {
                letters[i][j] = input.get(i - 3).charAt(j - 3);
            }
        }
    }

    private void process(List<String> input) {
        for (int i = 3; i < input.size() + 3; i++) {
            for (int j = 3; j < input.get(0).length() + 3; j++) {
                for (int k = 0; k < 8; k++) {
                    if (letters[i][j] == 'X' && letters[i + ym[k]][j + xm[k]] == 'M'
                            && letters[i + ya[k]][j + xa[k]] == 'A' && letters[i + ys[k]][j + xs[k]] == 'S') {
                        numberOfXmas++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Day4a(Files.readAllLines(Path.of(filename)));
    }
}
