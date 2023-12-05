package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.CoverLetterPostRequestDto;
import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.dto.response.CoverLetterResponseDto;
import com.miracle.userservice.entity.CoverLetter;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.NoSuchCoverLetterException;
import com.miracle.userservice.repository.CoverLetterRepository;
import com.miracle.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CoverLetterServiceImpl implements CoverLetterService {

    private final CoverLetterRepository coverLetterRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<CoverLetterListResponseDto> getCoverLetterList(Long userId, Pageable pageable) {
        String errorMessage = "User id is null";
        Objects.requireNonNull(userId, errorMessage);

        return coverLetterRepository.findByUserId(userId, pageable);
    }

    @Override
    public Page<CoverLetterListResponseDto> searchCoverLetter(Long userId, String word, Pageable pageable) {
        Objects.requireNonNull(userId, "User id is null");
        Objects.requireNonNull(word, "Word is null");

        return coverLetterRepository.findByUserIdAndTitleContains(userId, word, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public CoverLetterResponseDto getCoverLetterDetail(Long coverLetterId) {
        String errorMessage = "CoverLetter id is null";
        Objects.requireNonNull(coverLetterId, errorMessage);

        Optional<CoverLetter> coverLetterOpt = coverLetterRepository.findById(coverLetterId);

        CoverLetter coverLetter = coverLetterOpt.orElseThrow(() -> new NoSuchCoverLetterException("400_1", "자기소개서가 존재하지 않습니다."));

        return CoverLetterResponseDto.builder()
                .id(coverLetter.getId())
                .title(coverLetter.getTitle())
                .modifiedAt(coverLetter.getModifiedAt())
                .qnaList(coverLetter.getQnaList())
                .build();
    }

    @Override
    public boolean postCoverLetter(Long userId, CoverLetterPostRequestDto dto) {
        String errorMessage = "CoverLetterPostRequestDto is null";
        Objects.requireNonNull(dto, errorMessage);

        User user = userRepository.findById(userId).orElseThrow();

        CoverLetter coverLetter = CoverLetter.builder()
                .user(user)
                .title(dto.getTitle())
                .build();

        coverLetter.getQnaList().addAll(dto.getQnaList());

        coverLetterRepository.save(coverLetter);

        return true;
    }

    @Override
    public boolean updateCoverLetter(Long coverLetterId, CoverLetterPostRequestDto dto) {
        String errorMessage = "CoverLetter id is null";
        Objects.requireNonNull(coverLetterId, errorMessage);

        Optional<CoverLetter> coverLetterOpt = coverLetterRepository.findById(coverLetterId);
        CoverLetter coverLetter = coverLetterOpt.orElseThrow(() -> new NoSuchCoverLetterException("400_1", "자기소개서가 존재하지 않습니다."));

        coverLetter.setTitle(dto.getTitle());
        coverLetter.getQnaList().clear();
        coverLetter.getQnaList().addAll(dto.getQnaList());

        coverLetterRepository.save(coverLetter);

        return true;
    }

    @Override
    public boolean deleteCoverLetter(Long coverLetterId) {
        String errorMessage = "CoverLetter id is null";
        Objects.requireNonNull(coverLetterId, errorMessage);

        Optional<CoverLetter> coverLetterOpt = coverLetterRepository.findById(coverLetterId);
        CoverLetter coverLetter = coverLetterOpt.orElseThrow(() -> new NoSuchCoverLetterException("400_1", "자기소개서가 존재하지 않습니다."));

        coverLetterRepository.delete(coverLetter);

        return true;
    }
}
