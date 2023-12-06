package com.miracle.userservice.cypher;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESCypher implements SymmetricCypher {

    private final SecretKey secretKey;

    public AESCypher() {
        try {
            this.secretKey = KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("암호화 실패", e);
        }
    }

    @Override
    public String decrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(input));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("복호화 실패", e);
        }
    }
}
