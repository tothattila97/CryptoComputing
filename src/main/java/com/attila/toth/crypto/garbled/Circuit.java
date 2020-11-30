package com.attila.toth.crypto.garbled;

import com.attila.toth.crypto.BloodTypeHelper;

import java.util.ArrayList;

public class Circuit {

    private static final int INPUT_WIRES_COUNT = 6;
    public static final int WIRE_COUNT = 21;

    private ArrayList<Wire> wires = new ArrayList<>();
    private ArrayList<Gate> gates = new ArrayList<>();
    public ArrayList<Wire> encoding;
    Wire d;

    Circuit() {
        BloodTypeHelper.generateKeysForInputWires(wires);
        d = wires.get(WIRE_COUNT - 1);
        encoding = getWireValues();

        createFirstNotGates();
        wires.set(7, wires.get(1));
        wires.set(9, wires.get(3));
        wires.set(11, wires.get(5));
        createFirstAndGates();
        createSecondNotGates();
        createSecondAndGate();
        wires.set(19, wires.get(17));
        createThirdAndGate();
    }

    private ArrayList<Wire> getWireValues() {
        ArrayList<Wire> result = new ArrayList<>();
        for (int i = 0; i < INPUT_WIRES_COUNT; i++) {
            result.add(wires.get(i));
        }
        return result;
    }

    public ArrayList<Gate> getGates() {
        return gates;
    }

    // Set the input and output wire for the first level NOT gates
    private void createFirstNotGates() {
        gates.add(new Gate(wires.get(0), wires.get(6)));
        gates.add(new Gate(wires.get(2), wires.get(8)));
        gates.add(new Gate(wires.get(4), wires.get(10)));
    }

    // Set the two input wires and output wire for the first level AND gates
    private void createFirstAndGates() {
        gates.add(new Gate(wires.get(6), wires.get(7), wires.get(12)));
        gates.add(new Gate(wires.get(8), wires.get(9), wires.get(13)));
        gates.add(new Gate(wires.get(10), wires.get(11), wires.get(14)));
    }

    // Set the input and output wire for the second level NOT gates
    private void createSecondNotGates() {
        gates.add(new Gate(wires.get(12), wires.get(15)));
        gates.add(new Gate(wires.get(13), wires.get(16)));
        gates.add(new Gate(wires.get(14), wires.get(17)));
    }

    // Set the two input wires and output wire for the second level AND gates
    private void createSecondAndGate() {
        gates.add(new Gate(wires.get(15), wires.get(16), wires.get(18)));
    }

    private void createThirdAndGate() {
        gates.add(new Gate(wires.get(18), wires.get(19), wires.get(20)));
    }
}
