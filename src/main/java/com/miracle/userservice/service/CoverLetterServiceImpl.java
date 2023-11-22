package com.miracle.userservice.service;

import com.miracle.userservice.dto.response.SearchCoverLetterResponseDto;
import com.miracle.userservice.entity.CoverLetter;
import com.miracle.userservice.repository.CoverLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class CoverLetterServiceImpl implements CoverLetterService{

    private final CoverLetterRepository coverLetterRepository;
    @Override
    public List<SearchCoverLetterResponseDto> getCoverLetterList(Long userId) {
        String errorMessage = "userId is null";
        Objects.requireNonNull(userId, errorMessage);

        List<CoverLetter> coverLetterList = coverLetterRepository.findByUserId(userId);

        if (coverLetterList.isEmpty()) {
            return Collections.emptyList();
        }

        return coverLetterList.stream()
                .map(coverLetter -> new SearchCoverLetterResponseDto(
                        coverLetter.getId(),
                        coverLetter.getUser().getId(),
                        coverLetter.getTitle(),
                        coverLetter.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }
}
