package com.attila.toth.crypto.bedoza;

import com.attila.toth.crypto.BloodType;

import java.security.SecureRandom;

/**
 * This class called Party encapsulates the common fields and methods for Alice and Bob
 */
abstract class Party {
    BloodType bloodType;



    int numberOfLayers;
    int numberOfWires;
    public boolean[][] circuit;
    SecureRandom random = new SecureRandom();

    private boolean d;
    private boolean da;
    private boolean db;
    private boolean e;
    private boolean ea;
    private boolean eb;

    public boolean[][] getCircuit() {
        return circuit;
    }

    public void setCircuit(boolean[][] circuit) {
        this.circuit = circuit;
    }

    public boolean isD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public boolean isDa() {
        return da;
    }

    public void setDa(boolean da) {
        this.da = da;
    }

    public boolean isDb() {
        return db;
    }

    public void setDb(boolean db) {
        this.db = db;
    }

    public boolean isE() {
        return e;
    }

    public void setE(boolean e) {
        this.e = e;
    }

    public boolean isEa() {
        return ea;
    }

    public void setEa(boolean ea) {
        this.ea = ea;
    }

    public boolean isEb() {
        return eb;
    }

    public void setEb(boolean eb) {
        this.eb = eb;
    }
    public int getNumberOfLayers() {
        return numberOfLayers;
    }

    public int getNumberOfWires() {
        return numberOfWires;
    }
    Party() {
    }

    abstract void initInputWires();

    public void xor(int layer, int wire) {
        circuit[layer][wire] = circuit[layer - 1][wire] ^ circuit[layer - 1][wire + 1];
    }

    /*public void xorWithConstant(int layer, int wire, boolean c) {
        circuit[layer][wire] = circuit[layer - 1][wire] ^ c;
    }*/

    public void not(int layer, int wire) {
        circuit[layer][wire] = !circuit[layer - 1][wire];
    }

    /*public void andWithConstant(int layer, int wire, boolean c) {
        circuit[layer][wire] = circuit[layer - 1][wire] && c;
    }*/

}
