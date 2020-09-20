package com.attila.toth.crypto.ottt;

import com.attila.toth.crypto.BloodType;

public class Alice extends Party {
    private boolean[][] matrixA;
    private int r;

    public Alice(boolean[][] matrixA, int r, int n, BloodType bloodtype) {
        this.r = r;
        this.n = n;
        this.bloodtype = bloodtype;
        this.matrixA = matrixA;
    }

    /**
     * Calculate the u value locally
     */
    public void computeValues() {
        u = Math.floorMod(7 - bloodtype.decimal + r, (int) Math.pow(2, n));
    }

    /**
     * @return Returns with the calculated output, called z in the protocol specification
     */
    public boolean computeOutput() {
        return matrixA[u][v] ^ zb;
    }
}
