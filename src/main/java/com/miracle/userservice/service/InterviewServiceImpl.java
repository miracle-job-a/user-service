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

@RequiredArgsConstructor
@Transactional
@Service
public class InterviewServiceImpl implements InterviewService{

    private final InterviewRepository interviewRepository;
    private final ApplicationLetterRepository applicationLetterRepository;

    @Transactional(readOnly = true)
    @Override
    public InterviewResponseDto getInterviews(Long applicationLetterId) {
        Objects.requireNonNull(applicationLetterId, "ApplicationLetter id is null");

        applicationLetterRepository.findById(applicationLetterId).orElseThrow(() -> new NoSuchApplicationLetterException("400_1", "지원서가 존재하지 않습니다."));
        Interview interview = interviewRepository.findByApplicationLetterId(applicationLetterId);
        if(interview == null) return null;

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
