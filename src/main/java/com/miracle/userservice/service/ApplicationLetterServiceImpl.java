package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.ApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.CoverLetterInApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.CoverLetterTitleResponseDto;
import com.miracle.userservice.dto.response.ResumeTitleResponseDto;
import com.miracle.userservice.entity.ApplicationLetter;
import com.miracle.userservice.entity.CoverLetter;
import com.miracle.userservice.entity.Qna;
import com.miracle.userservice.entity.Resume;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;
import com.miracle.userservice.repository.ApplicationLetterRepository;
import com.miracle.userservice.repository.CoverLetterRepository;
import com.miracle.userservice.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ApplicationLetterServiceImpl implements ApplicationLetterService {

    private final CoverLetterRepository coverLetterRepository;
    private final ResumeRepository resumeRepository;
    private final ApplicationLetterRepository applicationLetterRepository;

    @Override
    public long getNumberOfApplicant(Long postId) {
        return applicationLetterRepository.countByPostId(postId);
    }

    @Transactional(readOnly = true)
    @Override
    public ApplicationLetterResponseDto getResumeAndCoverLetterList(Long userId) {
        String errorMessage = "UserId is null";
        Objects.requireNonNull(userId, errorMessage);

        List<ResumeTitleResponseDto> resumeList = getResumeList(userId);
        List<CoverLetterTitleResponseDto> coverLetterList = getCoverLetterList(userId);

        return new ApplicationLetterResponseDto(resumeList, coverLetterList);
    }

    @Transactional(readOnly = true)
    @Override
    public CoverLetterInApplicationLetterResponseDto getCoverLetter(Long applicationLetterId) {
        Optional<ApplicationLetter> applicationLetterOpt = applicationLetterRepository.findById(applicationLetterId);
        ApplicationLetter applicationLetter = applicationLetterOpt.orElseThrow(() -> new NoSuchApplicationLetterException("400", "지원서가 존재하지 않습니다."));

        return CoverLetterInApplicationLetterResponseDto.builder()
                .id(applicationLetterId)
                .coverLetterTitle(applicationLetter.getCoverLetterTitle())
                .qnaList(
                        applicationLetter.getQnaList()
                                .stream()
                                .map(qna -> new Qna(qna.getQuestion(), qna.getAnswer()))
                                .toList()
                )
                .build();
    }

    private List<ResumeTitleResponseDto> getResumeList(Long userId) {
        List<Resume> resumeList = resumeRepository.findByUserId(userId);
        return resumeList.stream()
                .map(resume -> new ResumeTitleResponseDto(resume.getId(), resume.getTitle()))
                .toList();
    }

    private List<CoverLetterTitleResponseDto> getCoverLetterList(Long userId) {
        List<CoverLetter> coverLetterList = coverLetterRepository.findByUserId(userId);
        return coverLetterList.stream()
                .map(coverLetter -> new CoverLetterTitleResponseDto(coverLetter.getId(), coverLetter.getTitle()))
                .toList();
    }
}
