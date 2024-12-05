package aoc24.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5a {
    // private static String filename = "./src/aoc24/day5/input.txt";
    private static String filename = "./src/aoc24/day5/example.txt";

    private List<Rule> rules = new ArrayList<>();
    private List<List<Integer>> updates = new ArrayList<>();

    private int middlePageNumberSum = 0;

    private Day5a(List<String> input) {
        parse(input);

        for (List<Integer> update : updates) {
            boolean validUpdate = true;

            for (Rule rule : rules) {
                if (update.containsAll(List.of(rule.before, rule.after))
                        && update.indexOf(rule.before) > update.indexOf(rule.after)) {
                    validUpdate = false;
                    break;
                }
            }

            if (validUpdate) {
                middlePageNumberSum += update.get(update.size() / 2);
            }
        }

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
                updates.add(Arrays.stream(line.split(",")).map(Integer::parseInt).toList());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Day5a(Files.readAllLines(Path.of(filename)));
    }

    private record Rule(int before, int after) {

    }
}
