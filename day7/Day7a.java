package aoc24.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7a {
    // private static String filename = "./src/aoc24/day7/input.txt";
    private static String filename = "./src/aoc24/day7/example.txt";
    private List<Equation> equations = new ArrayList<>();

    private long calibrationResult = 0;

    private Day7a(List<String> input) {
        parse(input);

        for (Equation equation : equations) {
            long result = calculate(equation.result, equation.values, 0, 0);

            if (result == equation.result) {
                calibrationResult += result;
            }
        }

        System.out.println("calibrationResult: " + calibrationResult);
    }

    private long calculate(long result, int[] values, int i, long currentResult) {
        if (i + 1 == values.length) {
            if (currentResult + values[i] == result) {
                return result;
            }

            if (currentResult * values[i] == result) {
                return result;
            }

            return 0;
        }

        long plus = calculate(result, values, i + 1, currentResult + values[i]);

        if (plus != 0) {
            return plus;
        } else {
            return calculate(result, values, i + 1, currentResult * values[i]);
        }
    }

    private void parse(List<String> input) {
        for (String line : input) {
            long result = Long.parseLong(line.substring(0, line.indexOf(':')));
            int[] values = Arrays.stream(line.substring(line.indexOf(':') + 2).split(" ")).mapToInt(Integer::parseInt)
                    .toArray();
            equations.add(new Equation(result, values));
        }
    }

    public static void main(String[] args) throws IOException {
        new Day7a(Files.readAllLines(Path.of(filename)));
    }

    private record Equation(long result, int[] values) {
        @Override
        public String toString() {
            return "Equation [result=" + result + ", values=" + Arrays.toString(values) + "]";
        }
    }
}
