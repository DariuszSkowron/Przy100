package com.skowrondariusz.przy100.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;

    @OneToOne(mappedBy = "question", cascade = CascadeType.MERGE)
    private Answer correctAnswer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE)
    private List<Answer> wrongAnswers;

    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public Question(String description, Answer correctAnswer, List<Answer> wrongAnswers) {
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

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Answer> getWrongAnswers() {
        return wrongAnswers;
    }

    public void setWrongAnswers(List<Answer> wrongAnswers) {
        this.wrongAnswers = wrongAnswers;
    }
}
