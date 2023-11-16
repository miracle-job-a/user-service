package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.Birth;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserJoinRequestDto {

    @Email(message = "400_1:이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "400_1:이메일 형식이 올바르지 않습니다.")
    private final String email;

    @Pattern(regexp = "^[가-힣]{2,}$", message = "400_2:이름 형식이 올바르지 않습니다.")
    @NotBlank(message = "400_2:이름 형식이 올바르지 않습니다.")
    private final String name;

    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$", message = "400_3:비밀번호 형식이 올바르지 않습니다.")
    @NotBlank(message = "400_3:비밀번호 형식이 올바르지 않습니다.")
    private final String password;

    @Pattern(regexp = "^010(\\d{8})$", message = "400_4:전화번호 형식이 올바르지 않습니다.")
    @NotBlank(message = "400_4:전화번호 형식이 올바르지 않습니다.")
    private final String phone;

    @Birth
    private final LocalDate birth;

    @NotBlank(message = "400_6:주소 형식이 올바르지 않습니다.")
    private final String address;

    public UserJoinRequestDto() {
        this.email = null;
        this.name = null;
        this.password = null;
        this.phone = null;
        this.birth = null;
        this.address = null;
    }

    @Builder
    public UserJoinRequestDto(String email, String name, String password, String phone, LocalDate birth, String address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
    }
}
