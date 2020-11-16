package com.skowrondariusz.przy100.dto;

import java.util.Date;

public class UserAnswerDto {

    private long questionId;
    private Date answerTime;
    private String answer;

    public UserAnswerDto() {
    }

//    public UserAnswerDto(String questionId, Date answerTime, String answer) {
//        this.questionId = questionId;
//        this.answerTime = answerTime;
//        this.answer = answer;
//    }


    public UserAnswerDto(long questionId, Date answerTime, String answer) {
        this.questionId = questionId;
        this.answerTime = answerTime;
        this.answer = answer;
    }

//    public String getQuestionId() {
//        return questionId;
//    }
//
//    public void setQuestionId(String questionId) {
//        this.questionId = questionId;
//    }


    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
