package aoc24.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1b {
    // private static String filename = "./src/aoc24/example.txt";
    private static String filename = "./src/aoc24/input.txt";
    List<Integer> leftList = new ArrayList<>();
    List<Integer> rightList = new ArrayList<>();

    public Day1b(List<String> input) {
        parse(input);

        int sum = 0;

        for (int left : leftList) {
            int occurranceOfLeft = 0;

            for (int right : rightList) {
                if (left == right) {
                    occurranceOfLeft++;
                }
            }

            sum += left * occurranceOfLeft;
        }

        System.out.println("Sum: " + sum);
    }

    private void parse(List<String> input) {
        for (String line : input) {
            String[] numbers = line.split("   ");

            leftList.add(Integer.parseInt(numbers[0]));
            rightList.add(Integer.parseInt(numbers[1]));
        }
    }

    public static void main(String[] args) throws IOException {
        new Day1b(Files.readAllLines(Path.of(filename)));
    }
}
