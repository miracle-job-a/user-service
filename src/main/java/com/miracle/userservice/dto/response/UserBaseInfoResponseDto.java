package com.miracle.userservice.dto.response;

import com.miracle.userservice.util.DateFormatUtil;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserBaseInfoResponseDto {

    private final String email;
    private final String name;
    private final String phone;
    private final String birth;
    private final String address;
    private final Set<Long> stackIdSet;

    @Builder
    public UserBaseInfoResponseDto(String email, String name, String phone, LocalDate birth, String address, Set<Long> stackIdSet) {
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.birth = DateFormatUtil.dateToString(birth);
        this.address = address;
        this.stackIdSet = Set.copyOf(stackIdSet);
    }
}
