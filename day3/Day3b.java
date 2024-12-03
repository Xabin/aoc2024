package aoc24.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day3b {
    private static String filename = "./src/aoc24/day3/input.txt";
    // private static String filename = "./src/aoc24/day3/example2.txt";
    private String memory;
    private int sum;
    private boolean enabled = true;

    private Day3b(List<String> input) {
        parse(input);

        while (true) {
            int indexOfMul = memory.indexOf("mul(");

            if (indexOfMul == -1) {
                break;
            }

            indexOfMul += 4;

            enableOrDisable(indexOfMul);

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

            if (enabled) {
                sum += firstNumber * secondNumber;
            }
        }

        System.out.println("Sum: " + sum);
    }

    private void enableOrDisable(int indexOfMul) {
        int indexOfDo = memory.indexOf("do()");
        int indexOfDont = memory.indexOf("don't()");

        if (indexOfDo == -1) {
            indexOfDo = Integer.MAX_VALUE;
        }

        if (indexOfDont == -1) {
            indexOfDont = Integer.MAX_VALUE;
        }

        if (indexOfDo < indexOfMul && (indexOfDo > indexOfDont || indexOfDont > indexOfMul)) {
            enabled = true;
        } else if (indexOfDont < indexOfMul && (indexOfDont > indexOfDo || indexOfDo > indexOfMul)) {
            enabled = false;
        }
    }

    private void parse(List<String> input) {
        for (String line : input) {
            memory = line;
        }
    }

    public static void main(String[] args) throws IOException {
        new Day3b(Files.readAllLines(Path.of(filename)));
    }
}
