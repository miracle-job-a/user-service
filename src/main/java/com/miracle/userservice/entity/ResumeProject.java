package com.miracle.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ResumeProject extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "resume_id")
    private Resume resume;

    public ResumeProject(String content, Resume resume) {
        this.content = content;
        this.resume = resume;
        resume.addProject(this);
    }
}
