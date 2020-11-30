package com.attila.toth.crypto.garbled;

import com.attila.toth.crypto.BloodType;

/**
 * This class called Party encapsulates the common fields and methods for Alice and Bob for Yao protocol
 */
public abstract class Party {

    BloodType bloodType;
    String[] X;
    String[] Y;
    String Z;

    Party(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}
