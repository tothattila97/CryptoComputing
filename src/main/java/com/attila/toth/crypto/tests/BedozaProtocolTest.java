package com.attila.toth.crypto.tests;

import com.attila.toth.crypto.BloodType;
import com.attila.toth.crypto.BloodTypeHelper;
import com.attila.toth.crypto.Main;
import com.attila.toth.crypto.bedoza.Alice;
import com.attila.toth.crypto.bedoza.Bob;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class BedozaProtocolTest {

    @Test
    public void whenDonorZeroPositive() {
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.ZERO_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorZeroNegative() {
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.A_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.B_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.ZERO_NEGATIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorAPositive() {
        assertFalse(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorANegative() {
        assertFalse(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.A_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.A_NEGATIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorBPositive() {
        assertFalse(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorBNegative() {
        assertFalse(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.A_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.B_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.B_NEGATIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorAbPositive() {
        assertFalse(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.AB_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_POSITIVE, BloodType.AB_NEGATIVE));
    }

    @Test
    public void whenDonorAbNegative() {
        assertFalse(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.ZERO_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.ZERO_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.A_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.A_NEGATIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.B_POSITIVE));
        assertFalse(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.B_NEGATIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE));
        assertTrue(Main.protocolBEDOZA(BloodType.AB_NEGATIVE, BloodType.AB_NEGATIVE));
    }
}
