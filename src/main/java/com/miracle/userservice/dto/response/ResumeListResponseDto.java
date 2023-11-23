package com.miracle.userservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Data
public class ResumeListResponseDto {

    @Schema(description = "이력서 ID")
    private Long id;

    @Schema(description = "이력서 제목")
    private String title;

    @Schema(description = "이력서와 연관된 직무 ID 목록")
    private Set<Long> jobIdSet;

    @Schema(
            description = "이력서 수정 일자",
            format = "yyyy-MM-dd-hh-mm-ss"
    )
    private String modifiedAt;

    @Schema(description = "이력서 공개 여부")
    private boolean open;

    @Builder
    public ResumeListResponseDto(Long id, String title, Set<Long> jobIdSet, LocalDateTime modifiedAt, boolean open) {
        this.id = id;
        this.title = title;
        this.jobIdSet = Set.copyOf(jobIdSet);
        this.modifiedAt = modifiedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-hh-mm-ss"));
        this.open = open;
    }
}
