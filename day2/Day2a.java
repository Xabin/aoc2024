package aoc24.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2a {
    // private static String filename = "./src/aoc24/input.txt";
    private static String filename = "./src/aoc24/example.txt";

    public Day2a(List<String> input) {
        parse(input);
    }

    private void parse(List<String> input) {
        for (String line : input) {

        }
    }

    public static void main(String[] args) throws IOException {
        new Day2a(Files.readAllLines(Path.of(filename)));
    }
}
