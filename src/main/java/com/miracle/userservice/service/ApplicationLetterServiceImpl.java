package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.ApplicationLetterPostRequestDto;
import com.miracle.userservice.dto.response.*;
import com.miracle.userservice.entity.*;
import com.miracle.userservice.entity.ApplicationLetter.ApplicationLetterBuilder;
import com.miracle.userservice.exception.DuplicateApplicationLetterException;
import com.miracle.userservice.exception.NoSuchApplicationLetterException;
import com.miracle.userservice.exception.NoSuchCoverLetterException;
import com.miracle.userservice.exception.NoSuchResumeException;
import com.miracle.userservice.repository.ApplicationLetterRepository;
import com.miracle.userservice.repository.CoverLetterRepository;
import com.miracle.userservice.repository.ResumeRepository;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional
    @Override
    public boolean postApplicationLetter(Long userId, ApplicationLetterPostRequestDto dto) {
        Objects.requireNonNull(userId, "UserId is null");
        Objects.requireNonNull(dto, "ApplicationLetterPostRequestDto is null");

        User user = userRepository.findById(userId).orElseThrow();
        Long postId = dto.getPostId();
        PostType postType = dto.getPostType();

        Optional<ApplicationLetter> applicationOpt = applicationLetterRepository.findByUserIdAndPostId(userId, postId);
        if (applicationOpt.isPresent()) {
            throw new DuplicateApplicationLetterException("400_10", "이미 지원한 공고입니다.");
        }

        ApplicationLetter applicationLetter;
        ApplicationLetterBuilder builder = ApplicationLetter.builder();
        LocalDateTime submitDate = dto.getSubmitDate();
        ApplicationStatus applicationStatus = dto.getApplicationStatus();
        String email = user.getEmail();
        String userName = user.getName();
        String phone = user.getPhone();
        if (postType == PostType.NORMAL) {
            Resume resume = resumeRepository.findById(dto.getResumeId()).orElseThrow(() -> new NoSuchResumeException("400_8", "이력서가 존재하지 않습니다."));
            CoverLetter coverLetter = coverLetterRepository.findById(dto.getCoverLetterId()).orElseThrow(() -> new NoSuchCoverLetterException("400_9", "자기소개서가 존재하지 않습니다."));

            applicationLetter = builder
                    .postType(postType)
                    .user(user)
                    .postId(postId)
                    .submitDate(submitDate)
                    .applicationStatus(applicationStatus)
                    .userEmail(email)
                    .userName(userName)
                    .userPhone(phone)
                    .resumeTitle(resume.getTitle())
                    .coverLetterTitle(coverLetter.getTitle())
                    .userEducation(resume.getEducation())
                    .userJob(dto.getUserJob())
                    .userGitLink(resume.getGitLink())
                    .userBirth(user.getBirth())
                    .userCareer(resume.getCareer())
                    .build();

            applicationLetter.getCareerDetailList().addAll(resume.getCareerDetailList());
            applicationLetter.getProjectList().addAll(resume.getProjectList());
            applicationLetter.getEtcList().addAll(resume.getEtcList());
            applicationLetter.getStackIdSet().addAll(resume.getStackIdSet());
            applicationLetter.getQnaList().addAll(coverLetter.getQnaList());
        } else {
            applicationLetter = builder
                    .postType(postType)
                    .user(user)
                    .postId(postId)
                    .submitDate(submitDate)
                    .applicationStatus(applicationStatus)
                    .userEmail(email)
                    .userName(userName)
                    .userPhone(phone)
                    .build();
        }

        applicationLetterRepository.save(applicationLetter);
        return true;
    }

    @Override
    public ResumeInApplicationLetterResponseDto getResume(Long applicationLetterId) {
        Optional<ApplicationLetter> applicationLetterOpt = applicationLetterRepository.findById(applicationLetterId);
        ApplicationLetter applicationLetter = applicationLetterOpt.orElseThrow(() -> new NoSuchApplicationLetterException("400_1", "지원서가 존재하지 않습니다."));

        return ResumeInApplicationLetterResponseDto.builder()
                .resumeTitle(applicationLetter.getResumeTitle())
                .userName(applicationLetter.getUserName())
                .userEmail(applicationLetter.getUserEmail())
                .userCareer(applicationLetter.getUserCareer())
                .userBirth(applicationLetter.getUserBirth())
                .userPhone(applicationLetter.getUserPhone())
                .userAddress(applicationLetter.getUser().getAddress())
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

    @Transactional
    @Override
    public boolean updateApplicationLetter(Long applicationLetterId, ApplicationStatus applicationStatus) {
        Objects.requireNonNull(applicationLetterId, "ApplicationLetter id is null");
        Objects.requireNonNull(applicationStatus, "ApplicationStatus is null");

        Optional<ApplicationLetter> applicationLetterOpt = applicationLetterRepository.findById(applicationLetterId);
        ApplicationLetter applicationLetter = applicationLetterOpt.orElseThrow(() -> new NoSuchApplicationLetterException("400_2", "지원서가 존재하지 않습니다."));

        applicationLetter.setApplicationStatus(applicationStatus);

        return true;
    }

    @Transactional
    @Override
    public boolean deleteApplicationLetter(Long applicationLetterId) {
        String errorMessage = "ApplicationLetter id is null";
        Objects.requireNonNull(applicationLetterId, errorMessage);

        applicationLetterRepository.findById(applicationLetterId).orElseThrow(() -> new NoSuchApplicationLetterException("400_1", "지원서가 존재하지 않습니다."));
        applicationLetterRepository.deleteById(applicationLetterId);

        return true;
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
    public Page<ApplicationLetterListResponseDto> getApplicationLetterList(Long userId, Pageable pageable) {
        return applicationLetterRepository.findAllApplicationLetterListByUserId(userId, pageable);
    }
}
