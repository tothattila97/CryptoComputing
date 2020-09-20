package com.attila.toth.crypto;

import com.attila.toth.crypto.ottt.Alice;
import com.attila.toth.crypto.ottt.Bob;
import com.attila.toth.crypto.ottt.Dealer;
import com.attila.toth.crypto.tests.BedozaProtocolTest;
import com.attila.toth.crypto.tests.BoolFormulaTest;
import com.attila.toth.crypto.tests.OTTTProtocolTest;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                JUnitCore junit = new JUnitCore();
                junit.addListener(new TextListener(System.out));
                Result run = junit.run(BoolFormulaTest.class, OTTTProtocolTest.class, BedozaProtocolTest.class);
                resultReport(run);
                return;
            } else if (args.length != 3) {
                System.err.println("A switch and the two blood types are required! Separate them with a single space!");
                return;
            }
            String function = args[0];
            String donor = args[1];
            String patience = args[2];

            BloodType donorBloodType = BloodTypeHelper.convertLabelToEnum(donor);
            BloodType patienceBloodType = BloodTypeHelper.convertLabelToEnum(patience);

            boolean compatibility = false;
            switch (function) {
                case "-lookup":
                    compatibility = Objects.requireNonNull(BloodTypeHelper.getCompatibleBloodTypesFromLookup(donorBloodType)).contains(patienceBloodType);
                    break;
                case "-bool":
                    String donorBinary = donorBloodType.binary;
                    String patienceBinary = patienceBloodType.binary;
                    compatibility = BloodTypeHelper.boolFormula(donorBinary.substring(donorBinary.length() - 3), patienceBinary.substring(patienceBinary.length() - 3));
                    break;
                case "-ottt":
                    boolean[][] truthTable = new boolean[8][8];
                    initTruthTable(truthTable);
                    ImmutableTriple<Dealer, Alice, Bob> participants = initParticipantsForOTTT(truthTable, patienceBloodType, donorBloodType);
                    compatibility = protocolOTTT(participants.middle, participants.right);
                    break;
                case "-bedoza":
                    compatibility = protocolBEDOZA(patienceBloodType, donorBloodType);
                    break;
                default:
                    break;
            }

            System.out.println("Compatibility: " + compatibility);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ImmutableTriple<Dealer, Alice, Bob> initParticipantsForOTTT(boolean[][] truthTable, BloodType patienceBloodType, BloodType donorBloodType) {
        Dealer dealer = new Dealer(truthTable, 3); // n=3 because we store the blood types with 3 bits

        Alice alice = new Alice(dealer.matrixA, dealer.r, dealer.n, patienceBloodType);
        Bob bob = new Bob(dealer.matrixB, dealer.s, dealer.n, donorBloodType);
        return ImmutableTriple.of(dealer, alice, bob);
    }

    private static void resultReport(Result result) {
        System.out.println("Finished. Result: Failures: " +
                result.getFailureCount() + ". Ignored: " +
                result.getIgnoreCount() + ". Tests run: " +
                result.getRunCount() + ". Time: " +
                result.getRunTime() + "ms.");
    }

    public static void initTruthTable(boolean[][] truthTable) {
        //StringBuilder sBuilder = new StringBuilder("\n");
        for (int i = 0; i < 8; i++) {
            for (int k = 7; k >= 0; k--) {
                boolean b = BloodTypeHelper.boolFormula(BloodType.values()[i].binary, BloodType.values()[k].binary);
                truthTable[i][k] = b;
            }
        }
        //System.out.println(sBuilder);
    }

    public static boolean protocolOTTT(Alice alice, Bob bob) {
        alice.computeValues();
        bob.u = alice.u;
        bob.computeValues();
        alice.v = bob.v;
        alice.zb = bob.zb;
        return alice.computeOutput();
    }

    public static boolean protocolBEDOZA(BloodType patienceBloodType, BloodType donorBloodType) {
        com.attila.toth.crypto.bedoza.Dealer dealer = new com.attila.toth.crypto.bedoza.Dealer();
        dealer.generateTriplets();

        com.attila.toth.crypto.bedoza.Alice alice = new com.attila.toth.crypto.bedoza.Alice(6,6,patienceBloodType);
        com.attila.toth.crypto.bedoza.Bob bob = new com.attila.toth.crypto.bedoza.Bob(6,6,donorBloodType);

        // Initialize Alice and Bob wires
        int layer = 0;
        alice.initInputWires();
        bob.initInputWires();
        alice.setBobInputWires(bob.xas);
        bob.setAliceInputWires(alice.xbs);

        // Compute the AND
        layer++;
        for (int i = 0; i< 6;i+=2){
            BloodTypeHelper.andOfTwoWires(alice,bob,dealer, layer,i);
        }

        // Copy the outputs to the upward wires
        layer++;
        alice.circuit[layer][0] = alice.circuit[layer-1][0];
        alice.circuit[layer][1] = alice.circuit[layer-1][2];
        alice.circuit[layer][2] = alice.circuit[layer-1][4];
        bob.circuit[layer][0] = bob.circuit[layer-1][0];
        bob.circuit[layer][1] = bob.circuit[layer-1][2];
        bob.circuit[layer][2] = bob.circuit[layer-1][4];


        // Compute NOT for all wire
        layer++;
        for (int i = 0; i < 3; i++){
            alice.not(layer,i);
            bob.not(layer,i);
        }

        // Layer 4 compute another AND, the rest copied to an upper wire
        layer++;
        BloodTypeHelper.andOfTwoWires(alice,bob,dealer,layer,0);
        alice.circuit[layer][1] = alice.circuit[layer-1][2];
        bob.circuit[layer][1] = bob.circuit[layer-1][2];

        // Layer 5 last AND calculation
        layer++;
        BloodTypeHelper.andOfTwoWires(alice,bob,dealer,layer,0);

        // Calculate output in Alice
        return alice.circuit[alice.getNumberOfLayers()-1][0] ^ bob.circuit[bob.getNumberOfLayers()-1][0];
    }
}
