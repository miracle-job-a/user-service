package com.miracle.userservice.converter;

import com.miracle.userservice.cypher.SymmetricCypher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@RequiredArgsConstructor
@Component
@Converter
public class SymmetricCypherConverter implements AttributeConverter<String, String> {

    private final SymmetricCypher symmetricCypher;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return symmetricCypher.encrypt(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return symmetricCypher.decrypt(dbData);
    }
}
