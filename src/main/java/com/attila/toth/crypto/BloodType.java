package com.attila.toth.crypto;

public enum BloodType {
    ZERO_POSITIVE("0+","000", 0),
    ZERO_NEGATIVE("0-","001", 1),
    A_POSITIVE("A+","010", 2),
    A_NEGATIVE("A-","011", 3),
    B_POSITIVE("B+","100", 4),
    B_NEGATIVE("B-","101", 5),
    AB_POSITIVE("AB+","110", 6),
    AB_NEGATIVE("AB-","111", 7);

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