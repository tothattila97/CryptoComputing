package com.attila.toth.crypto.ottt;

import com.attila.toth.crypto.BloodType;

/**
 * This class called Party encapsulates the common fields and methods for Alice and Bob
 */
abstract class Party {
    BloodType bloodtype;
    int n;
    public int u;
    public int v;
    public boolean zb;

    abstract void computeValues();
}
