package com.skowrondariusz.przy100.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date timeSpent;
    private int numberOfCorrectAnswers;


    public Result() {
    }

    public Result(Date timeSpent, int numberOfCorrectAnswers) {
        this.timeSpent = timeSpent;
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Date timeSpent) {
        this.timeSpent = timeSpent;
    }

    public int getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }

    public void setNumberOfCorrectAnswers(int numberOfCorrectAnswers) {
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", timeSpent=" + timeSpent +
                ", numberOfCorrectAnswers=" + numberOfCorrectAnswers +
                '}';
    }
}
