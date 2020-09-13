package com.attila.toth.crypto;

public class Bob extends Party {
    private boolean[][] matrixB;
    private int s;

    Bob(boolean[][] matrixB, int s, int n, BloodType bloodtype) {
        this.s = s;
        this.n = n;
        this.bloodtype = bloodtype;
        this.matrixB = matrixB;
    }

    /**
     * Calculate v and zb locally in order to Bob can send it to ALice
     */
    void computeValues() {
        v = Math.floorMod(7 - bloodtype.decimal + s, (int) Math.pow(2, n));
        zb = matrixB[u][v];
    }
}
