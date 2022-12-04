package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.util.Pair;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day6 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day6());
    }

    private final Pattern pattern = Pattern.compile("(?<action>[\\w\\s]+) (?<startX>\\d+),(?<startY>\\d+) through (?<endX>\\d+),(?<endY>\\d+)");
    private final int dimension = 1_000;

    private Day6() {
        super(6);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private Stream<Pair<String, Range>> getInput() {
        return Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                .map(pattern::matcher)
                .map(it -> {
                    it.find();

                    final var startX = Integer.parseInt(it.group("startX"));
                    final var endX = Integer.parseInt(it.group("endX"));
                    final var startY = Integer.parseInt(it.group("startY"));
                    final var endY = Integer.parseInt(it.group("endY"));

                    return Pair.of(it.group("action"), new Range(startX, endX, startY, endY));
                });
    }

    private int count(final int[][] matrix) {
        var total = 0;

        for (var x = 0; x < dimension; x++) {
            for (var y = 0; y < dimension; y++) {
                total += matrix[x][y];
            }
        }

        return total;
    }

    @Override
    public Integer solveFirstPart() {
        final var matrix = new int[dimension][dimension];
        getInput().forEach(it -> {
            for (var x = it.second().startX; x <= it.second().endX; x++) {
                for (var y = it.second().startY; y <= it.second().endY; y++) {
                    matrix[x][y] = switch (it.first()) {
                        case "turn on" -> 1;
                        case "turn off" -> 0;
                        default -> matrix[x][y] ^ 1; // swap 0 with 1 and vice-versa
                    };
                }
            }
        });

        return count(matrix);
    }

    @Override
    public Integer solveSecondPart() {
        final var matrix = new int[dimension][dimension];

        getInput().forEach(it -> {
            for (var x = it.second().startX; x <= it.second().endX; x++) {
                for (var y = it.second().startY; y <= it.second().endY; y++) {
                    switch (it.first()) {
                        case "turn on" -> matrix[x][y]++;
                        case "turn off" -> matrix[x][y] = Math.max(0, matrix[x][y] - 1);
                        default -> matrix[x][y] += 2;
                    };
                }
            }
        });

        return count(matrix);
    }

    private record Range(int startX, int endX, int startY, int endY) { }

}
