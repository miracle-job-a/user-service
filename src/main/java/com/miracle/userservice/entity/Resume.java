package com.miracle.userservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Resume extends BaseEntity {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    @Builder
    public Resume(User user, String title, String education, String gitLink, String photo, int career, boolean open) {
        this.user = user;
        this.title = title;
        this.education = education;
        this.gitLink = gitLink;
        this.photo = photo;
        this.career = career;
        this.open = open;
    }

    public void addStackIdAll(Collection<Long> collection) {
        stackIdSet.addAll(collection);
    }

    public void addJobIdAll(Collection<Long> collection) {
        jobIdSet.addAll(collection);
    }

    public void addCareerDetail(ResumeCareerDetail resumeCareerDetail) {
        careerDetailList.add(resumeCareerDetail);
    }

    public void addProject(ResumeProject resumeProject) {
        projectList.add(resumeProject);
    }

    public void addEtc(ResumeEtc resumeEtc) {
        etcList.add(resumeEtc);
    }
}
