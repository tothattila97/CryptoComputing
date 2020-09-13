package com.attila.toth.crypto;

public class Bob {
    private boolean[][] matrixB;
    private BloodType bloodtype;
    private int s;
    private int n;
    int u;
    int v;
    boolean zb;

    Bob(boolean[][] matrixB, int s, int n, BloodType bloodtype) {
        this.bloodtype = bloodtype;
        this.matrixB = matrixB;
        this.s = s;
        this.n = n;
    }

    void calculateValue() {
        v = Math.floorMod(7 - bloodtype.decimal + s, (int) Math.pow(2, n));
        zb = matrixB[u][v];
    }
}
