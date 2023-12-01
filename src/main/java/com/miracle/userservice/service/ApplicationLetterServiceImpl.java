package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.*;
import com.miracle.userservice.entity.ApplicationLetter;
import com.miracle.userservice.entity.CoverLetter;
import com.miracle.userservice.entity.Resume;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;
import com.miracle.userservice.repository.ApplicationLetterRepository;
import com.miracle.userservice.repository.CoverLetterRepository;
import com.miracle.userservice.repository.ResumeRepository;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApplicationLetterServiceImpl implements ApplicationLetterService {

    private final CoverLetterRepository coverLetterRepository;
    private final ResumeRepository resumeRepository;
    private final ApplicationLetterRepository applicationLetterRepository;
    private final UserRepository userRepository;

    @Override
    public long getNumberOfApplicant(Long postId) {
        return applicationLetterRepository.countByPostId(postId);
    }

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

    @Override
    public ResumeInApplicationLetterResponseDto getResume(Long applicationLetterId, Long userId) {
        Optional<ApplicationLetter> applicationLetterOpt = applicationLetterRepository.findById(applicationLetterId);
        ApplicationLetter applicationLetter = applicationLetterOpt.orElseThrow(() -> new NoSuchApplicationLetterException("400_1", "지원서가 존재하지 않습니다."));
        User user = userRepository.findById(userId).orElseThrow();

        return ResumeInApplicationLetterResponseDto.builder()
                .resumeTitle(applicationLetter.getResumeTitle())
                .userName(applicationLetter.getUserName())
                .userEmail(applicationLetter.getUserEmail())
                .userCareer(applicationLetter.getUserCareer())
                .userBirth(applicationLetter.getUserBirth())
                .userPhone(applicationLetter.getUserPhone())
                .userAddress(user.getAddress())
                .userJob(applicationLetter.getUserJob())
                .userStackIdSet(applicationLetter.getStackIdSet())
                .userEducation(applicationLetter.getUserEducation())
                .userGitLink(applicationLetter.getUserGitLink())
                .userCareerDetailList(applicationLetter.getCareerDetailList())
                .userProjectList(applicationLetter.getProjectList())
                .userEtcList(applicationLetter.getEtcList())
                .build();
    }

    @Override
    public CoverLetterInApplicationLetterResponseDto getCoverLetter(Long applicationLetterId) {
        Optional<ApplicationLetter> applicationLetterOpt = applicationLetterRepository.findById(applicationLetterId);
        ApplicationLetter applicationLetter = applicationLetterOpt.orElseThrow(() -> new NoSuchApplicationLetterException("400_1", "지원서가 존재하지 않습니다."));

        return CoverLetterInApplicationLetterResponseDto.builder()
                .coverLetterTitle(applicationLetter.getCoverLetterTitle())
                .qnaList(applicationLetter.getQnaList())
                .build();
    }

    @Override
    public Page<ApplicantListResponseDto> getApplicantList(Long postId, Pageable pageable) {
        return applicationLetterRepository.findAllApplicantListByPostId(postId, pageable);
    }

    @Override
    public Page<ApplicationLetterListResponseDto> getApplicationLetterList(Long userId, Pageable pageable) {
        return applicationLetterRepository.findAllApplicationLetterListByUserId(userId, pageable);
    }
}
