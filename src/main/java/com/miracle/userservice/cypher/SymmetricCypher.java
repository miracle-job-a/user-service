package com.miracle.userservice.cypher;

public interface SymmetricCypher extends AsymmetricCypher {

    String decrypt(String input);
}
