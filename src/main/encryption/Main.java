package main.encryption;

import main.encryption.RSA.RSA;

public class Main {

    public static void main(String[] args) {

        RSA rsa = new RSA();
        String oMsg = "100";
        String cipher = rsa.encryptMessage(oMsg,5);
        System.out.println("Encrypted Message : " + cipher);
        String message = new RSA(rsa.getPublicKey()).decryptCipher(cipher);
        System.out.println("Decrypted Message : " + message);
        System.out.println("Original Message : " + oMsg);

    }
}
