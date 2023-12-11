package com.miracle.userservice.repository;

import com.miracle.userservice.dto.response.ApplicantListResponseDto;
import com.miracle.userservice.dto.response.ApplicationLetterListResponseDto;
import com.miracle.userservice.entity.ApplicationLetter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ApplicationLetterRepository extends JpaRepository<ApplicationLetter, Long> {

    long countByPostId(Long postId);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.ApplicantListResponseDto(al.id, al.resumeTitle, al.userEmail, al.userName, al.user.address, al.submitDate)
           FROM ApplicationLetter al
           WHERE al.postId = :postId
           """)
    Page<ApplicantListResponseDto> findAllApplicantListByPostId(@Param("postId") Long postId, Pageable pageable);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.ApplicationLetterListResponseDto(
               al.id, al.postId, i.id, al.postType, al.submitDate, al.applicationStatus, al.userJob
           )
           FROM ApplicationLetter al
           LEFT JOIN Interview i
           ON i.applicationLetter.id = al.id
           WHERE al.user.id = :userId
           """)
    Page<ApplicationLetterListResponseDto> findAllApplicationLetterListByUserId(@Param("userId") Long userId, Pageable pageable);

    Optional<ApplicationLetter> findByUserIdAndPostId(Long userId, Long postId);
}
