package com.miracle.userservice.repository;

import com.miracle.userservice.dto.response.CoverLetterInApplicationLetterResponseDto;
import com.miracle.userservice.dto.response.ResumeInApplicationLetterResponseDto;
import com.miracle.userservice.entity.ApplicationLetter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationLetterRepository extends JpaRepository<ApplicationLetter, Long> {

    long countByPostId(Long postId);
}
