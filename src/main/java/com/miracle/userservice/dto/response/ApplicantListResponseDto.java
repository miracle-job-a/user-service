package com.miracle.userservice.dto.response;

import com.miracle.userservice.util.DateFormatUtil;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplicantListResponseDto {

    private final Long applicationLetterId;
    private final String resumeTitle;
    private final String email;
    private final String name;
    private final String address;
    private final String submitDate;

    public ApplicantListResponseDto(Long applicationLetterId, String resumeTitle, String email, String name, String address, LocalDateTime submitDate) {
        this.applicationLetterId = applicationLetterId;
        this.resumeTitle = resumeTitle;
        this.email = email;
        this.name = name;
        this.address = address;
        this.submitDate = DateFormatUtil.dateToString(submitDate);
    }
}
