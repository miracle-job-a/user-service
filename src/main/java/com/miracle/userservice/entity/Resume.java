package com.miracle.userservice.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ElementCollection
    @CollectionTable(name = "resume_career_detail", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(nullable = false, name = "content")
    private final List<String> careerDetailList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "resume_project", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(nullable = false, name = "content", columnDefinition = "TEXT")
    private final List<String> projectList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "resume_etc", joinColumns = @JoinColumn(name = "resume_id"))
    @Column(nullable = false, name = "content")
    private final List<String> etcList = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "resume_stack", joinColumns = @JoinColumn(nullable = false, name = "resume_id"))
    @Column(nullable = false, name = "stack_id")
    private final Set<Long> stackIdSet = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "resume_job", joinColumns = @JoinColumn(nullable = false, name = "resume_id"))
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

    public void updateCareerDetailList(List<String> careerDetailList) {
        this.careerDetailList.clear();
        this.careerDetailList.addAll(careerDetailList);
        setModifiedAt(LocalDateTime.now());
    }

    public void updateProjectList(List<String> projectList) {
        this.projectList.clear();
        this.projectList.addAll(projectList);
        setModifiedAt(LocalDateTime.now());
    }

    public void updateEtcList(List<String> etcList) {
        this.etcList.clear();
        this.etcList.addAll(etcList);
        setModifiedAt(LocalDateTime.now());
    }

    public void updateStackIdSet(Set<Long> stackIdSet) {
        this.stackIdSet.clear();
        this.stackIdSet.addAll(stackIdSet);
        setModifiedAt(LocalDateTime.now());
    }

    public void updateJobIdSet(Set<Long> jobIdSet) {
        this.jobIdSet.clear();
        this.jobIdSet.addAll(jobIdSet);
        setModifiedAt(LocalDateTime.now());
    }
}
