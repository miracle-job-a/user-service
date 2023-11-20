package com.miracle.userservice.dto.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Objects;

public class BirthValidator implements ConstraintValidator<Birth, LocalDate> {

    private static final int MIN_YEAR = 1900;

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (Objects.nonNull(value)) {
            return value.getYear() >= MIN_YEAR;
        }

        return false;
    }
}
