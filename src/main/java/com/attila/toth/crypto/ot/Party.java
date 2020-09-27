package com.attila.toth.crypto.ot;

import com.attila.toth.crypto.BloodType;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * This class called Party encapsulates the common fields and methods for Alice and Bob for OT protocol
 */
abstract class Party {
    BigInteger g;  // group
    BigInteger p;  // In the classic DDH assumption p = 2q + 1
    BigInteger q;
    BigInteger[] pks = new BigInteger[8];  // public keys
    BigInteger[][] encryptedMessages;
    SecureRandom random = new SecureRandom();
    BloodType bloodType;

    public BigInteger getG() {
        return g;
    }

    public void setG(BigInteger g) {
        this.g = g;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger[] getPks() {
        return pks;
    }

    public void setPks(BigInteger[] pks) {
        this.pks = pks;
    }

    public void setEncryptedMessages(BigInteger[][] encryptedMessages) {
        this.encryptedMessages = encryptedMessages;
    }

    public BigInteger[][] getEncryptedMessages() {
        return encryptedMessages;
    }
}
