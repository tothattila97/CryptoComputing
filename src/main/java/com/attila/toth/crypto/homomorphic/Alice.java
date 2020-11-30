package com.attila.toth.crypto.homomorphic;

import com.attila.toth.crypto.BloodType;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class Alice {

    private BloodType bloodtype;
    private Random random;
    private SecureRandom secureRandom = new SecureRandom();
    private BigInteger p;   // secret public key 2000 bit length

    private int n = 16;   // in case of n=128, the tests require more than 20 minutes to run
    private int riSize = 60;
    private ArrayList<Integer> s = new ArrayList<>();  // encryption  function  you  need  to  choose  a random subset S {1, .... , n}
    private ArrayList<BigInteger> qis = new ArrayList<>();  // big random integers
    private ArrayList<BigInteger> ris = new ArrayList<>(); // small random integers
    private BigInteger[] message = new BigInteger[3];   // generated message to Bob

    private boolean output;  // retrieved output which is computed by Alice

    public Alice(BloodType bloodtype) {
        random = new Random();
        this.bloodtype = bloodtype;

        p = new BigInteger("323170060713110073003389139264238282488179412411402391128420097514007417066343542" +
                "226196894173635693471179017379097041917546058732091950288537589861856221532121754" +
                "1251490177452027023579607823624888424618947758764110592864609941172324542662252219" +
                "3230540919037680524235519125679715870117001058055877651038861847280257976054903569" +
                "7325615261670813393617995413364765591603683178967290731783845896806396719009772021" +
                "94168647225871031411336429319536193471636533209717077448227988588565369208645296636" +
                "077250268955505928362751121174096972998068410554359584866583291642136218231078990999448652468262416972035911852507045361090559");

        for (int i = 0; i < n; i++) {
            int qiSize = 128;
            qis.add(generateBigPrimeNumber(qiSize));
            ris.add(new BigInteger(riSize, random));
        }
    }

    private BigInteger encrypt(int message) {
        generateSLambda.run();
        BigInteger yiSum = BigInteger.ZERO;
        for (Integer i : s) {
            BigInteger yi = ris.get(i).multiply(new BigInteger("2")).add(p.multiply(qis.get(i)));
            yiSum = yiSum.add(yi);
        }

        //BigInteger r = new BigInteger(riSize, random);
        //BigInteger m = new BigInteger(String.format("%d", message));

        return new BigInteger(String.format("%d", message)).add(new BigInteger(riSize, random).multiply(new BigInteger("2"))).add(yiSum);
    }

    private LambdaFunction generateSLambda = () -> {
        for (int i = 0; i < n; i++) if (random.nextBoolean()) s.add(i);
    };

    public BigInteger[] getMessage() {
        return generateMessage();
    }

    private BigInteger[] generateMessage() {
        for (int i = 0; i < 3; i++)
            message[i] = encrypt(Character.getNumericValue(bloodtype.binary.charAt(i)));
        return message;
    }

    public void decryptAndSetOutput(BigInteger c) {
        output = c.mod(p).mod(new BigInteger("2")).equals(new BigInteger("1"));
    }

    private BigInteger generateBigPrimeNumber(int primeBitLength) {
        //System.out.print("Generating prime number...\t");
        BigInteger prime = BigInteger.probablePrime(primeBitLength, secureRandom);

        while (!prime.subtract(BigInteger.ONE).shiftRight(1).isProbablePrime(100)) {
            prime = BigInteger.probablePrime(primeBitLength, secureRandom);
        }
        return prime;
    }

    public boolean isOutput() {
        return output;
    }

    interface LambdaFunction {
        void run();
    }

}
