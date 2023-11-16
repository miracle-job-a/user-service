package com.miracle.userservice.dto.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.ConstraintValidator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class UserLoginRequestDto {

    @NotBlank(message = "400_3:NULL 값이 입력되었습니다.")
    private String email;

    @NotBlank(message = "400_3:NULL 값이 입력되었습니다.")
    private String password;

    public UserLoginRequestDto() {
        this.email = null;
        this.password = null;
    }
}
