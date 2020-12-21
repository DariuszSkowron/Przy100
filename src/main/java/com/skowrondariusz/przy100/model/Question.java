package com.skowrondariusz.przy100.model;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String description;
    private String correctAnswer;

//    @JsonManagedReference

//    @ElementCollection(fetch = FetchType.LAZY)
//    @Transactional
@ElementCollection
    private List<String> listOfWrongAnswers;


    public Question() {
    }

    public Question(String description) {
        this.description = description;
    }

    public Question(String description, String correctAnswer, List<String> listOfWrongAnswers) {
        this.description = description;
        this.correctAnswer = correctAnswer;
        this.listOfWrongAnswers = listOfWrongAnswers;
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



