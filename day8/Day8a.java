package aoc24.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day8a {
    // private static String filename = "./src/aoc24/day/input.txt";
    private static String filename = "./src/aoc24/day/example.txt";

    private Day8a(List<String> input) {
        parse(input);
    }

    private void parse(List<String> input) {
        for (String line : input) {

        }
    }

    public static void main(String[] args) throws IOException {
        new Day8a(Files.readAllLines(Path.of(filename)));
    }
}
