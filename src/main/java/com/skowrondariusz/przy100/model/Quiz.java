package com.skowrondariusz.przy100.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

public class Quiz {

    private Date startTime;
    private Date endTime;
    private double score;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partsOrder", cascade = CascadeType.ALL)
    private List<Question> questionList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "partsOrder", cascade = CascadeType.ALL)
    private List<Answer> userAnswers;

    public Quiz() {
    }

    public Quiz(Date startTime, List<Question> questionList) {
        this.startTime = startTime;
        this.questionList = questionList;
    }

    public Quiz(Date startTime, Date endTime, double score, List<Question> questionList, List<Answer> userAnswers) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
        this.questionList = questionList;
        this.userAnswers = userAnswers;
    }



    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public List<Answer> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<Answer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
