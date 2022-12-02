package me.gabytm.adventofcode.days;

public class Day1 extends Day<Integer> {

    public static void main(String[] args) {
        showAnswer(new Day1());
    }

    private Day1() {
        super(1);
    }

    @Override
    public Integer solveFirstPart() {
        return getInput(1).chars()
                .map(it -> it == '(' ? 1 : -1)
                .sum();
    }

    @Override
    public Integer solveSecondPart() {
        var level = 0;
        var n = 0;

        for (var ch : getInput(1).toCharArray()) {
            level += (ch == '(' ? 1 : -1);
            n++;

            if (level == -1) {
                return n;
            }
        }

        return null;
    }

}
