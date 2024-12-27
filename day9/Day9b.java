package aoc24.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day9b {
    // private static String filename = "./src/aoc24/day9/input.txt";
    private static String filename = "./src/aoc24/day9/example.txt";
    private List<String> filesystem = new ArrayList<>();

    private Day9b(List<String> input) {
        parse(input);

        defrag();

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

    private void defrag() {
        int i = filesystem.size() - 1;

        while (i > 0) {
            String block = filesystem.get(i);

            if (block.equals(".")) {
                i--;
                continue;
            }

            int fileBlocks = (int) filesystem.stream().filter(block::equals).count();
            int indexOfAvailableSpaceForFile = findAvailableSpace(fileBlocks);

            if (indexOfAvailableSpaceForFile != -1 && indexOfAvailableSpaceForFile < i) {
                moveFile(indexOfAvailableSpaceForFile, fileBlocks, block);
            }

            i -= fileBlocks;
        }
    }

    private int findAvailableSpace(int spaceNeeded) {
        for (int i = 0; i < filesystem.size(); i++) {
            if (!filesystem.get(i).equals(".")) {
                continue;
            }

            if (i + spaceNeeded < filesystem.size() && hasSpace(i, i + spaceNeeded)) {
                return i;
            }
        }

        return -1;
    }

    private boolean hasSpace(int start, int end) {
        for (int i = start; i < end; i++) {
            if (!filesystem.get(i).equals(".")) {
                return false;
            }
        }

        return true;
    }

    private void moveFile(int indexOfAvailableSpace, int blocksToMove, String fileId) {
        for (int i = 0; i < filesystem.size(); i++) {
            if (filesystem.get(i).equals(fileId)) {
                filesystem.set(i, ".");
            }
        }

        IntStream.range(indexOfAvailableSpace, indexOfAvailableSpace + blocksToMove)
                .forEach(index -> filesystem.set(index, fileId));
    }

    private long checksum() {
        long checksum = 0;

        for (int i = 0; i < filesystem.size(); i++) {
            String block = filesystem.get(i);

            if (block.equals(".")) {
                continue;
            }

            checksum += Integer.parseInt(block) * i;
        }

        return checksum;
    }

    public static void main(String[] args) throws IOException {
        new Day9b(Files.readAllLines(Path.of(filename)));
    }
}
