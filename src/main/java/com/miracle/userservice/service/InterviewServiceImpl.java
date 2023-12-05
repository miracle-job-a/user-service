package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.InterviewResponseDto;
import com.miracle.userservice.entity.Interview;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;
import com.miracle.userservice.exception.NoSuchInterviewException;
import com.miracle.userservice.repository.ApplicationLetterRepository;
import com.miracle.userservice.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class InterviewServiceImpl implements InterviewService{

    private final InterviewRepository interviewRepository;

    @Transactional(readOnly = true)
    @Override
    public InterviewResponseDto getInterviews(Long interviewId) {
        if(interviewId == null) return null;

        Optional<Interview> interviewOpt = interviewRepository.findById(interviewId);
        Interview interview = interviewOpt.orElseThrow(() -> new NoSuchInterviewException("400_1", "면접 정보가 존재하지 않습니다."));

        return new InterviewResponseDto(interview.getQnaList());
    }

    @Override
    public boolean deleteInterview(Long interviewId) {
        Objects.requireNonNull(interviewId, "Interview id is null");

        interviewRepository.findById(interviewId).orElseThrow(() -> new NoSuchInterviewException("400_1", "면접 정보가 존재하지 않습니다."));
        interviewRepository.deleteById(interviewId);

        return true;
    }
}
