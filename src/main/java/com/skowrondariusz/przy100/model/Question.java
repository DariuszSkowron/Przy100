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
    private String correctAnswer;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE)
//    @JsonManagedReference
    @ElementCollection
    private List<String> listOfWrongAnswers;

    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public Question(String description, String correctAnswerId, List<String> listOfAnswers) {
        this.description = description;
        this.correctAnswer = correctAnswerId;
        this.listOfWrongAnswers = listOfAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getListOfWrongAnswers() {
        return listOfWrongAnswers;
    }

    public void setListOfWrongAnswers(List<String> listOfWrongAnswers) {
        this.listOfWrongAnswers = listOfWrongAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", listOfWrongAnswers=" + listOfWrongAnswers +
                '}';
    }
}



