package aoc24.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1a {
    private static String filename = "./src/aoc24/day1/example.txt";
    // private static String filename = "./src/aoc24/day1/input.txt";
    private List<Integer> leftList = new ArrayList<>();
    private List<Integer> rightList = new ArrayList<>();

    private Day1a(List<String> input) {
        parse(input);

        leftList.sort(Integer::compare);
        rightList.sort(Integer::compare);

        int sum = 0;

        for (int i = 0; i < leftList.size(); i++) {
            Integer left = leftList.get(i);
            Integer right = rightList.get(i);
            int difference = Math.abs(left - right);
            sum += difference;
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
        new Day1a(Files.readAllLines(Path.of(filename)));
    }
}
