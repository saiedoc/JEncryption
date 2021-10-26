package main.encryption.RSA;

import java.math.BigInteger;
import java.util.Random;

public class RSA {

    private PublicKey publicKey;

    public class PublicKey {

        private BigInteger n;
        private BigInteger e;
        private BigInteger phi;

        public PublicKey(BigInteger n,BigInteger e,BigInteger phi){
            this.n = n;
            this.e = e;
            this.phi = phi;
        }

        public BigInteger getN() {
            return n;
        }

        public void setN(BigInteger n) {
            this.n = n;
        }

        public BigInteger getE() {
            return e;
        }

        public void setE(BigInteger e) {
            this.e = e;
        }

        public BigInteger getPhi() {
            return phi;
        }

        public void setPhi(BigInteger phi) {
            this.phi = phi;
        }
    }

    public RSA(){}

    public RSA(PublicKey key){
        this.publicKey = key;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    private boolean isPrime(BigInteger a){
        return a.isProbablePrime(1);
    }
    
    private BigInteger findCoPrime(BigInteger f){

        for(BigInteger i = new BigInteger("2");f.compareTo(i)==1;i=i.add(BigInteger.ONE)){
            if(f.gcd(i).intValue() == BigInteger.ONE.intValue())
                return i;
        }
        return null;
    }

    private void generatePublicKey(int bitLength){

        BigInteger p = new BigInteger(bitLength,new Random());
        while(!isPrime(p)){
            p = new BigInteger(bitLength,new Random());
        }
        System.out.println("P : " + p);
        BigInteger q = new BigInteger(bitLength,new Random());
        while(!isPrime(q)){
            q = new BigInteger(bitLength,new Random());
        }
        System.out.println("Q : " + q);
        BigInteger n = p.multiply(q);
        BigInteger phi = p.add(new BigInteger("-1")).multiply(q.add(new BigInteger("-1")));
        System.out.println("phi : " + phi);
        System.out.println("n : " + n);
        BigInteger e = findCoPrime(phi);
        System.out.println("e : " + e);
        this.publicKey = new PublicKey(n,e,phi);
    }

    public BigInteger generatePrivateKey(PublicKey publicKey){
        for(BigInteger i = new BigInteger("2");publicKey.phi.compareTo(i)==1;i = i.add(BigInteger.ONE)){
            if((i.multiply(publicKey.e)).mod(publicKey.phi).intValue() == BigInteger.ONE.intValue()){
                System.out.println("PK : " + i);
                return i;
            }
        }
        return null;
    }

    public BigInteger encryptMessage(String msg, int bitLength){
        BigInteger C = new BigInteger(msg);
        System.out.println("Cipher : " + C);
        generatePublicKey(bitLength);
        C = C.modPow(publicKey.e,publicKey.n);
        System.out.println("Cipher : " + C);
        return C;
    }

    public String decryptCipher(String cipher){
        BigInteger msg = new BigInteger(cipher);
        BigInteger pk = generatePrivateKey(this.publicKey);
        msg = msg.modPow(pk,publicKey.n);
        return msg.toString();
    }

}
