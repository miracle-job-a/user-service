package com.miracle.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(length = 20)
    private String education;

    @Column(length = 100)
    private String gitLink;

    @Column(length = 50)
    private String photo;

    private int career;

    @Column(name = "open_status")
    private boolean open;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ResumeCareerDetail> careerDetailList = new ArrayList<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ResumeProject> projectList = new ArrayList<>();

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ResumeEtc> etcList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "resume_stack",
            joinColumns = @JoinColumn(nullable = false, name = "resume_id")
    )
    @Column(nullable = false, name = "stack_id")
    private final Set<Long> stackIdSet = new HashSet<>();

    @ElementCollection
    @CollectionTable(
            name = "resume_job",
            joinColumns = @JoinColumn(nullable = false, name = "resume_id")
    )
    @Column(nullable = false, name = "job_id")
    private final Set<Long> jobIdSet = new HashSet<>();
}
