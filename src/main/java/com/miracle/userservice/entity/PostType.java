package com.miracle.userservice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum PostType {
    NORMAL,
    MZ;

    @JsonCreator
    public static PostType parsing(String inputValue) {
        return Stream.of(PostType.values())
                .filter(postType -> postType.toString().equals(inputValue.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
