package com.skowrondariusz.przy100.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE)
    private String correctAnswer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Answer> wrongAnswers;

    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public Question(String description, String correctAnswer, List<Answer> wrongAnswers) {
        this.description = description;
        this.correctAnswer = correctAnswer;
        this.wrongAnswers = wrongAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<Answer> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", correctAnswer=" + correctAnswer +
                ", wrongAnswers=" + wrongAnswers +
                '}';
    }
}



