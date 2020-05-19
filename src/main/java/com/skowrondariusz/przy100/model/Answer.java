package com.skowrondariusz.przy100.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @OneToOne
    private long id;
    String text;


    @ManyToOne
    @JsonBackReference
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", question=" + question +
                '}';
    }
}
