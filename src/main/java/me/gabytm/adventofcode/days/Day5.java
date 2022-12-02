package me.gabytm.adventofcode.days;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Day5 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day5());
    }

    private Day5() {
        super(5);
    }

    @Override
    public Integer solveFirstPart() {
        final var disallowedPattern = Pattern.compile("(ab|cd|pq|xy)");
        final var vowelsPattern = Pattern.compile("(?:[aeiou].*){3}");
        final var duplicatedPattern = Pattern.compile("(.)\\1");

        return (int) Arrays.stream(getInput(1, "\n"))
                .filter(line -> !disallowedPattern.matcher(line).find())
                .filter(line -> vowelsPattern.matcher(line).find())
                .filter(line -> duplicatedPattern.matcher(line).find())
                .count();
    }

    @Override
    public Integer solveSecondPart() {
        var rule1 = Pattern.compile("(..).*\\1");
        var rule2 = Pattern.compile("(.).\\1");
        return (int) Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                .filter(line -> rule1.matcher(line).find())
                .filter(line -> rule2.matcher(line).find())
                .count();
    }

}
