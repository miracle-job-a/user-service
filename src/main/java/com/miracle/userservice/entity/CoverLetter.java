package com.miracle.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CoverLetter extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String title;

    @OneToMany(mappedBy = "coverLetter", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<CoverLetterQna> qnaList = new ArrayList<>();

    public void addQna(CoverLetterQna coverLetterQna) {
        qnaList.add(coverLetterQna);
    }
}
