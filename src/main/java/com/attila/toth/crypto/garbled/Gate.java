package com.attila.toth.crypto.garbled;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;

class Gate {

    private final String padding = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";  // added redundancy
    private ArrayList<String> cipherTexts = new ArrayList<>();
    private MessageDigest hash;

    Gate(Wire inputWire, Wire outputWire) {
        try {
            hash = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cipherTexts.add(computeCipherText(inputWire.k(0), outputWire.k(1)));
        cipherTexts.add(computeCipherText(inputWire.k(1), outputWire.k(0)));
        // Permute the order of the ciphertexts to hide information about inputs and outputs
        Collections.shuffle(cipherTexts);
    }

    Gate(Wire left, Wire right, Wire outputWire) {
        try {
            hash = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cipherTexts.add(computeCipherText(left.k(0)+right.k(0), outputWire.k(0)));
        cipherTexts.add(computeCipherText(left.k(1)+right.k(0), outputWire.k(0)));
        cipherTexts.add(computeCipherText(left.k(0)+right.k(1), outputWire.k(0)));
        cipherTexts.add(computeCipherText(left.k(1)+right.k(1), outputWire.k(1)));
        Collections.shuffle(cipherTexts);
    }

    private String xor(String k, String c) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < k.length(); i++) {
            if (k.charAt(i) == (c.charAt(i))) result.append("0");
            else result.append("1");
        }
        return result.toString();
    }

    private String toBinary(byte[] byteArray){
        StringBuilder result = new StringBuilder();
        // Mask every byte with 0xFF
        for (byte tempByte : byteArray) result.append(Integer.toBinaryString((tempByte & 0xFF) + 0x100).substring(1));
        return result.toString();
    }

    String evaluate(String input){
        String inputHashedBinary = toBinary(hash.digest(input.getBytes(StandardCharsets.UTF_8)));
        for (String tempCipherText : cipherTexts){
            String result = xor(inputHashedBinary, tempCipherText);
            if (result.substring(128,256).equals(padding)){
                return result.substring(0,128);
            }
        }
        return "";
    }

    private String computeCipherText(String input, String output){
        String inputHashedBinary = toBinary(hash.digest(input.getBytes(StandardCharsets.UTF_8)));
        String outputPadded = output + padding;
        return xor(inputHashedBinary, outputPadded);
    }

}
