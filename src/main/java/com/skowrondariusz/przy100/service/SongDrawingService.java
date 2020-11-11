package com.skowrondariusz.przy100.service;

//import com.google.cloud.translate.*;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.skowrondariusz.przy100.model.Song;
import com.skowrondariusz.przy100.model.SongQuestion;
import com.skowrondariusz.przy100.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
public class SongDrawingService {


//    Storage storage = StorageOptions.getDefaultInstance().getService();
//    Translate translate = TranslateOptions.getDefaultInstance().getService()
//Translate translate = TranslateOptions.getDefaultInstance().getService();


    public SongRepository songRepository;
    public QuestionService questionService;

    public SongDrawingService(SongRepository songRepository, QuestionService questionService) {
        this.songRepository = songRepository;
        this.questionService = questionService;
    }

    public void musicQuestionGenerate() {
        List<Song> songList = songRepository.findAll();
        for (Song song : songList) {
            var songQuestion = new SongQuestion();
            if (song.getPreviewUrl() != null) {
                songQuestion.setDescription("Guess the song title");
                songQuestion.setCorrectSongUrl(song.getPreviewUrl());
                songQuestion.setCorrectAnswer(song.getName());
//                Detection detection = translate.detect(song.getName());
//                System.out.println(detection.toString());
                songQuestion.setListOfWrongAnswers(randomSongsNames(String.valueOf(song.getId())));
                questionService.saveQuestion(songQuestion);
            }
        }
    }

    private List<String> randomSongsNames(String id) {
        List<Song> songList = songRepository.findAll();
        int[] songIds = new Random().ints(1, Math.toIntExact(songList.size()))
                .skip(Long.parseLong(id))
                .distinct()
                .limit(3)
                .toArray();

        List<String> drawnSongsNames = new ArrayList<>();

        for (int songId : songIds) {
            drawnSongsNames.add(songRepository.findById(songId).getName());


        }
        System.out.println(drawnSongsNames.toString());
        return drawnSongsNames;
    }


}
