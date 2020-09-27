package com.attila.toth.crypto.ot;

import com.attila.toth.crypto.BloodType;
import com.attila.toth.crypto.BloodTypeHelper;

import java.math.BigInteger;

public class Bob extends Party{

    private boolean[] inputMessages = new boolean[8];

    public Bob(BloodType bloodType) {
        this.bloodType = bloodType;
        for (int i = 0 ;i < inputMessages.length; i++) {
            // fill the input messages array with the "truthtable's" given row
            inputMessages[i] = BloodTypeHelper.boolFormula(this.bloodType.binary, BloodType.values()[i].binary);
        }
    }

    public void generateEncryptedMessages() {
        encryptedMessages = new BigInteger[8][2];  // to store as C = (g^r, m*h^r)
        for (int i = 0; i < inputMessages.length; i++) {
            BigInteger r = new BigInteger(q.bitLength(), random);
            while (r.compareTo(q) >= 0)
                r = new BigInteger(q.bitLength(), random);
            if (inputMessages[i]) {  // if the input message true
                encryptedMessages[i][0] = g.modPow(r, p);  // c1
                encryptedMessages[i][1] = pks[i].modPow(r, p).multiply(new BigInteger("1")); // c2  1: true
            } else { // if the input message false
                encryptedMessages[i][0] = g.modPow(r, p);  // c1
                encryptedMessages[i][1] = pks[i].modPow(r, p).multiply(new BigInteger("-1")); // c2  -1: false
            }
        }
    }
}
