package me.gabytm.adventofcode.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day2 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day2());
    }

    private Day2() {
        super(2);
    }

    private Stream<List<Integer>> getInput() {
        return Arrays.stream(getInput(1, "\n"))
                .map(String::trim)
                .map(it -> it.split("x"))
                .map(it -> new ArrayList<>(List.of(Integer.parseInt(it[0]), Integer.parseInt(it[1]), Integer.parseInt(it[2]))));
    }

    @Override
    public Integer solveFirstPart() {
        return getInput()
                .flatMapToInt(it -> {
                    var l = it.get(0);
                    var w = it.get(1);
                    var h = it.get(2);
                    var a1 = l*w;
                    var a2 = w*h;
                    var a3 = h*l;

                    return IntStream.of(2*a1 + 2*a2 + 2*a3 + Math.min(a1, Math.min(a2, a3)));
                })
                .sum();
    }

    @Override
    public Integer solveSecondPart() {
        return getInput()
                .flatMapToInt(it -> {
                    Collections.sort(it);
                    var a = it.get(0);
                    var b = it.get(1);
                    var c = it.get(2);
                    return IntStream.of(a+a + b+b + a*b*c);
                })
                .sum();
    }

}
