package com.miracle.userservice.repository;

import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.entity.CoverLetter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Long> {
    List<CoverLetter> findByUserId(Long userId);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.CoverLetterListResponseDto(c.id, c.title, c.modifiedAt)
           FROM CoverLetter c
           ORDER BY c.id DESC
           """)
    Page<CoverLetterListResponseDto> findByUserId(Long userId, Pageable pageable);
}
