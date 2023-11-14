package com.miracle.userservice.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Qna {

    @Column(nullable = false)
    private String question;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;
}
