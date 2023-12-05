package com.miracle.userservice.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 50)
    private String title;

    @ElementCollection
    @CollectionTable(
            name = "cover_letter_qna",
            joinColumns = @JoinColumn(name = "cover_letter_id")
    )
    private final List<Qna> qnaList = new ArrayList<>();

    @Builder
    public CoverLetter(User user, String title) {
        this.user = user;
        this.title = title;
    }

    public void updateQnaList(List<Qna> qnaList) {
        this.qnaList.clear();
        this.qnaList.addAll(qnaList);
        setModifiedAt(LocalDateTime.now());
    }
}
