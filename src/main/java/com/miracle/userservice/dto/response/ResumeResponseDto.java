package com.miracle.userservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class ResumeResponseDto {

    private final Long id;
    private final String title;
    private final String photo;
    private final int career;
    private final LocalDate birth;
    private final String phone;
    private final String education;
    private final String gitLink;
    private final Set<Long> jobIdSet;
    private final Set<Long> stackIdSet;
    private final List<String> careerDetailList;
    private final List<String> projectList;
    private final List<String> etcList;

    @Builder
    private ResumeResponseDto(Long id, String title, String photo, int career, LocalDate birth, String phone, String education, String gitLink, Set<Long> jobIdSet, Set<Long> stackIdSet, List<String> careerDetailList, List<String> projectList, List<String> etcList) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.career = career;
        this.birth = birth;
        this.phone = phone;
        this.education = education;
        this.gitLink = gitLink;
        this.jobIdSet = Set.copyOf(jobIdSet);
        this.stackIdSet = Set.copyOf(stackIdSet);
        this.careerDetailList = List.copyOf(careerDetailList);
        this.projectList = List.copyOf(projectList);
        this.etcList = List.copyOf(etcList);
    }
}
