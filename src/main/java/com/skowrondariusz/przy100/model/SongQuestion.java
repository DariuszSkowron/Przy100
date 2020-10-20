package com.skowrondariusz.przy100.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class SongQuestion extends Question {

    private String correctSongUrl;

    public SongQuestion() {
    }

    public SongQuestion(String correctSongUrl) {
        this.correctSongUrl = correctSongUrl;
    }

    public SongQuestion(String description, String correctSongUrl) {
        super(description);
        this.correctSongUrl = correctSongUrl;
    }

    public SongQuestion(String description, String correctAnswerId, List<String> listOfAnswers, String correctSongUrl) {
        super(description, correctAnswerId, listOfAnswers);
        this.correctSongUrl = correctSongUrl;
    }

    public String getCorrectSongUrl() {
        return correctSongUrl;
    }

    public void setCorrectSongUrl(String correctSongUrl) {
        this.correctSongUrl = correctSongUrl;
    }


    @Override
    public String toString() {
        return "SongQuestion{" +
                "correctSongUrl='" + correctSongUrl + '\'' +
                '}';
    }
}
