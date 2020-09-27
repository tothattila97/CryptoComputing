package com.attila.toth.crypto.ot;

import com.attila.toth.crypto.BloodType;

import java.math.BigInteger;

public class Alice extends Party {

    private BigInteger sk;
    public boolean output;

    public Alice(BloodType bloodType) {
        this.bloodType = bloodType;
        //  The generated prime number is 256 bit long, in production environment it's recommended to use 1024 bit or 2048 bit length prime, but the generation of it can takes several minutes
        // So in order to the tests will run in under 1 minute I decided to choose 256 bit long primes
        p = generateBigPrimeNumber(256);
        q = p.subtract(BigInteger.ONE).shiftRight(1);
        sk = q;
        while (sk.compareTo(q) >= 0)
            sk = new BigInteger(q.bitLength(), random);
        computeAndSetGroupGenerator();
    }

    private BigInteger generateBigPrimeNumber(int primeBitLength) {
        //System.out.print("Generating prime number...\t");
        BigInteger prime = BigInteger.probablePrime(primeBitLength, random);

        while (!prime.subtract(BigInteger.ONE).shiftRight(1).isProbablePrime(100))
            prime = BigInteger.probablePrime(primeBitLength, random);
        //System.out.println("Prime is generated.");
        return prime;
    }

    private void computeAndSetGroupGenerator() {
        BigInteger generator = new BigInteger(p.bitCount() - 1, random).mod(p);

        while (generator.modPow(q, p).compareTo(BigInteger.ONE) == 0 || generator.modPow(new BigInteger("2"), p).compareTo(BigInteger.ONE) == 0)
            generator = new BigInteger(p.bitCount() - 1, random).mod(p);
        this.g = generator;
    }

    public void generatePublicKeys() {
        // Generate public keys with OGen
        for (int i = 0; i < pks.length; i++) {
            BigInteger j = p;
            while (j.compareTo(p) >= 0)
                j = new BigInteger(p.bitLength(), random);
            pks[i] = g.modPow(j, p);
        }
        // Use the pk_b <- Gen(sk)
        pks[bloodType.val] = g.modPow(sk, p);
    }

    public void computeOutput() {
        // decrypt ->  m = c2*c1^-sk
        if (encryptedMessages[bloodType.val][0].modPow(new BigInteger("-1").multiply(sk), p).multiply(encryptedMessages[bloodType.val][1]).mod(p).equals(new BigInteger("1"))) {
            output = true;
        } else if (encryptedMessages[bloodType.val][0].modPow(new BigInteger("-1").multiply(sk), p).multiply(encryptedMessages[bloodType.val][1]).mod(p).equals(new BigInteger("-1"))) {
            output = false;
        }
    }
}
