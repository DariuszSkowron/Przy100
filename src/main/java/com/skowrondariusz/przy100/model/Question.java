package com.skowrondariusz.przy100.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private Answer correctAnswer;
    private List<Answer> wrongAnswers;

    public Question() {
    }

    public Question(Answer correctAnswer, List<Answer> wrongAnswers) {
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
