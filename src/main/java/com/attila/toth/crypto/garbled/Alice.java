package com.attila.toth.crypto.garbled;

import com.attila.toth.crypto.BloodType;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Alice extends Party {

    private Circuit garbledCircuit;
    public boolean output;

    public Alice(BloodType bloodType) {
        super(bloodType);
        this.X = new String[3];
        garbledCircuit = new Circuit();
    }

    public boolean isOutput() {
        return output;
    }

    public void setOutput(boolean output) {
        this.output = output;
    }

    public Circuit getGarbledCircuit() {
        return garbledCircuit;
    }

    /**
     * Receive the value Z from Bob
     */
    public void receiveZFromBob(String z) {
        this.Z = z;
    }

    /**
     *  Alice compute the encoding of X from the garbled circuit wire values
     **/
    public String[] computeEncodingX() {
        ArrayList<Wire> temp = new ArrayList<>();
        temp.add(garbledCircuit.encoding.get(0));
        temp.add(garbledCircuit.encoding.get(2));
        temp.add(garbledCircuit.encoding.get(4));
        IntStream.range(0, bloodType.binary.toCharArray().length).forEachOrdered(i -> {
            if (bloodType.binary.toCharArray()[i] == '1')
                X[i] = temp.get(i).k(1);
            else
                X[i] = temp.get(i).k(0);
        });
        return X;
    }

    /**
     * ALice compute the output locally
     **/
    public void computeOutput() {
        if (Z.equals(garbledCircuit.d.k(1))) {
            setOutput(true);
            return;
        }
        if (Z.equals(garbledCircuit.d.k(0))) setOutput(false);
    }
}
