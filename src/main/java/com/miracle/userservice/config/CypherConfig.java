package com.miracle.userservice.config;

import com.miracle.userservice.cypher.Cypher;
import com.miracle.userservice.cypher.SHA3Cypher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CypherConfig {

    @Bean
    public Cypher cypher() {
        return new SHA3Cypher();
    }
}
