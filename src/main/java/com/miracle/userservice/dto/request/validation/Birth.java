package com.miracle.userservice.dto.request.validation;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;

import javax.validation.Constraint;
import javax.validation.constraints.Past;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BirthValidator.class)
@Past(message = ValidationDefaultMsgUtil.UserJoin.BIRTH)
public @interface Birth {

    String message() default "";
    Class[] groups() default {};
    Class[] payload() default {};
}
