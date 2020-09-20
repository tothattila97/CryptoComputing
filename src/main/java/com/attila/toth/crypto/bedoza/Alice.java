package com.attila.toth.crypto.bedoza;

import com.attila.toth.crypto.BloodType;

import java.security.SecureRandom;

public class Alice extends Party {

    private boolean ua;
    private boolean va;
    private boolean wa;
    public boolean[] xbs;
    boolean output;

    public Alice(int numberOfLayers, int numberOfWires, BloodType bloodType) {
        this.bloodType = bloodType;
        this.numberOfLayers = numberOfLayers;
        this.numberOfWires = numberOfWires;
        this.circuit = new boolean[numberOfLayers][numberOfWires];
    }

    @Override
    public void initInputWires() {
        xbs = new boolean[3];

        for (int i = 0; i < 3; i++){
            xbs[i] = random.nextBoolean();
        }

        circuit[0][0] = (bloodType.binary.charAt(0) == "0".charAt(0)) == xbs[0];
        circuit[0][2] = (bloodType.binary.charAt(1) == "0".charAt(0)) == xbs[1];
        circuit[0][4] = (bloodType.binary.charAt(2) == "0".charAt(0)) == xbs[2];
    }

    public void setBobInputWires(boolean[] bobInputs) {
        circuit[0][1] = bobInputs[0];
        circuit[0][3] = bobInputs[1];
        circuit[0][5] = bobInputs[2];
    }

    @Override
    public void computeValues() {

    }

    public void setTripletFromDealer(boolean ua, boolean va, boolean wa){
        this.ua = ua;
        this.va = va;
        this.wa = wa;
    }

    public boolean isUa() {
        return ua;
    }

    public void setUa(boolean ua) {
        this.ua = ua;
    }

    public boolean isVa() {
        return va;
    }

    public void setVa(boolean va) {
        this.va = va;
    }

    public boolean isWa() {
        return wa;
    }

    public void setWa(boolean wa) {
        this.wa = wa;
    }

}
