package com.attila.toth.crypto;

import com.attila.toth.crypto.BloodType;

/**
 * This class called Party encapsulates the common fields and methods for Alice and Bob
 */
abstract class Party {
    BloodType bloodtype;
    int n;
    int u;
    int v;
    boolean zb;

    abstract void computeValues();
}
