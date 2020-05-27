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
    private String nickname;


    public Result() {
    }

    public Result(Date timeSpent, int numberOfCorrectAnswers, String nickname) {
        this.timeSpent = timeSpent;
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
