package com.miracle.userservice.dto.response;

import com.miracle.userservice.swagger.util.DateFormatUtil;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserBaseInfoResponseDto {

    private final String email;
    private final String name;
    private final String phone;
    private final String birth;
    private final String address;

    public UserBaseInfoResponseDto(String email, String name, String phone, LocalDate birth, String address) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.birth = DateFormatUtil.dateToString(birth, "yyyy-MM-dd");
        this.address = address;
    }
}
