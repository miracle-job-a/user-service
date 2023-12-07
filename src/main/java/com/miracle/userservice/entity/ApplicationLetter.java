package com.miracle.userservice.entity;

import com.miracle.userservice.converter.SymmetricCypherConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class ApplicationLetter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_letter_type", nullable = false, length = 10)
    private PostType postType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private LocalDateTime submitDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ApplicationStatus applicationStatus;

    @Column(nullable = false, length = 50)
    private String resumeTitle;

    @Column(nullable = false, length = 50)
    private String coverLetterTitle;

    @Convert(converter = SymmetricCypherConverter.class)
    @Column(nullable = false, length = 50)
    private String userEmail;

    @Column(nullable = false, length = 30)
    private String userName;

    @Convert(converter = SymmetricCypherConverter.class)
    @Column(nullable = false, length = 20)
    private String userPhone;

    @Column(length = 20)
    private String userEducation;

    @Column(length = 50)
    private String userJob;

    @Column(length = 100)
    private String userGitLink;

    private LocalDate userBirth;
    private int userCareer;

    @ElementCollection
    @CollectionTable(
            name = "application_letter_career_detail",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "content", nullable = false)
    private final List<String> careerDetailList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_project",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private final List<String> projectList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_etc",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "content", nullable = false)
    private final List<String> etcList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_answer",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    private final List<Qna> qnaList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_stack",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "stack_id", nullable = false)
    private final Set<Long> stackIdSet = new HashSet<>();

    @Builder
    public ApplicationLetter(PostType postType, User user, Long postId, LocalDateTime submitDate, ApplicationStatus applicationStatus, String resumeTitle, String coverLetterTitle, String userEmail, String userName, String userPhone, String userEducation, String userJob, String userGitLink, LocalDate userBirth, int userCareer) {
        this.postType = postType;
        this.user = user;
        this.postId = postId;
        this.submitDate = submitDate;
        this.applicationStatus = applicationStatus;
        this.resumeTitle = resumeTitle;
        this.coverLetterTitle = coverLetterTitle;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEducation = userEducation;
        this.userJob = userJob;
        this.userGitLink = userGitLink;
        this.userBirth = userBirth;
        this.userCareer = userCareer;
    }
}
