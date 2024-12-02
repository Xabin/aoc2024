package aoc24.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day2b {
    // private static String filename = "./src/aoc24/day2/input.txt";
    private static String filename = "./src/aoc24/day2/example.txt";
    private List<List<Integer>> reports = new ArrayList<>();

    private Day2b(List<String> input) {
        parse(input);

        int safeReports = 0;

        for (List<Integer> report : reports) {
            if (ascendingOk(report)) {
                safeReports++;
            } else if (descendingOk(report)) {
                safeReports++;
            }
        }

        System.out.println("Safe reports: " + safeReports);
    }

    private boolean descendingOk(List<Integer> report) {
        boolean safe = true;
        int previousLevel = report.get(0);

        for (int i = 1; i < report.size(); i++) {
            int currentLevel = report.get(i);

            if (descendingDifferenceUnsafe(previousLevel, currentLevel)) {
                List<Integer> reportWithoutPreviousUnsafeLevel = reportWithoutElement(report, i - 1);
                List<Integer> reportWithoutCurrentUnsafeLevel = reportWithoutElement(report, i);

                return descendingOkWithoutElement(reportWithoutPreviousUnsafeLevel)
                        || descendingOkWithoutElement(reportWithoutCurrentUnsafeLevel);
            }

            previousLevel = currentLevel;
        }

        return safe;
    }

    private boolean descendingDifferenceUnsafe(int previousLevel, int currentLevel) {
        return currentLevel > previousLevel || differenceOk(previousLevel, currentLevel);
    }

    private boolean differenceOk(int previousLevel, int currentLevel) {
        int difference = currentLevel - previousLevel;
        return Math.abs(difference) > 3 || Math.abs(difference) < 1;
    }

    private List<Integer> reportWithoutElement(List<Integer> report, int i) {
        return IntStream.range(0, report.size()).filter(index -> index != i).map(report::get).boxed().toList();
    }

    private boolean descendingOkWithoutElement(List<Integer> report) {
        boolean safe = true;
        int previousLevel = report.get(0);

        for (int i = 1; i < report.size(); i++) {
            int currentLevel = report.get(i);

            if (descendingDifferenceUnsafe(previousLevel, currentLevel)) {
                return false;
            }

            previousLevel = currentLevel;
        }

        return safe;
    }

    private boolean ascendingOk(List<Integer> report) {
        boolean safe = true;
        int previousLevel = report.get(0);

        for (int i = 1; i < report.size(); i++) {
            int currentLevel = report.get(i);

            if (ascendingDifferenceUnsafe(previousLevel, currentLevel)) {
                List<Integer> reportWithoutPreviousUnsafeLevel = reportWithoutElement(report, i - 1);
                List<Integer> reportWithoutCurrentUnsafeLevel = reportWithoutElement(report, i);

                return ascendingOkWithoutElement(reportWithoutPreviousUnsafeLevel)
                        || ascendingOkWithoutElement(reportWithoutCurrentUnsafeLevel);
            }

            previousLevel = currentLevel;
        }

        return safe;
    }

    private boolean ascendingDifferenceUnsafe(int previousLevel, int currentLevel) {
        return currentLevel < previousLevel || differenceOk(previousLevel, currentLevel);
    }

    private boolean ascendingOkWithoutElement(List<Integer> report) {
        boolean safe = true;
        int previousLevel = report.get(0);

        for (int i = 1; i < report.size(); i++) {
            int currentLevel = report.get(i);

            if (ascendingDifferenceUnsafe(previousLevel, currentLevel)) {
                return false;
            }

            previousLevel = currentLevel;
        }

        return safe;
    }

    private void parse(List<String> input) {
        for (String line : input) {
            reports.add(Arrays.stream(line.split(" ")).map(Integer::parseInt).toList());
        }
    }

    public static void main(String[] args) throws IOException {
        new Day2b(Files.readAllLines(Path.of(filename)));
    }
}
