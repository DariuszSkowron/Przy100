package com.skowrondariusz.przy100.dto;

import java.util.List;

public class QuestionDto {

    private String id;
    private String description;
    private List<String> answersList;
    private String correctSongUrl;
    private String correctAnswer;

    public QuestionDto() {
    }

    public QuestionDto(String id, String description, List<String> answersList, String correctAnswerId) {
        this.id = id;
        this.description = description;
        this.answersList = answersList;
        this.correctAnswer = correctAnswerId;
    }

    public QuestionDto(String id, String description, List<String> answersList, String correctSongUrl, String correctAnswer) {
        this.id = id;
        this.description = description;
        this.answersList = answersList;
        this.correctSongUrl = correctSongUrl;
        this.correctAnswer = correctAnswer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAnswersList() {
        return answersList;
    }

    public void setAnswersList(List<String> answersList) {
        this.answersList = answersList;
    }

    public String getCorrectSongUrl() {
        return correctSongUrl;
    }

    public void setCorrectSongUrl(String correctSongUrl) {
        this.correctSongUrl = correctSongUrl;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
