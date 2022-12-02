package me.gabytm.adventofcode.days;

import me.gabytm.adventofcode.util.Pair;

import java.util.HashSet;

public class Day3 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day3());
    }

    private Day3() {
        super(3);
    }

    private Pair<Integer, Integer> move(char direction, Pair<Integer, Integer> coords) {
        return switch (direction) {
            case '^' -> Pair.of(coords.first() + 1, coords.second());
            case 'v' -> Pair.of(coords.first() - 1, coords.second());
            case '>' -> Pair.of(coords.first(), coords.second() + 1);
            default -> Pair.of(coords.first(), coords.second() - 1);
        };
    }

    @Override
    public Integer solveFirstPart() {
        var coords = Pair.of(0, 0);
        var delivered = 1;

        var visitedHouses = new HashSet<String>();
        visitedHouses.add("0;0");

        for (var direction : getInput(1).toCharArray()) {
            coords = move(direction, coords);

            if (visitedHouses.add("%d;%d".formatted(coords.first(), coords.second()))) {
                delivered++;
            }
        }

        return delivered;
    }

    @Override
    public Integer solveSecondPart() {
        var santaCoords = Pair.of(0, 0);
        var robotCoords = Pair.of(0, 0);

        var visitedHouses = new HashSet<String>();
        visitedHouses.add("0;0");
        var delivered = 1;

        var directions = getInput(1).toCharArray();
        for (var i = 0; i < directions.length; i++) {
            if (i % 2 == 0) {
                robotCoords = move(directions[i], robotCoords);
            } else {
                santaCoords = move(directions[i], santaCoords);
            }

            var coords = (i % 2 == 0 ? robotCoords : santaCoords);

            if (visitedHouses.add("%d;%d".formatted(coords.first(), coords.second()))) {
                delivered++;
            }
        }

        return delivered;
    }

}
