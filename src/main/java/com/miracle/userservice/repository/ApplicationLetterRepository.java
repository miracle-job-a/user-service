package com.miracle.userservice.repository;

import com.miracle.userservice.dto.response.ApplicantListResponseDto;
import com.miracle.userservice.entity.ApplicationLetter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ApplicationLetterRepository extends JpaRepository<ApplicationLetter, Long> {

    long countByPostId(Long postId);

    @Query("""
           SELECT new com.miracle.userservice.dto.response.ApplicantListResponseDto(al.id, al.resumeTitle, al.userName, al.user.address, al.submitDate)
           FROM ApplicationLetter al
           """)
    Page<ApplicantListResponseDto> findAllApplicantListByPostId(Long postId, Pageable pageable);
}
