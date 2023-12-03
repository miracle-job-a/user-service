package com.miracle.userservice.dto.response;

import com.miracle.userservice.util.DateFormatUtil;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class ResumeInApplicationLetterResponseDto {

    private final String resumeTitle;
    private final String userName;
    private final String userEmail;
    private final int userCareer;
    private final String userBirth;
    private final String userPhone;
    private final String userAddress;
    private final String userJob;
    private final Set<Long> userStackIdSet;
    private final String userEducation;
    private final String userGitLink;
    private final List<String> userCareerDetailList;
    private final List<String> userProjectList;
    private final List<String> userEtcList;

    @Builder
    public ResumeInApplicationLetterResponseDto(String resumeTitle, String userName, String userEmail, int userCareer, LocalDate userBirth, String userPhone, String userAddress, String userJob, Set<Long> userStackIdSet, String userEducation, String userGitLink, List<String> userCareerDetailList, List<String> userProjectList, List<String> userEtcList) {
        this.resumeTitle = resumeTitle;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userCareer = userCareer;
        this.userBirth = DateFormatUtil.dateToString(userBirth);
        this.userPhone = userPhone;
        this.userAddress = userAddress;
        this.userJob = userJob;
        this.userStackIdSet = Set.copyOf(userStackIdSet);
        this.userEducation = userEducation;
        this.userGitLink = userGitLink;
        this.userCareerDetailList = List.copyOf(userCareerDetailList);
        this.userProjectList = List.copyOf(userProjectList);
        this.userEtcList = List.copyOf(userEtcList);
    }
}
