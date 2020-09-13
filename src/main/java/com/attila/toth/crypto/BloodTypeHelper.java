package com.attila.toth.crypto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BloodTypeHelper {

    private BloodTypeHelper() {
    }

    static List<BloodType> getCompatibleBloodTypesFromLookup(BloodType donor) {
        switch (donor) {
            case ZERO_POSITIVE:
                return Stream.of(BloodType.ZERO_POSITIVE, BloodType.A_POSITIVE, BloodType.B_POSITIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case ZERO_NEGATIVE:
                return Stream.of(BloodType.ZERO_POSITIVE, BloodType.ZERO_NEGATIVE, BloodType.B_POSITIVE, BloodType.B_NEGATIVE, BloodType.A_POSITIVE, BloodType.A_NEGATIVE, BloodType.AB_POSITIVE, BloodType.AB_NEGATIVE).collect(Collectors.toList());
            case A_POSITIVE:
                return Stream.of(BloodType.A_POSITIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case A_NEGATIVE:
                return Stream.of(BloodType.AB_NEGATIVE, BloodType.A_POSITIVE, BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case B_POSITIVE:
                return Stream.of(BloodType.B_POSITIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case B_NEGATIVE:
                return Stream.of(BloodType.B_NEGATIVE, BloodType.B_POSITIVE, BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            case AB_POSITIVE:
                return Stream.of(BloodType.AB_POSITIVE).collect(Collectors.toList());
            case AB_NEGATIVE:
                return Stream.of(BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE).collect(Collectors.toList());
            default:
                return Collections.emptyList();
        }
    }

    /**
     * @param donor    binary blood type of donor
     * @param patience binary blood type of patience
     * @return Bool formula: output = A'B'F' + A'EF' + B'DF' + DEF' + A'B'C + A'CE + B'CD + CDE
     */
    public static boolean boolFormula(String donor, String patience) {
        return orGate(Stream.of(
                andGate(notGate(donor.charAt(0)), notGate(donor.charAt(1)), notGate(patience.charAt(2))), // A'B'F'
                andGate(notGate(donor.charAt(0)), patience.charAt(1) == '1', notGate(patience.charAt(2))), // A'EF'
                andGate(notGate(donor.charAt(1)), patience.charAt(0) == '1', notGate(patience.charAt(2))), // B'DF'
                andGate(patience.charAt(0) == '1', patience.charAt(1) == '1', notGate(patience.charAt(2))), // DEF'
                andGate(notGate(donor.charAt(0)), notGate(donor.charAt(1)), donor.charAt(2) == '1'), // A'B'C
                andGate(notGate(donor.charAt(0)), donor.charAt(2) == '1', patience.charAt(1) == '1'), // A'CE
                andGate(notGate(donor.charAt(1)), donor.charAt(2) == '1', patience.charAt(0) == '1'), //  B'CD
                andGate(donor.charAt(2) == '1', patience.charAt(0) == '1', patience.charAt(1) == '1') // CDE
        ).collect(Collectors.toList()));
    }

    private static boolean andGate(boolean input1, boolean input2, boolean input3) {
        return input1 && input2 && input3;
    }

    private static boolean orGate(List<Boolean> input) {
        return input.contains(Boolean.TRUE);
    }

    private static boolean notGate(char c) {
        return c != '1';
    }

    static BloodType convertLabelToEnum(String label) {
        if (label.equals(BloodType.ZERO_NEGATIVE.label)) return BloodType.ZERO_NEGATIVE;
        if (label.equals(BloodType.ZERO_POSITIVE.label)) return BloodType.ZERO_POSITIVE;
        if (label.equals(BloodType.A_NEGATIVE.label)) return BloodType.A_NEGATIVE;
        if (label.equals(BloodType.A_POSITIVE.label)) return BloodType.A_POSITIVE;
        if (label.equals(BloodType.B_NEGATIVE.label)) return BloodType.B_NEGATIVE;
        if (label.equals(BloodType.B_POSITIVE.label)) return BloodType.B_POSITIVE;
        if (label.equals(BloodType.AB_NEGATIVE.label)) return BloodType.AB_NEGATIVE;
        return BloodType.AB_POSITIVE;
    }

}
