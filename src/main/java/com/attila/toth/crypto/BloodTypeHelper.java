package com.attila.toth.crypto;

import com.attila.toth.crypto.bedoza.Alice;
import com.attila.toth.crypto.bedoza.Bob;
import com.attila.toth.crypto.bedoza.Dealer;
import com.attila.toth.crypto.garbled.Circuit;
import com.attila.toth.crypto.garbled.Wire;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BloodTypeHelper {

    private BloodTypeHelper() {
    }

    static List<BloodType> getCompatibleBloodTypesFromLookup(BloodType donor) {
        switch (donor) {
            case ZERO_POSITIVE:
                return Stream.of(BloodType.ZERO_POSITIVE, BloodType.A_POSITIVE, BloodType.B_POSITIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case ZERO_NEGATIVE:
                return Stream.of(BloodType.ZERO_POSITIVE, BloodType.ZERO_NEGATIVE, BloodType.B_POSITIVE, BloodType.B_NEGATIVE, BloodType.A_POSITIVE, BloodType.A_NEGATIVE, BloodType.AB_POSITIVE, BloodType.AB_NEGATIVE).collect(Collectors.toList());
            case A_POSITIVE:
                return Stream.of(BloodType.A_POSITIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case A_NEGATIVE:
                return Stream.of(BloodType.AB_NEGATIVE, BloodType.A_POSITIVE, BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case B_POSITIVE:
                return Stream.of(BloodType.B_POSITIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case B_NEGATIVE:
                return Stream.of(BloodType.B_NEGATIVE, BloodType.B_POSITIVE, BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case AB_POSITIVE:
                return Stream.of(BloodType.AB_POSITIVE).collect(Collectors.toList());
            case AB_NEGATIVE:
                return Stream.of(BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    /**
     * @param donor    binary blood type of donor
     * @param patience binary blood type of patience
     * @return Bool formula: output = A'B'F' + A'EF' + B'DF' + DEF' + A'B'C + A'CE + B'CD + CDE
     */
    public static boolean boolFormula(String donor, String patience) {
        return (!(donor.charAt(0) == '1' && (patience.charAt(0) != '1'))) &&
                (!(donor.charAt(1) == '1' && (patience.charAt(1) != '1'))) &&
                (!(donor.charAt(2) == '1' && (patience.charAt(2) != '1')));

        /*orGate(Stream.of(
                andGate(notGate(donor.charAt(0)), notGate(donor.charAt(1)), notGate(patience.charAt(2))), // A'B'F'
                andGate(notGate(donor.charAt(0)), patience.charAt(1) == '1', notGate(patience.charAt(2))), // A'EF'
                andGate(notGate(donor.charAt(1)), patience.charAt(0) == '1', notGate(patience.charAt(2))), // B'DF'
                andGate(patience.charAt(0) == '1', patience.charAt(1) == '1', notGate(patience.charAt(2))), // DEF'
                andGate(notGate(donor.charAt(0)), notGate(donor.charAt(1)), donor.charAt(2) == '1'), // A'B'C
                andGate(notGate(donor.charAt(0)), donor.charAt(2) == '1', patience.charAt(1) == '1'), // A'CE
                andGate(notGate(donor.charAt(1)), donor.charAt(2) == '1', patience.charAt(0) == '1'), //  B'CD
                andGate(donor.charAt(2) == '1', patience.charAt(0) == '1', patience.charAt(1) == '1') // CDE
        ).collect(Collectors.toList()));*/
    }

    private static boolean andGate(boolean input1, boolean input2, boolean input3) {
        return input1 && input2 && input3;
    }

    private static boolean orGate(List<Boolean> input) {
        return input.contains(Boolean.TRUE);
    }

    private static boolean notGate(char c) {
        return c != '1';
    }

    static BloodType convertLabelToEnum(String label) {
        if (label.equals(BloodType.ZERO_NEGATIVE.label)) return BloodType.ZERO_NEGATIVE;
        if (label.equals(BloodType.ZERO_POSITIVE.label)) return BloodType.ZERO_POSITIVE;
        if (label.equals(BloodType.A_NEGATIVE.label)) return BloodType.A_NEGATIVE;
        if (label.equals(BloodType.A_POSITIVE.label)) return BloodType.A_POSITIVE;
        if (label.equals(BloodType.B_NEGATIVE.label)) return BloodType.B_NEGATIVE;
        if (label.equals(BloodType.B_POSITIVE.label)) return BloodType.B_POSITIVE;
        if (label.equals(BloodType.AB_NEGATIVE.label)) return BloodType.AB_NEGATIVE;
        return BloodType.AB_POSITIVE;
    }

    static void andOfTwoWires(Alice alice, Bob bob, Dealer dealer, int layer, int wire) {
        dealer.generateTriplets();
        alice.setTripletFromDealer(dealer.ua, dealer.va, dealer.wa);
        bob.setTripletFromDealer(dealer.ub, dealer.vb, dealer.wb);

        // Run sub protocol: compute d = x XOR u and e = y XOR v
        alice.setDa(alice.getCircuit()[layer - 1][wire] ^ alice.isUa());
        alice.setEa(alice.getCircuit()[layer - 1][wire + 1] ^ alice.isVa());
        bob.setDb(bob.getCircuit()[layer - 1][wire] ^ bob.isUb());
        bob.setEb(bob.getCircuit()[layer - 1][wire + 1] ^ bob.isVb());

        // Run sub protocol: open d and e values
        alice.setDb(bob.isDb());
        alice.setEb(bob.isEb());
        bob.setDa(alice.isDa());
        bob.setEa(alice.isEa());

        alice.setD(alice.isDa() ^ alice.isDb());
        alice.setE(alice.isEa() ^ alice.isEb());
        bob.setD(bob.isDa() ^ bob.isDb());
        bob.setE(bob.isEa() ^ bob.isEb());

        //Run sub protocol: calculate z value : z = [w] XOR e AND [x] XOR d AND [y] XOR e and d
        alice.circuit[layer][wire] = alice.isWa() ^ (alice.isE() & alice.circuit[layer - 1][wire]) ^ (alice.isD() & alice.circuit[layer - 1][wire + 1]) ^ (alice.isE() & alice.isD());
        bob.circuit[layer][wire] = bob.isWb() ^ (bob.isE() & bob.circuit[layer - 1][wire]) ^ (bob.isD() & bob.circuit[layer - 1][wire + 1]);  //^ (bob.isE() & bob.isD())
    }

    public static void generateKeysForInputWires(ArrayList<Wire> wires) {
        SecureRandom rand = new SecureRandom(SecureRandom.getSeed(256));
        for (int j = 0; j < Circuit.WIRE_COUNT; j++) {
            StringBuilder k0 = new StringBuilder();
            for (int i = 0; i < 128; i++) {
                k0.append(rand.nextInt(2));
            }
            StringBuilder k1 = new StringBuilder();
            for (int i = 0; i < 128; i++) {
                k1.append(rand.nextInt(2));
            }
            wires.add(new Wire(k0.toString(), k1.toString()));
        }
    }

}
