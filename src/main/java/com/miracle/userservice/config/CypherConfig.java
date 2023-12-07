package com.miracle.userservice.config;

import com.miracle.userservice.cypher.AESCypher;
import com.miracle.userservice.cypher.AsymmetricCypher;
import com.miracle.userservice.cypher.SHA3Cypher;
import com.miracle.userservice.cypher.SymmetricCypher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class CypherConfig {

    @Value("${my.secret-key}")
    private String key;

    @Bean
    public SecretKey secretKey() {
        byte[] decodedKey = Base64.getDecoder().decode(key);
        String algorithm = "AES";
        return new SecretKeySpec(decodedKey, algorithm);
    }

    @Bean
    public AsymmetricCypher asymmetricCypher() {
        return new SHA3Cypher();
    }

    @Bean
    public SymmetricCypher symmetricCypher(SecretKey secretKey) {
        return new AESCypher(secretKey);
    }
}
