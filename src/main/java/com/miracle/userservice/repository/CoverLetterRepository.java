package com.miracle.userservice.repository;

import com.miracle.userservice.dto.response.CoverLetterListResponseDto;
import com.miracle.userservice.entity.CoverLetter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoverLetterRepository extends JpaRepository<CoverLetter, Long> {
    List<CoverLetter> findByUserId(Long userId);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.CoverLetterListResponseDto(c.id, c.title, c.modifiedAt)
           FROM CoverLetter c
           ORDER BY c.modifiedAt DESC
           """)
    Page<CoverLetterListResponseDto> findByUserId(Long userId, Pageable pageable);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.CoverLetterListResponseDto(c.id, c.title, c.modifiedAt)
           FROM CoverLetter c
           WHERE c.title LIKE %:word%
           AND (COALESCE(:userId, c.user.id) = c.user.id)
           """)
    Page<CoverLetterListResponseDto> findByUserIdAndTitleContains(@Param("userId") Long userId, @Param("word") String word, Pageable pageable);
}
