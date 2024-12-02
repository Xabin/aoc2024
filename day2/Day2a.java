package aoc24.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2a {
    // private static String filename = "./src/aoc24/day2/input.txt";
    private static String filename = "./src/aoc24/day2/example.txt";
    private List<List<Integer>> reports = new ArrayList<>();
    private int safeReports = 0;

    private Day2a(List<String> input) {
        parse(input);

        for (List<Integer> report : reports) {
            boolean safe = true;
            int previousLevel = report.get(0);

            for (int i = 1; i < report.size(); i++) {
                int currentLevel = report.get(i);

                if (currentLevel < previousLevel || Math.abs(currentLevel - previousLevel) > 3
                        || Math.abs(currentLevel - previousLevel) < 1) {
                    safe = false;
                    break;
                }

                previousLevel = currentLevel;
            }

            if (safe) {
                safeReports++;
            }

            safe = true;
            previousLevel = report.get(0);

            for (int i = 1; i < report.size(); i++) {
                int currentLevel = report.get(i);

                if (currentLevel > previousLevel || Math.abs(currentLevel - previousLevel) > 3
                        || Math.abs(currentLevel - previousLevel) < 1) {
                    safe = false;
                    break;
                }

                previousLevel = currentLevel;
            }

            if (safe) {
                safeReports++;
            }
        }

        System.out.println("Safe reports: " + safeReports);
    }

    private void parse(List<String> input) {
        for (String line : input) {
            reports.add(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
        }
    }

    public static void main(String[] args) throws IOException {
        new Day2a(Files.readAllLines(Path.of(filename)));
    }
}
