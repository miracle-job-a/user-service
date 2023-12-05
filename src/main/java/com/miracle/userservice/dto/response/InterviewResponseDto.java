package com.miracle.userservice.dto.response;

import com.miracle.userservice.entity.Qna;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(of = "qnaList")
public class InterviewResponseDto {

    private final List<Qna> qnaList;

    public InterviewResponseDto(List<Qna> qnaList) {
        this.qnaList = List.copyOf(qnaList);
    }
}
