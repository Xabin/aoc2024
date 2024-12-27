package aoc24.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day9a {
    // private static String filename = "./src/aoc24/day9/input.txt";
    private static String filename = "./src/aoc24/day9/example.txt";
    private List<String> filesystem = new ArrayList<>();

    private Day9a(List<String> input) {
        parse(input);

        fragment();

        System.out.println("Checksum: " + checksum());
    }

    private void parse(List<String> input) {
        char[] digits = input.get(0).toCharArray();
        int fileCounter = 0;

        for (int i = 0; i < digits.length; i++) {
            int blockSize = Integer.parseInt(digits[i] + "");
            int fileId = fileCounter;

            if (i % 2 == 0) {
                IntStream.range(0, blockSize).forEach(unused -> filesystem.add("" + fileId));
                fileCounter++;
            } else {
                IntStream.range(0, blockSize).forEach(unused -> filesystem.add("."));
            }
        }
    }

    private void fragment() {
        for (int i = 0; i < filesystem.size(); i++) {
            String block = filesystem.get(i);

            if (block.equals(".")) {
                filesystem.set(i, lastFileBlock(i));
            }
        }
    }

    private String lastFileBlock(int currentPosition) {
        for (int i = filesystem.size() - 1; i > currentPosition; i--) {
            String block = filesystem.get(i);

            if (!block.equals(".")) {
                filesystem.set(i, ".");
                return block;
            }
        }

        return ".";
    }

    private long checksum() {
        long checksum = 0;

        for (int i = 0; i < filesystem.size(); i++) {
            if (filesystem.get(i).equals(".")) {
                return checksum;
            }

            checksum += Integer.parseInt(filesystem.get(i)) * i;
        }

        return checksum;
    }

    public static void main(String[] args) throws IOException {
        new Day9a(Files.readAllLines(Path.of(filename)));
    }
}
