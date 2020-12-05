package com.skowrondariusz.przy100.model;

import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.dto.UserAnswerDto;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Quiz {

    private Date startTime;
    private Date endTime;
    private double score;
    private List<QuestionDto> questionList;
//    private List<> userAnswers;
    private List<UserAnswerDto> userAnswers;

    public Quiz() {
    }

    public Quiz(Date startTime, List<QuestionDto> questionList) {
        this.startTime = startTime;
        this.questionList = questionList;
    }

//    public Quiz(Date startTime, Date endTime, double score, List<QuestionDto> questionList, List<String> userAnswers) {
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.score = score;
//        this.questionList = questionList;
//        this.userAnswers = userAnswers;
//    }


    public Quiz(Date startTime) {
        this.startTime = startTime;
    }

    public Quiz(Date startTime, Date endTime, double score, List<QuestionDto> questionList, List<UserAnswerDto> userAnswers) {
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

    public List<QuestionDto> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionDto> questionList) {
        this.questionList = questionList;
    }

//    public List<String> getUserAnswers() {
//        return userAnswers;
//    }
//
//    public void setUserAnswers(List<String> userAnswers) {
//        this.userAnswers = userAnswers;
//    }


    public List<UserAnswerDto> getUserAnswers() {
        return userAnswers;
    }

    public void setUserAnswers(List<UserAnswerDto> userAnswers) {
        this.userAnswers = userAnswers;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", score=" + score +
                ", questionList=" + questionList +
                ", userAnswers=" + userAnswers +
                '}';
    }
}
