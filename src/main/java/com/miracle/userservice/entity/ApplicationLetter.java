package com.miracle.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ApplicationLetter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private PostType postType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long postId;

    @Column(nullable = false)
    private LocalDateTime submitDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ApplicationStatus applicationStatus;

    @Column(nullable = false, length = 50)
    private String userEmail;

    @Column(nullable = false, length = 30)
    private String userName;

    @Column(nullable = false, length = 20)
    private String userPhone;

    @Column(length = 20)
    private String userEducation;

    private LocalDate userBirth;

    @Column(length = 50)
    private String userJob;

    @Column(length = 100)
    private String userGitLink;

    private int userCareer;

    @ElementCollection
    @CollectionTable(
            name = "application_letter_career_detail",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "career_detail", nullable = false)
    private final List<String> careerDetailList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_project",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "project_content", nullable = false, columnDefinition = "TEXT")
    private final List<String> projectContentList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_etc",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "etc", nullable = false)
    private final List<String> etcList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_answer",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(nullable = false)
    private final List<Qna> qnaList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "application_letter_stack",
            joinColumns = @JoinColumn(name = "application_letter_id")
    )
    @Column(name = "stack_id", nullable = false)
    private final List<Long> stackList = new ArrayList<>();

}
