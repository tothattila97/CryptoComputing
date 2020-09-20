package com.attila.toth.crypto.bedoza;

import java.security.SecureRandom;

public class Dealer {

    // Alice and Bob uniformly random triplets
    public boolean ua;
    public boolean va;
    public boolean wa;
    public boolean ub;
    public boolean vb;
    public boolean wb;
    private SecureRandom random = new SecureRandom();

    public Dealer(){

    }

    public void generateTriplets() {
        ua = random.nextInt(2) != 0;
        va = random.nextInt(2) != 0;
        wa = random.nextInt(2) != 0;
        ub = random.nextInt(2) != 0;
        vb = random.nextInt(2) != 0;
        wb = ((ua ^ ub) & (va ^ vb)) ^ wa;
    }
}
