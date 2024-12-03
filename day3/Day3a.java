package aoc24.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3a {
    // private static String filename = "./src/aoc24/day3/input.txt";
    private static String filename = "./src/aoc24/day3/example.txt";
    private String memory;
    private int sum;

    private Day3a(List<String> input) {
        parse(input);

        while (true) {
            int indexOfMul = memory.indexOf("mul(");

            if (indexOfMul == -1) {
                break;
            }

            indexOfMul += 4;

            memory = memory.substring(indexOfMul);

            int indexOfComma = memory.indexOf(',');

            if (indexOfComma > 3) {
                continue;
            }

            int firstNumber;

            try {
                firstNumber = Integer.parseInt(memory.substring(0, indexOfComma));
            } catch (NumberFormatException e) {
                continue;
            }

            memory = memory.substring(indexOfComma + 1);

            int indexOfSecondParenthesis = memory.indexOf(')');

            if (indexOfSecondParenthesis > 3) {
                continue;
            }

            int secondNumber;

            try {
                secondNumber = Integer.parseInt(memory.substring(0, indexOfSecondParenthesis));
            } catch (NumberFormatException e) {
                continue;
            }

            System.out.println("Found (" + firstNumber + "," + secondNumber + ")");

            sum += firstNumber * secondNumber;
        }

        System.out.println("Sum: " + sum);
    }

    private void parse(List<String> input) {
        for (String line : input) {
            memory = line;
        }
    }

    public static void main(String[] args) throws IOException {
        new Day3a(Files.readAllLines(Path.of(filename)));
    }
}
