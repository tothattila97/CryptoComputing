package com.attila.toth.crypto.garbled;

import com.attila.toth.crypto.BloodType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Bob extends Party {

    private String[] Y = new String[3];

    public Bob(BloodType bloodType) {
        super(bloodType);
        //this.Y =
    }

    /**
     * Bob sends the value Z to ALice
     */
    public String sendZToALice() {
        return Z;
    }

    public void receiveXFromAlice(String[] x) {
        this.X = x;
    }

    public void evaluateCircuit(ArrayList<Gate> gates) {
        String w6 = gates.get(0).evaluate(X[0]);
        String w8 = gates.get(1).evaluate(X[1]);
        String w10 = gates.get(2).evaluate(X[2]);

        String w12 = gates.get(3).evaluate(w6 + Y[0]);
        String w13 = gates.get(4).evaluate(w8 + Y[1]);
        String w14 = gates.get(5).evaluate(w10 + Y[2]);

        String w15 = gates.get(6).evaluate(w12);
        String w16 = gates.get(7).evaluate(w13);
        String w17 = gates.get(8).evaluate(w14);

        String w18 = gates.get(9).evaluate(w15 + w16);

        Z = gates.get(10).evaluate(w18 + w17);
    }

    public void OT(List<Wire> wires) {
        ArrayList<Wire> temporary = new ArrayList<>();
        temporary.add(wires.get(1));
        temporary.add(wires.get(3));
        temporary.add(wires.get(5));
        IntStream.range(0, bloodType.binary.toCharArray().length).forEachOrdered(i -> {
            if (bloodType.binary.toCharArray()[i] == '1') Y[i] = temporary.get(i).k(1);
            else Y[i] = temporary.get(i).k(0);
        });
    }
}
