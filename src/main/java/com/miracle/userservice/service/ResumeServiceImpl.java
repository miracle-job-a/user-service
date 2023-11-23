package com.miracle.userservice.service;

import com.miracle.userservice.controller.Requester;
import com.miracle.userservice.dto.request.ResumePostRequestDto;
import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.entity.*;
import com.miracle.userservice.exception.NoSuchResumeException;
import com.miracle.userservice.repository.ResumeRepository;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public ResumeResponseDto getResumeDetail(Long id, Requester requester) {
        Objects.requireNonNull(id, "Resume id is null");

        Optional<Resume> resumeOpt = resumeRepository.findById(id);
        if (requester == Requester.COMPANY) resumeOpt = resumeOpt.filter(Resume::isOpen);

        Resume resume = resumeOpt.orElseThrow(() -> new NoSuchResumeException("이력서가 존재하지 않습니다."));
        return ResumeResponseDto.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .photo(resume.getPhoto())
                .career(resume.getCareer())
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
    public boolean postResume(ResumePostRequestDto dto) {
        Objects.requireNonNull(dto, "ResumePostRequestDto is null");

        Long userId = dto.getUserId();
        User user = userRepository.findById(userId).orElseThrow();

        Resume resume = Resume.builder()
                .user(user)
                .title(dto.getTitle())
                .education(dto.getEducation())
                .gitLink(dto.getGitLink())
                .photo(dto.getPhoto())
                .career(dto.getCareer())
                .open(dto.isOpen())
                .build();

        resume.addStackIdAll(dto.getStackIdSet());
        resume.addJobIdAll(dto.getJobIdSet());

        dto.getCareerDetailList().forEach(content -> new ResumeCareerDetail(content, resume));
        dto.getProjectList().forEach(content -> new ResumeProject(content, resume));
        dto.getEtcList().forEach(content -> new ResumeEtc(content, resume));

        resumeRepository.save(resume);
        return true;
    }

    @Override
    public boolean updateResume(Long id, ResumePostRequestDto dto) {
        Objects.requireNonNull(id, "Resume id is null");

        Optional<Resume> resumeOpt = resumeRepository.findById(id);
        Resume resume = resumeOpt.orElseThrow(() -> new NoSuchResumeException("이력서가 존재하지 않습니다."));

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

        resumeRepository.save(resume);
        return true;
    }

    @Override
    public boolean deleteResume(Long id) throws NoSuchResumeException {
        Objects.requireNonNull(id, "Resume id is null");

        Optional<Resume> resumeOpt = resumeRepository.findById(id);
        Resume resume = resumeOpt.orElseThrow(() -> new NoSuchResumeException("이력서가 존재하지 않습니다."));

        resumeRepository.delete(resume);
        return true;
    }
}
