package com.skowrondariusz.przy100.dto;

import java.util.List;

public class QuestionDto {

    private String id;
    private String description;
    private List<String> answersList;
    private String correctSongUrl;

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
}
