package com.miracle.userservice.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.websocket.Encoder;

@Getter
@NoArgsConstructor
@Entity
public class CoverLetterQna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cover_letter_id")
    private CoverLetter coverLetter;

    @Column(nullable = false, length = 255)
    private String question;

    @Column(columnDefinition = "TEXT")
    private String answer;
}
