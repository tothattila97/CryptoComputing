package com.attila.toth.crypto.tests;

import com.attila.toth.crypto.BloodType;

/**
 * This class called Party encapsulates the common fields for Alice and Bob
 */
public abstract class Party {
    protected BloodType bloodtype;
    protected int n;
    public int u;
    public int v;
    public boolean zb;
}
