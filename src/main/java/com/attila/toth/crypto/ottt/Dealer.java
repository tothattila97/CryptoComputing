package com.attila.toth.crypto.ottt;

import java.security.SecureRandom;
import java.util.Arrays;

/**
 * Class for the Trusted Dealer
 */
public class Dealer {
    public int r;
    public int s;
    public int n;
    public boolean[][] matrixA;
    public boolean[][] matrixB;

    public Dealer(boolean[][] truthTable, int n) {
        this.n = n;
        int matrixLen = (int) Math.pow(2, n);
        r = new SecureRandom().nextInt(matrixLen);
        s = new SecureRandom().nextInt(matrixLen);

        boolean[][] t; // truth table
        t = Arrays.stream(truthTable).map(boolean[]::clone).toArray(boolean[][]::new);

        matrixA = new boolean[matrixLen][matrixLen];
        matrixB = new boolean[matrixLen][matrixLen];

        fillMb();
        fillMa(t, matrixLen);
    }

    private void fillMb() {
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            for (int row = 0; row < matrixB.length; row++) {
                for (int col = 0; col < matrixB[0].length; col++) {
                    matrixB[row][col] = random.nextInt(2) % 2 != 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillMa(boolean[][] truthTable, int matrixLen) {
        for (int row = 0; row < matrixB.length; row++) {
            for (int col = 0; col < matrixB[0].length; col++) {
                matrixA[row][col] = truthTable[Math.floorMod((row - r), (matrixLen))][Math.floorMod((col - s), (matrixLen))] != matrixB[row][col];
            }
        }
    }

}
