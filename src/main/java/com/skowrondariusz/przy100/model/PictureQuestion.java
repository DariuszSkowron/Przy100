package com.skowrondariusz.przy100.model;

import javax.persistence.Entity;
import java.util.List;

@Entity
public class PictureQuestion extends Question {

    private String pictureUrl;


    public PictureQuestion() {
    }

    public PictureQuestion(String description, String correctAnswerId, List<String> listOfAnswers, String pictureUrl) {
        super(description, correctAnswerId, listOfAnswers);
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "PictureQuestion{" +
                "pictureUrl='" + pictureUrl + '\'' +
                '}';
    }
}
