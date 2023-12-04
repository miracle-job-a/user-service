package com.miracle.userservice.service;

import com.miracle.userservice.controller.Requester;
import com.miracle.userservice.dto.request.ResumePostRequestDto;
import com.miracle.userservice.dto.response.ResumeListResponseDto;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.entity.*;
import com.miracle.userservice.exception.NoSuchResumeException;
import com.miracle.userservice.exception.OverflowException;
import com.miracle.userservice.repository.ResumeRepository;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public ResumeResponseDto getResumeDetail(Long resumeId, Requester requester) {
        Objects.requireNonNull(resumeId, "Resume id is null");

        Optional<Resume> resumeOpt = resumeRepository.findById(resumeId);
        if (requester == Requester.COMPANY) resumeOpt = resumeOpt.filter(Resume::isOpen);

        Resume resume = resumeOpt.orElseThrow(() -> new NoSuchResumeException("400_1", "이력서가 존재하지 않습니다."));
        return getResumeResponseDto(resume);
    }

    private ResumeResponseDto getResumeResponseDto(Resume resume) {
        return ResumeResponseDto.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .photo(resume.getPhoto())
                .career(resume.getCareer())
                .open(resume.isOpen())
                .birth(resume.getUser().getBirth())
                .phone(resume.getUser().getPhone())
                .education(resume.getEducation())
                .gitLink(resume.getGitLink())
                .jobIdSet(resume.getJobIdSet())
                .stackIdSet(resume.getStackIdSet())
                .careerDetailList(
                        resume.getCareerDetailList()
                                .stream()
                                .map(ResumeCareerDetail::getContent)
                                .toList()
                )
                .projectList(
                        resume.getProjectList()
                                .stream()
                                .map(ResumeProject::getContent)
                                .toList()
                )
                .etcList(
                        resume.getEtcList()
                                .stream()
                                .map(ResumeEtc::getContent)
                                .toList()
                )
                .build();
    }

    @Override
    public boolean postResume(Long userId, ResumePostRequestDto dto) {
        Objects.requireNonNull(dto, "ResumePostRequestDto is null");

        if (resumeRepository.countByUserId(userId) >= 5) {
            throw new OverflowException("406", "이력서는 최대 5개까지 저장 가능합니다.");
        }

        Resume resume = createResume(userId, dto);

        resume.addStackIdAll(dto.getStackIdSet());
        resume.addJobIdAll(dto.getJobIdSet());

        dto.getCareerDetailList().forEach(content -> new ResumeCareerDetail(content, resume));
        dto.getProjectList().forEach(content -> new ResumeProject(content, resume));
        dto.getEtcList().forEach(content -> new ResumeEtc(content, resume));

        resumeRepository.save(resume);
        return true;
    }

    private Resume createResume(Long userId, ResumePostRequestDto dto) {
        User user = userRepository.findById(userId).orElseThrow();
        return Resume.builder()
                .user(user)
                .title(dto.getTitle())
                .education(dto.getEducation())
                .gitLink(dto.getGitLink())
                .photo(dto.getPhoto())
                .career(dto.getCareer())
                .open(dto.isOpen())
                .build();
    }

    @Override
    public boolean updateResume(Long resumeId, ResumePostRequestDto dto) {
        Objects.requireNonNull(resumeId, "Resume id is null");

        Optional<Resume> resumeOpt = resumeRepository.findById(resumeId);
        Resume resume = resumeOpt.orElseThrow(() -> new NoSuchResumeException("400_1", "이력서가 존재하지 않습니다."));

        update(resume, dto);

        resumeRepository.save(resume);
        return true;
    }

    private void update(Resume resume, ResumePostRequestDto dto) {
        resume.setTitle(dto.getTitle());
        resume.setEducation(dto.getEducation());
        resume.setGitLink(dto.getGitLink());
        resume.setPhoto(dto.getPhoto());
        resume.setCareer(dto.getCareer());
        resume.setOpen(dto.isOpen());

        resume.getStackIdSet().clear();
        resume.getJobIdSet().clear();
        resume.addStackIdAll(dto.getStackIdSet());
        resume.addJobIdAll(dto.getJobIdSet());

        resume.getCareerDetailList().clear();
        resume.getProjectList().clear();
        resume.getEtcList().clear();
        dto.getCareerDetailList().forEach(content -> new ResumeCareerDetail(content, resume));
        dto.getProjectList().forEach(content -> new ResumeProject(content, resume));
        dto.getEtcList().forEach(content -> new ResumeEtc(content, resume));
    }

    @Override
    public boolean deleteResume(Long resumeId) {
        Objects.requireNonNull(resumeId, "Resume id is null");

        Optional<Resume> resumeOpt = resumeRepository.findById(resumeId);
        Resume resume = resumeOpt.orElseThrow(() -> new NoSuchResumeException("400_1", "이력서가 존재하지 않습니다."));

        resumeRepository.delete(resume);
        return true;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResumeListResponseDto> getUserResumes(Long userId) {
        Objects.requireNonNull(userId, "User id is null");
        List<Resume> userResumes = resumeRepository.findByUserId(userId);

        return userResumes.stream()
                .map(this::mapToResumeListResponseDto)
                .toList();
    }

    private ResumeListResponseDto mapToResumeListResponseDto(Resume resume) {
        return ResumeListResponseDto.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .jobIdSet(resume.getJobIdSet())
                .modifiedAt(resume.getModifiedAt())
                .open(resume.isOpen())
                .build();
    }
}
