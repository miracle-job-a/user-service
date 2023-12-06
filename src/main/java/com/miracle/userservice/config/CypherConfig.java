package com.miracle.userservice.config;

import com.miracle.userservice.cypher.AESCypher;
import com.miracle.userservice.cypher.SymmetricCypher;
import com.miracle.userservice.cypher.AsymmetricCypher;
import com.miracle.userservice.cypher.SHA3Cypher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CypherConfig {

    @Bean
    public AsymmetricCypher asymmetricCypher() {
        return new SHA3Cypher();
    }

    @Bean
    public SymmetricCypher symmetricCypher() {
        return new AESCypher();
    }
}
