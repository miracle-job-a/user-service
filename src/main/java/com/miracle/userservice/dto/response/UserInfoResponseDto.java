package com.miracle.userservice.dto.response;

import com.miracle.userservice.util.DateFormatUtil;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserInfoResponseDto {

    private final Long id;
    private final String name;
    private final String birth;
    private final int password;
    private final String phone;
    private final String address;
    private final Set<Long> stackIdSet;

    @Builder
    public UserInfoResponseDto(Long id, String name, LocalDate birth, int password, String phone, String address, Set<Long> stackIdSet) {
        this.id = id;
        this.name = name;
        this.birth = DateFormatUtil.dateToString(birth);
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.stackIdSet = Set.copyOf(stackIdSet);
    }
}
