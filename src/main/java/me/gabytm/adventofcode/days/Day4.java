package me.gabytm.adventofcode.days;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day4 extends Day<Integer> {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        showAnswer(new Day4());
    }

    private final MessageDigest md5 = MessageDigest.getInstance("MD5");

    private Day4() throws NoSuchAlgorithmException {
        super(4);
    }

    private int find(int zeros) {
        var input = getInput(1);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            md5.update((input + i).getBytes());
            var no = new BigInteger(1, md5.digest());

            if (no.toString(16).length() == 32-zeros) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public Integer solveFirstPart() {
        return find(5);
    }

    @Override
    public Integer solveSecondPart() {
        return find(6);
    }

}
