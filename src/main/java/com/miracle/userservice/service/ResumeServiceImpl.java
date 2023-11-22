package com.miracle.userservice.service;

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

    @Override
    public ResumeResponseDto getResumeDetail(Long id) {
        String message = "Resume id is null";
        Objects.requireNonNull(id, message);

        Optional<Resume> resumeOpt = resumeRepository.findById(id).filter(Resume::isOpen);
        Resume resume = resumeOpt.orElseThrow(() -> new NoSuchResumeException("이력서가 존재하지 않습니다."));
        return ResumeResponseDto.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .phone(resume.getPhoto())
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
        String message = "ResumePostRequestDto is null";
        Objects.requireNonNull(dto, message);

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
}
