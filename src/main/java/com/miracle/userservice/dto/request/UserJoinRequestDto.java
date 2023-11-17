package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.Birth;
import com.miracle.userservice.dto.util.ValidationDefaultMsgUtil;
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
    @Email(message = ValidationDefaultMsgUtil.EMAIL)
    @NotBlank(message = ValidationDefaultMsgUtil.EMAIL)
    private final String email;

    @Pattern(regexp = "^[가-힣]{2,}$", message = "400_2:이름 형식이 올바르지 않습니다.")
    @NotBlank(message = "400_2:이름 형식이 올바르지 않습니다.")
    @Pattern(regexp = "^[가-힣]{2,}$", message = ValidationDefaultMsgUtil.NAME)
    @NotBlank(message = ValidationDefaultMsgUtil.NAME)
    private final String name;

    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$", message = "400_3:비밀번호 형식이 올바르지 않습니다.")
    @NotBlank(message = "400_3:비밀번호 형식이 올바르지 않습니다.")
    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$", message = ValidationDefaultMsgUtil.PASSWORD)
    @NotBlank(message = ValidationDefaultMsgUtil.PASSWORD)
    private final String password;

    @Pattern(regexp = "^010(\\d{8})$", message = "400_4:전화번호 형식이 올바르지 않습니다.")
    @NotBlank(message = "400_4:전화번호 형식이 올바르지 않습니다.")
    @Pattern(regexp = "^010(\\d{8})$", message = ValidationDefaultMsgUtil.PHONE)
    @NotBlank(message = ValidationDefaultMsgUtil.PHONE)
    private final String phone;

    @Birth
    @Birth(message = ValidationDefaultMsgUtil.BIRTH)
    private final LocalDate birth;

    @NotBlank(message = "400_6:주소 형식이 올바르지 않습니다.")
    @NotBlank(message = ValidationDefaultMsgUtil.ADDRESS)
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
