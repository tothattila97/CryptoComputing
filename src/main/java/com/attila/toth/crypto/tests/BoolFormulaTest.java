package com.attila.toth.crypto.tests;

import com.attila.toth.crypto.BloodType;
import com.attila.toth.crypto.BloodTypeHelper;
import junit.framework.TestCase;
import org.junit.Test;
import static junit.framework.TestCase.*;

public class BoolFormulaTest {

    @Test
    public void whenDonorZeroPositive() {
        TestCase.assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.A_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.A_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.B_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.AB_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.ZERO_POSITIVE.binary, BloodType.AB_NEGATIVE.binary));
    }

    @Test
    public void whenDonorZeroNegative() {
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.A_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.A_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.B_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.AB_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.ZERO_NEGATIVE.binary, BloodType.AB_NEGATIVE.binary));
    }

    @Test
    public void whenDonorAPositive() {
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.A_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.A_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.B_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.AB_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_POSITIVE.binary, BloodType.AB_NEGATIVE.binary));
    }

    @Test
    public void whenDonorANegative() {
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.A_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.A_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.B_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.AB_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.A_NEGATIVE.binary, BloodType.AB_NEGATIVE.binary));
    }

    @Test
    public void whenDonorBPositive() {
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.A_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.A_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.B_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.AB_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_POSITIVE.binary, BloodType.AB_NEGATIVE.binary));
    }

    @Test
    public void whenDonorBNegative() {
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.A_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.A_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.B_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.AB_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.B_NEGATIVE.binary, BloodType.AB_NEGATIVE.binary));
    }

    @Test
    public void whenDonorAbPositive() {
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.A_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.A_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.B_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.AB_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_POSITIVE.binary, BloodType.AB_NEGATIVE.binary));
    }

    @Test
    public void whenDonorAbNegative() {
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.ZERO_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.ZERO_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.A_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.A_NEGATIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.B_POSITIVE.binary));
        assertFalse(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.B_NEGATIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.AB_POSITIVE.binary));
        assertTrue(BloodTypeHelper.boolFormula(BloodType.AB_NEGATIVE.binary, BloodType.AB_NEGATIVE.binary));
    }
}
