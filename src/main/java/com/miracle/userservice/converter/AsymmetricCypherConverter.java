package com.miracle.userservice.converter;

import com.miracle.userservice.cypher.AsymmetricCypher;
import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@RequiredArgsConstructor
@Converter
public class AsymmetricCypherConverter implements AttributeConverter<String, String> {

    private final AsymmetricCypher asymmetricCypher;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return asymmetricCypher.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
