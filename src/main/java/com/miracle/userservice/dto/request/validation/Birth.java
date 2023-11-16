package com.miracle.userservice.dto.request.validation;

import javax.validation.Constraint;
import javax.validation.constraints.Past;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthValidator.class)
@Past(message = "400_5:생년월일 형식이 올바르지 않습니다.")
public @interface Birth {

    String message() default "400_5:생년월일 형식이 올바르지 않습니다.";
    Class[] groups() default {};
    Class[] payload() default {};
}
