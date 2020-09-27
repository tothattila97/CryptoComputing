package com.attila.toth.crypto.tests;

import com.attila.toth.crypto.BloodType;
import com.attila.toth.crypto.Main;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ObliviousTransferProtocolTest {

    @Test
    public void whenDonorZeroPositive() {
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.ZERO_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorZeroNegative() {
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.A_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.B_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.ZERO_NEGATIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorAPositive() {
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorANegative() {
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.A_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.A_NEGATIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorBPositive() {
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorBNegative() {
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.B_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.B_NEGATIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorAbPositive() {
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorAbNegative() {
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolObliviousTransfer(BloodType.AB_NEGATIVE, BloodType.AB_NEGATIVE));
    }
}
