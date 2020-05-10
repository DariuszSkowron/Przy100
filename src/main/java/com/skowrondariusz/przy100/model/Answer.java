package com.skowrondariusz.przy100.model;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    String text;


    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    public Answer() {
    }

    public Answer(String text) {
        this.text = text;
    }

    public Answer(String text, Question question) {
        this.text = text;
        this.question = question;
    }
}
