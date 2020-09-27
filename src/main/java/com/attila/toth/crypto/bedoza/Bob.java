package com.attila.toth.crypto.bedoza;

import com.attila.toth.crypto.BloodType;

import java.security.SecureRandom;

public class Bob extends Party {

    private boolean ub;
    private boolean vb;
    private boolean wb;

    public boolean[] xas;

    public Bob (int numberOfLayers, int numberOfWires, BloodType bloodType){
        this.bloodType = bloodType;
        this.numberOfLayers = numberOfLayers;
        this.numberOfWires = numberOfWires;
        this.circuit = new boolean[numberOfLayers][numberOfWires];
    }

    @Override
    public void initInputWires() {
        xas = new boolean[3];

        for (int i = 0; i < 3; i++){
            xas[i] = random.nextBoolean();
        }

        circuit[0][1] = (bloodType.binary.charAt(0) == '1') == xas[0];
        circuit[0][3] = (bloodType.binary.charAt(1) == '1') == xas[1];
        circuit[0][5] = (bloodType.binary.charAt(2) == '1') == xas[2];
    }

    public void setAliceInputWires(boolean[] aliceInputs) {
        circuit[0][0] = aliceInputs[0];
        circuit[0][2] = aliceInputs[1];
        circuit[0][4] = aliceInputs[2];
    }

    public void setTripletFromDealer(boolean ub, boolean vb, boolean wb){
        this.ub = ub;
        this.vb = vb;
        this.wb = wb;
    }

    public boolean isUb() {
        return ub;
    }

    public void setUb(boolean ub) {
        this.ub = ub;
    }

    public boolean isVb() {
        return vb;
    }

    public void setVb(boolean vb) {
        this.vb = vb;
    }

    public boolean isWb() {
        return wb;
    }

    public void setWb(boolean wb) {
        this.wb = wb;
    }
}
