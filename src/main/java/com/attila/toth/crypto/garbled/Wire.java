package com.attila.toth.crypto.garbled;

public class Wire {

    private String k0, k1;

    public Wire(String k0, String k1) {
        this.k0 = k0;
        this.k1 = k1;
    }

    String k(int i) {
        return i == 0 ? k0 : k1;
    }
}
