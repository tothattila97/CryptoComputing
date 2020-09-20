package com.attila.toth.crypto.tests;

import com.attila.toth.crypto.*;
import com.attila.toth.crypto.ottt.Alice;
import com.attila.toth.crypto.ottt.Bob;
import com.attila.toth.crypto.ottt.Dealer;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit4.class)
public class OTTTProtocolTest {

    private static boolean[][] truthTable = new boolean[8][8];

    @BeforeClass
    public static void setup() {
        Main.initTruthTable(truthTable);
    }

    @Test
    public void whenDonorZeroPositive() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.ZERO_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.ZERO_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.ZERO_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.ZERO_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.ZERO_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.ZERO_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.ZERO_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));
    }

    @Test
    public void whenDonorZeroNegative() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_POSITIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.ZERO_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));
    }

    @Test
    public void whenDonorAPositive() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_POSITIVE, BloodType.A_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.A_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.A_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.A_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.A_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.A_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.A_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.A_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));
    }

    @Test
    public void whenDonorANegative() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_POSITIVE, BloodType.A_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.A_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.A_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.A_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.A_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.A_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.A_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.A_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));
    }

    @Test
    public void whenDonorBPositive() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_POSITIVE, BloodType.B_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.B_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.B_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.B_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.B_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.B_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.B_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.B_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));
    }

    @Test
    public void whenDonorBNegative() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_POSITIVE, BloodType.B_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.B_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.B_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.B_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.B_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.B_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.B_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.B_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));
    }

    @Test
    public void whenDonorAbPositive() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_POSITIVE, BloodType.AB_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.AB_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.AB_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.AB_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.AB_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.AB_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.AB_POSITIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.AB_POSITIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));
    }

    @Test
    public void whenDonorAbNegative() {
        ImmutableTriple<Dealer, Alice, Bob> participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_POSITIVE, BloodType.AB_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.ZERO_NEGATIVE, BloodType.AB_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_POSITIVE, BloodType.AB_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.A_NEGATIVE, BloodType.AB_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_POSITIVE, BloodType.AB_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.B_NEGATIVE, BloodType.AB_NEGATIVE);
        assertFalse(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_POSITIVE, BloodType.AB_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));

        participants = Main.initParticipantsForOTTT(truthTable, BloodType.AB_NEGATIVE, BloodType.AB_NEGATIVE);
        assertTrue(Main.protocolOTTT(participants.middle, participants.right));
    }

}
