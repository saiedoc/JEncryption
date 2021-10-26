package main.encryption;

import main.encryption.RSA.RSA;

public class Main {

    public static void main(String[] args) {

        RSA rsa = new RSA();

        String cipher = String.valueOf(rsa.encryptMessage("100",3));
        System.out.println("Encrypted Message : " + cipher);
        String message = new RSA(rsa.getPublicKey()).decryptCipher(cipher);
        System.out.println("Decrypted Message : " + message);

    }
}
