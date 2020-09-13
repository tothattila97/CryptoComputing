package com.attila.toth.crypto;

import com.attila.toth.crypto.tests.Party;

public class Alice extends Party {
    private boolean[][] matrixA;
    private int r;

    Alice(boolean[][] matrixA, int r, int n, BloodType bloodtype) {
        this.bloodtype = bloodtype;
        this.matrixA = matrixA;
        this.r = r;
        this.n = n;
    }

    /**
     * Calculate the u value locally
     */
    void calculateValue() {
        u = Math.floorMod(7 - bloodtype.decimal + r, (int) Math.pow(2, n));
    }

    /**
     * @return Returns with the calculated output, called z in the protocol specification
     */
    boolean calculateOutput() {
        return matrixA[u][v] ^ zb;
    }
}
