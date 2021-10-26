package main.encryption;

import main.encryption.RSA.RSA;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {

        String oMsg = "100";
        long startTime = System.nanoTime();
        RSA rsa = new RSA();
        String cipher = rsa.encryptMessage(oMsg,5);
        System.out.println("Encrypted Message : " + cipher);
        String message = new RSA(rsa.getPublicKey()).decryptCipher(cipher);
        System.out.println("Decrypted Message : " + message);
        System.out.println("Original Message : " + oMsg);
        long endTime = System.nanoTime();
        System.out.println("Execution time for RSA : " + TimeUnit.MILLISECONDS.convert(endTime-startTime, TimeUnit.NANOSECONDS) +" ms");


    }
}
