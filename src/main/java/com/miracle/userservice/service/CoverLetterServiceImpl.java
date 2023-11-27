package com.miracle.userservice.service;

import com.miracle.userservice.dto.request.CoverLetterPostRequestDto;
import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.dto.response.CoverLetterResponseDto;
import com.miracle.userservice.entity.CoverLetter;
import com.miracle.userservice.entity.CoverLetterQna;
import com.miracle.userservice.entity.Qna;
import com.miracle.userservice.entity.User;
import com.miracle.userservice.exception.NoSuchCoverLetterException;
import com.miracle.userservice.repository.CoverLetterRepository;
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
public class CoverLetterServiceImpl implements CoverLetterService {

    private final CoverLetterRepository coverLetterRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<CoverLetterListResponseDto> getCoverLetterList(Long userId) {
        String errorMessage = "User id is null";
        Objects.requireNonNull(userId, errorMessage);

        List<CoverLetter> coverLetterList = coverLetterRepository.findByUserId(userId);

        return coverLetterList.stream()
                .map(coverLetter -> new CoverLetterListResponseDto(
                        coverLetter.getId(),
                        coverLetter.getUser().getId(),
                        coverLetter.getTitle(),
                        coverLetter.getModifiedAt()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CoverLetterResponseDto getCoverLetterDetail(Long id) {
        String errorMessage = "CoverLetter id is null";
        Objects.requireNonNull(id, errorMessage);

        Optional<CoverLetter> coverLetterOpt = coverLetterRepository.findById(id);

        CoverLetter coverLetter = coverLetterOpt.orElseThrow(() -> new NoSuchCoverLetterException("자기소개서가 존재하지 않습니다."));

        return CoverLetterResponseDto.builder()
                .id(coverLetter.getId())
                .title(coverLetter.getTitle())
                .modifiedAt(coverLetter.getModifiedAt())
                .qnaList(
                        coverLetter.getQnaList()
                                .stream()
                                .map(coverLetterQna -> new Qna(
                                        coverLetterQna.getQna().getQuestion(),
                                        coverLetterQna.getQna().getAnswer()))
                                .toList()
                )
                .build();
    }

    @Override
    public boolean postCoverLetter(CoverLetterPostRequestDto dto) {
        String errorMessage = "CoverLetterPostRequestDto is null";
        Objects.requireNonNull(dto, errorMessage);

        Long userId = dto.getUserId();
        Optional<User> user = userRepository.findById(userId);

        CoverLetter coverLetter = CoverLetter.builder()
                .user(user.get())
                .title(dto.getTitle())
                .build();

        dto.getQnaList().forEach(qna -> new CoverLetterQna(qna, coverLetter));

        coverLetterRepository.save(coverLetter);

        return true;
    }

    @Override
    public boolean updateCoverLetter(Long id, CoverLetterPostRequestDto dto) {
        String errorMessage = "CoverLetter id is null";
        Objects.requireNonNull(id, errorMessage);

        Optional<CoverLetter> coverLetterOpt = coverLetterRepository.findById(id);
        CoverLetter coverLetter = coverLetterOpt.orElseThrow(() -> new NoSuchCoverLetterException("자기소개서가 존재하지 않습니다."));

        coverLetter.setTitle(dto.getTitle());
        coverLetter.getQnaList().clear();
        dto.getQnaList().forEach(qna -> coverLetter.addQna(new CoverLetterQna(qna, coverLetter)));

        coverLetterRepository.save(coverLetter);

        return true;
    }

    @Override
    public boolean deleteCoverLetter(Long id) {
        String errorMessage = "CoverLetter id is null";
        Objects.requireNonNull(id, errorMessage);

        Optional<CoverLetter> coverLetterOpt = coverLetterRepository.findById(id);
        CoverLetter coverLetter = coverLetterOpt.orElseThrow(() -> new NoSuchCoverLetterException("자기소개서가 존재하지 않습니다."));

        coverLetterRepository.delete(coverLetter);

        return true;
    }
}
