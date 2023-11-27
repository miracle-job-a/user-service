package com.miracle.userservice.service;

import com.miracle.userservice.repository.ApplicationLetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ApplicationLetterServiceImpl implements ApplicationLetterService {

    private final ApplicationLetterRepository applicationLetterRepository;

    @Override
    public long getNumberOfApplicant(Long postId) {
        return applicationLetterRepository.countByPostId(postId);
    }
}
