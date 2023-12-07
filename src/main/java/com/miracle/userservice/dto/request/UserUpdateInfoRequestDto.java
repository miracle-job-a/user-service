package com.miracle.userservice.dto.request;

import com.miracle.userservice.dto.request.validation.util.ValidationDefaultMsgUtil;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserUpdateInfoRequestDto {

    @Schema(
            description = "유저 비밀번호. 최소 6글자이며 특수문자가 최소 1개 포함되어야함",
            required = true,
            example = "ostin123!"
    )
    @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$", message = ValidationDefaultMsgUtil.UserUpdateInfo.PASSWORD)
    @NotBlank(message = ValidationDefaultMsgUtil.UserUpdateInfo.PASSWORD)
    private final String password;

    @Schema(
            description = "사진 URL",
            example = "photo-url"
    )
    @Size(max = 50, message = ValidationDefaultMsgUtil.UserUpdateInfo.PHOTO)
    private final String photo;

    @ArraySchema(
            schema = @Schema(
                    description = "기술 스택 ID 목록",
                    required = true
            )
    )
    @NotNull(message = ValidationDefaultMsgUtil.UserUpdateInfo.COLLECTION)
    private final Set<Long> stackIdSet;

    @Schema(
            description = "유저 전화번호. 특수문자 없이 11개의 숫자로만 이루어져야함. 맨 앞은 010으로 시작해야함",
            required = true,
            example = "01012345678"
    )
    @Pattern(regexp = "^010(\\d{8})$", message = ValidationDefaultMsgUtil.UserUpdateInfo.PHONE)
    @NotBlank(message = ValidationDefaultMsgUtil.UserUpdateInfo.PHONE)
    private final String phone;

    @Schema(
            description = "유저 주소",
            required = true,
            example = "서울특별시 서초구 효령로 113"
    )
    @NotBlank(message = ValidationDefaultMsgUtil.UserUpdateInfo.ADDRESS)
    private final String address;

    public UserUpdateInfoRequestDto() {
        this.password = null;
        this.photo = null;
        this.stackIdSet = null;
        this.phone = null;
        this.address = null;
    }
}
