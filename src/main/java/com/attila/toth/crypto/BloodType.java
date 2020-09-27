package com.attila.toth.crypto;

public enum BloodType {
    ZERO_NEGATIVE("0-","000", 1,0),
    ZERO_POSITIVE("0+","001", 0,1),
    A_NEGATIVE("A-","100", 3,2),
    A_POSITIVE("A+","101", 2,3),
    B_NEGATIVE("B-","010", 5,4),
    B_POSITIVE("B+","011", 4,5),
    AB_NEGATIVE("AB-","110", 7,6),
    AB_POSITIVE("AB+","111", 6,7);

    public final String label;
    public final String binary;
    public final int decimal;
    public final int val;

    /**
     * @param label defines the blood type
     * @param binary defines the blood type also just in binary format
     */
    BloodType(String label, String binary, int decimal, int val) {
        this.label = label;
        this.binary = binary;
        this.decimal = decimal;
        this.val = val;
    }
}