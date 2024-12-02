package aoc24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DayTemplate {
    // private static String filename = "./src/aoc24/day/input.txt";
    private static String filename = "./src/aoc24/day/example.txt";

    private DayTemplate(List<String> input) {
        parse(input);
    }

    private void parse(List<String> input) {
        for (String line : input) {

        }
    }

    public static void main(String[] args) throws IOException {
        new DayTemplate(Files.readAllLines(Path.of(filename)));
    }
}
