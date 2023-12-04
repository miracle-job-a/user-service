package com.miracle.userservice.util;

import com.miracle.userservice.exception.InvalidParameterException;

import java.util.Arrays;

public abstract class ParameterValidator {

    public static void checkParameterWhenPaging(int startPage, int endPage, int pageSize, String errorMessage) {
        String[] split = errorMessage.split(":");
        String code = split[0];
        String message = split[1];

        checkPositive(startPage, code, message);
        checkPositive(endPage, code, message);
        checkPositive(pageSize, code, message);

        if (endPage < startPage) {
            throw new InvalidParameterException(code, message);
        }
    }

    private static void checkPositive(int i, String code, String errorMessage) {
        if (i <= 0) throw new InvalidParameterException(code, errorMessage);
    }

    public static <T extends Enum<T>> T checkParameterEnum(String enumName, Class<T> clazz, String errorMessage) {
        String[] split = errorMessage.split(":");
        String code = split[0];
        String message = split[1];

        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> e.name().equals(enumName.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new InvalidParameterException(code, message));
    }
}
