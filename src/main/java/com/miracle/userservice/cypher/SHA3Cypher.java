package com.miracle.userservice.cypher;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;

public class SHA3Cypher implements Cypher {

    @Override
    public String encrypt(String input) {
        SHA3.DigestSHA3 sha3 = new SHA3.Digest512();
        byte[] bytes = sha3.digest(input.getBytes(StandardCharsets.UTF_8));
        return Hex.toHexString(bytes);
    }
}
