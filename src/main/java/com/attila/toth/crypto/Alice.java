package com.attila.toth.crypto;

public class Alice {

    private boolean[][] matrixA;
    private BloodType bloodtype;
    private int r;
    private int n;
    int u;
    int v;
    boolean zb;

    Alice(boolean[][] matrixA, int r, int n, BloodType bloodtype) {
        this.bloodtype = bloodtype;
        this.matrixA = matrixA;
        this.r = r;
        this.n = n;
    }

    void calculateValue() {
        u = Math.floorMod(7 - bloodtype.decimal + r, (int) Math.pow(2, n));
    }

    boolean calculateOutput() {
        return matrixA[u][v] ^ zb;
    }
}
