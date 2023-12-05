package com.miracle.userservice.repository;

import com.miracle.userservice.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    Optional<Interview> findByApplicationLetterId(Long applicationLetterId);
}