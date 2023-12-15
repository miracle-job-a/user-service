package com.miracle.userservice.converter;

import lombok.RequiredArgsConstructor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@RequiredArgsConstructor
@Converter
public class EmailConverter implements AttributeConverter<String, String> {

    private final SymmetricCypherConverter symmetricCypherConverter;
    private final SsoConverter ssoConverter;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return symmetricCypherConverter.convertToDatabaseColumn(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        String decryptedData = symmetricCypherConverter.convertToEntityAttribute(dbData);
        return ssoConverter.convertToEntityAttribute(decryptedData);
    }
}
