package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.InterviewPostRequestDto;
import com.miracle.userservice.dto.response.InterviewResponseDto;
import com.miracle.userservice.entity.ApplicationLetter;
import com.miracle.userservice.entity.Interview;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.DuplicateInterviewException;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;
import com.miracle.userservice.exception.NoSuchInterviewException;
import com.miracle.userservice.exception.OverflowException;
import com.miracle.userservice.repository.ApplicationLetterRepository;
import com.miracle.userservice.repository.InterviewRepository;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final UserRepository userRepository;
    private final ApplicationLetterRepository applicationLetterRepository;

    @Transactional(readOnly = true)
    @Override
    public InterviewResponseDto getInterviews(Long interviewId) {
        if (interviewId == null) return null;

        Optional<Interview> interviewOpt = interviewRepository.findById(interviewId);
        Interview interview = interviewOpt.orElseThrow(() -> new NoSuchInterviewException("400_1", "면접 정보가 존재하지 않습니다."));

        return new InterviewResponseDto(interview.getQnaList());
    }

    @Override
    public boolean postInterview(Long userId, InterviewPostRequestDto dto) {
        Objects.requireNonNull(userId, "User id is null");
        Objects.requireNonNull(dto, "InterviewPostRequestDto is null");

        boolean result = interviewRepository.existsByApplicationLetterId(dto.getApplicationLetterId());
        if (result) {
            throw new DuplicateInterviewException("400_3", "면접 정보가 이미 존재합니다.");
        }

        if (dto.getQnaList().size() > 5) {
            throw new OverflowException("406", "면접 정보는 최대 5개까지 저장 가능합니다.");
        }

        User user = userRepository.findById(userId).orElseThrow();
        ApplicationLetter applicationLetter = applicationLetterRepository.findById(dto.getApplicationLetterId()).orElseThrow(() -> new NoSuchApplicationLetterException("400_4", "지원서가 존재하지 않습니다."));

        Interview interview = Interview.builder()
                .user(user)
                .applicationLetter(applicationLetter)
                .build();

        interview.getQnaList().addAll(dto.getQnaList());

        interviewRepository.save(interview);

        return true;
    }

    @Override
    public boolean updateInterview(Long interviewId, InterviewPostRequestDto dto) {
        Objects.requireNonNull(interviewId, "interview id is null");
        Objects.requireNonNull(dto, "InterviewPostRequestDto is null");

        if (dto.getQnaList().size() > 5) {
            throw new OverflowException("406", "면접 정보는 최대 5개까지 저장 가능합니다.");
        }

        boolean result = applicationLetterRepository.existsById(dto.getApplicationLetterId());
        if (!result) {
            throw new NoSuchApplicationLetterException("400_3", "지원서가 존재하지 않습니다.");
        }

        Optional<Interview> interviewOpt = interviewRepository.findById(interviewId);
        Interview interview = interviewOpt.orElseThrow(() -> new NoSuchInterviewException("400_4", "면접 정보가 존재하지 않습니다."));

        interview.updateQnaList(dto.getQnaList());

        return true;
    }

    @Override
    public boolean deleteInterview(Long interviewId) {
        Objects.requireNonNull(interviewId, "Interview id is null");

        boolean result = interviewRepository.existsById(interviewId);
        if (!result) {
            throw new NoSuchInterviewException("400_1", "면접 정보가 존재하지 않습니다.");
        }

        interviewRepository.deleteById(interviewId);

        return true;
    }
}
