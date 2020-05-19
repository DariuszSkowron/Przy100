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
//    @OneToOne(mappedBy = "question", cascade = CascadeType.MERGE)
    private long correctAnswerId;

    @OneToMany(mappedBy = "question", cascade = CascadeType.MERGE)
    @JsonManagedReference
    private List<Answer> listOfAnswers;

    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public Question(String description, long correctAnswerId, List<Answer> listOfAnswers) {
        this.description = description;
        this.correctAnswerId = correctAnswerId;
        this.listOfAnswers = listOfAnswers;
    }

    public Question(long correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(long correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<Answer> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", correctAnswerId=" + correctAnswerId +
                ", listOfAnswers=" + listOfAnswers +
                '}';
    }
}



