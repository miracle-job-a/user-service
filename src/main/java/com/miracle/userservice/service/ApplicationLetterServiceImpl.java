package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.ApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.CoverLetterTitleResponseDto;
import com.miracle.userservice.dto.response.ResumeTitleResponseDto;
import com.miracle.userservice.entity.CoverLetter;
import com.miracle.userservice.entity.Resume;
import com.miracle.userservice.repository.ApplicationLetterRepository;
import com.miracle.userservice.repository.CoverLetterRepository;
import com.miracle.userservice.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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
