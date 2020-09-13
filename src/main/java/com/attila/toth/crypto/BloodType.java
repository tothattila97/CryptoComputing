package com.attila.toth.crypto;

public enum BloodType {
    ZERO_POSITIVE("0+","001", 0),
    ZERO_NEGATIVE("0-","000", 1),
    A_POSITIVE("A+","101", 2),
    A_NEGATIVE("A-","100", 3),
    B_POSITIVE("B+","011", 4),
    B_NEGATIVE("B-","010", 5),
    AB_POSITIVE("AB+","111", 6),
    AB_NEGATIVE("AB-","110", 7);

    public final String label;
    public final String binary;
    public final int decimal;

    /**
     * @param label defines the blood type
     * @param binary defines the blood type also just in binary format
     */
    BloodType(String label, String binary, int decimal) {
        this.label = label;
        this.binary = binary;
        this.decimal = decimal;
    }
}