package com.miracle.userservice.dto.response;

import com.miracle.userservice.swagger.util.DateFormatUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserListResponseDto {

    private final Long id;
    private final String email;
    private final String name;
    private final String address;
    private final String joinDate;

    public UserListResponseDto(Long id, String email, String name, String address, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.address = address;
        this.joinDate = DateFormatUtil.dateToString(createdAt, "yyyy-MM-dd");
    }
}
