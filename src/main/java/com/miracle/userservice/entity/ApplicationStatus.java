package com.miracle.userservice.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.util.stream.Stream;

public enum ApplicationStatus {

    PASS,

    FAIL,

    IN_PROGRESS;

    @JsonCreator
    public static ApplicationStatus parsing(String inputValue) {
        return Stream.of(ApplicationStatus.values())
                .filter(applicationStatus -> applicationStatus.toString().equals(inputValue.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
