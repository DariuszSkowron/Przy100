package com.skowrondariusz.przy100.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class SongQuestion extends Question {

    String correctSongUrl;

    public SongQuestion() {
    }

    public SongQuestion(String description) {
        super(description);
    }

    public SongQuestion(String description, String correctSongUrl) {
        super(description);
        this.correctSongUrl = correctSongUrl;
    }

    public SongQuestion(String description, long correctAnswerId, List<Answer> listOfAnswers) {
        super(description, correctAnswerId, listOfAnswers);
    }

    public SongQuestion(long correctAnswerId) {
        super(correctAnswerId);
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
