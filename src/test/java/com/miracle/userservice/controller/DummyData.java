package com.miracle.userservice.controller;

import java.time.LocalDate;
import java.util.stream.Stream;

class DummyData {

    final String privateKey = "privateKey";
    final String sessionId = "sessionId";
    final int miracle = (sessionId + privateKey).hashCode();

    static Stream<LocalDate> provideBirths() {
        return Stream.of(
                LocalDate.parse("1830-01-01"),
                LocalDate.parse("3000-01-01")
        );
    }
}
