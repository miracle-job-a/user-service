package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.ResumeResponseDto;
import com.miracle.userservice.entity.Resume;
import com.miracle.userservice.entity.ResumeCareerDetail;
import com.miracle.userservice.entity.ResumeEtc;
import com.miracle.userservice.entity.ResumeProject;
import com.miracle.userservice.exception.NoSuchResumeException;
import com.miracle.userservice.repository.ResumeRepository;
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
}
