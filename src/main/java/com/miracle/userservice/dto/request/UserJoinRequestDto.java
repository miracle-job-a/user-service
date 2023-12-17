package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.Birth;
import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import com.miracle.userservice.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserJoinRequestDto {

    @Schema(
            description = "유저 이메일. 이메일 형식을 지켜야함",
            required = true,
            example = "youremail@naver.com"
    )
    @Email(message = ValidationDefaultMsgUtil.UserJoin.EMAIL)
    @NotBlank(message = ValidationDefaultMsgUtil.UserJoin.EMAIL)
    private final String email;

    @Schema(
            description = "유저 이름. 한글만 가능하고 2글자 이상이어야함",
            required = true,
            example = "오스틴"
    )
    @Pattern(regexp = "^[가-힣]{2,}$", message = ValidationDefaultMsgUtil.UserJoin.NAME)
    @NotBlank(message = ValidationDefaultMsgUtil.UserJoin.NAME)
    private final String name;

    @Schema(
            description = "유저 비밀번호. 최소 6글자이며 특수문자가 최소 1개 포함되어야함",
            required = true,
            example = "ostin123!"
    )
    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$", message = ValidationDefaultMsgUtil.UserJoin.PASSWORD)
    @NotBlank(message = ValidationDefaultMsgUtil.UserJoin.PASSWORD)
    private final String password;

    @Schema(
            description = "유저 전화번호. 특수문자 없이 11개의 숫자로만 이루어져야함. 맨 앞은 010으로 시작해야함",
            required = true,
            example = "01012345678"
    )
    @Pattern(regexp = "^010(\\d{8})$", message = ValidationDefaultMsgUtil.UserJoin.PHONE)
    @NotBlank(message = ValidationDefaultMsgUtil.UserJoin.PHONE)
    private final String phone;

    @Schema(
            description = "유저 생년월일. 'yyyy-MM-dd' 형식으로 요청해야함. 1900년 이후, 현재 날짜 이전 날짜만 가능",
            required = true,
            example = "2017-01-01"
    )
    @Birth(message = ValidationDefaultMsgUtil.UserJoin.BIRTH)
    private final LocalDate birth;

    @Schema(
            description = "유저 주소",
            required = true,
            example = "서울특별시 서초구 효령로 113"
    )
    @NotBlank(message = ValidationDefaultMsgUtil.UserJoin.ADDRESS)
    private final String address;

    @Schema(
            description = "sso 이름",
            example = "google"
    )
    private final String sso;

    public UserJoinRequestDto() {
        this.email = null;
        this.name = null;
        this.password = null;
        this.phone = null;
        this.birth = null;
        this.address = null;
        this.sso = null;
    }

    @Builder
    public UserJoinRequestDto(String email, String name, String password, String phone, LocalDate birth, String address, String sso) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
        this.sso = sso;
    }

    public String getEmail() {
        if (sso != null) {
            return sso + "#" + email;
        }

        return email;
    }

    public User transformToUser() {
        String name = this.name;
        String email = getEmail();
        String password = this.password;
        String phone = this.phone;
        String address = this.address;
        LocalDate birth = this.birth;

        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .phone(phone)
                .address(address)
                .birth(birth)
                .build();
    }
}
