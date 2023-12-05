package com.miracle.userservice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Interview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_letter_id")
    private ApplicationLetter applicationLetter;

    @ElementCollection
    @CollectionTable(
            name = "interview_qna",
            joinColumns = @JoinColumn(name = "interview_id")
    )
    private final List<Qna> qnaList = new ArrayList<>();

    @Builder
    public Interview(User user, ApplicationLetter applicationLetter) {
        this.user = user;
        this.applicationLetter = applicationLetter;
    }

    public void updateQnaList(List<Qna> qnaList) {
        this.qnaList.clear();
        this.qnaList.addAll(qnaList);
    }
}
