package aoc24.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5b {
    // private static String filename = "./src/aoc24/day5/input.txt";
    private static String filename = "./src/aoc24/day5/example.txt";

    private List<Rule> rules = new ArrayList<>();
    private List<List<Integer>> updates = new ArrayList<>();
    private List<List<Integer>> incorrectUpdates = new ArrayList<>();

    private int middlePageNumberSum = 0;

    private Day5b(List<String> input) {
        parse(input);
        populateIncorrectUpdates();
        reorderIncorrectUpdates();
        sumMiddlePages();

        System.out.println("middlePageNumberSum: " + middlePageNumberSum);
    }

    private void parse(List<String> input) {
        boolean rule = true;

        for (String line : input) {
            if (line.isEmpty()) {
                rule = false;
            } else if (rule) {
                rules.add(new Rule(Integer.parseInt(line.substring(0, 2)), Integer.parseInt(line.substring(3, 5))));
            } else {
                updates.add(Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toList()));
            }
        }
    }

    private void populateIncorrectUpdates() {
        for (List<Integer> update : updates) {
            for (Rule rule : rules) {
                if (update.containsAll(List.of(rule.before, rule.after))
                        && update.indexOf(rule.before) > update.indexOf(rule.after)) {
                    incorrectUpdates.add(update);
                    break;
                }
            }
        }
    }

    private void reorderIncorrectUpdates() {
        int i = 0;

        while (i < incorrectUpdates.size()) {
            List<Integer> update = incorrectUpdates.get(i);
            boolean unchanged = true;

            for (Rule rule : rules) {
                if (update.containsAll(List.of(rule.before, rule.after))
                        && update.indexOf(rule.before) > update.indexOf(rule.after)) {
                    int indexOfBefore = update.indexOf(rule.before);
                    int indexOfAfter = update.indexOf(rule.after);
                    update.set(indexOfAfter, rule.before);
                    update.set(indexOfBefore, rule.after);
                    unchanged = false;
                }
            }

            if (unchanged) {
                i++;
            }
        }
    }

    private void sumMiddlePages() {
        for (List<Integer> update : incorrectUpdates) {
            middlePageNumberSum += update.get(update.size() / 2);
        }
    }

    public static void main(String[] args) throws IOException {
        new Day5b(Files.readAllLines(Path.of(filename)));
    }

    private record Rule(int before, int after) {

    }
}
