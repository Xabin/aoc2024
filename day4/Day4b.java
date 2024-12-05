package aoc24.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4b {
    private static final int[] xm1 = new int[] { -1, 1, -1, 1 };
    private static final int[] xs1 = new int[] { 1, -1, 1, -1 };
    private static final int[] xm2 = new int[] { -1, 1, 1, -1 };
    private static final int[] xs2 = new int[] { 1, -1, -1, 1 };
    private static final int[] ym1 = new int[] { 1, -1, -1, 1 };
    private static final int[] ys1 = new int[] { 1, -1, 1, -1 };
    private static final int[] ym2 = new int[] { -1, 1, -1, 1 };
    private static final int[] ys2 = new int[] { -1, 1, 1, -1 };

    // private static String filename = "./src/aoc24/day4/input.txt";
    private static String filename = "./src/aoc24/day4/example.txt";

    private final char[][] letters;

    private int numberOfMas = 0;

    private Day4b(List<String> input) {
        letters = new char[input.size() + 6][input.get(0).length() + 6];

        init(input);
        parse(input);

        process(input);

        System.out.println("numberOfMas: " + numberOfMas);
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
                for (int k = 0; k < 4; k++) {
                    if (letters[i][j] == 'A' && letters[i + ym1[k]][j + xm1[k]] == 'M'
                            && letters[i + ys1[k]][j + xs1[k]] == 'S' && letters[i + ym2[k]][j + xm2[k]] == 'M'
                            && letters[i + ys2[k]][j + xs2[k]] == 'S') {
                        numberOfMas++;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Day4b(Files.readAllLines(Path.of(filename)));
    }
}
