package com.attila.toth.crypto.homomorphic;

import com.attila.toth.crypto.BloodType;

import java.math.BigInteger;

public class Bob {

    private BigInteger donorA;
    private BigInteger donorB;
    private BigInteger donorR;

    public Bob(BloodType bloodtype) {
        donorA = new BigInteger(String.format("%s", bloodtype.binary.charAt(0)));
        donorB = new BigInteger(String.format("%s", bloodtype.binary.charAt(1)));
        donorR = new BigInteger(String.format("%s", bloodtype.binary.charAt(2)));
    }

    public BigInteger transfer(BigInteger[] m1) {
        BigInteger receiverA = m1[0];
        BigInteger receiverB = m1[1];
        BigInteger receiverR = m1[2];
        BigInteger one = new BigInteger("1");

        // Boolean formula to compute the compatibility based on donor and receiver blood
        // Same as in the first assignment and BloodTypeHelper boolFormula function
        return one.add(((one.add(receiverA)).multiply(donorA)))
                .multiply((one.add((one.add(receiverB)).multiply(donorB))))
                .multiply((one.add((one.add(receiverR)).multiply(donorR))));
    }
}
