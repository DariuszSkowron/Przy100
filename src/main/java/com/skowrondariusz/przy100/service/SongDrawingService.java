package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Song;
import com.skowrondariusz.przy100.model.SongQuestion;
import com.skowrondariusz.przy100.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SongDrawingService {

    private SongRepository songRepository;
    private QuestionService questionService;

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
        long[] songIds = new Random().longs(1, Math.toIntExact(songList.size()))
                .takeWhile(number -> (number != Integer.parseInt(id)))
                .distinct()
                .limit(3)
                .toArray();


        List<String> drawnSongsNames = new ArrayList<>();

        for (long songId : songIds) {
            drawnSongsNames.add(songRepository.findById(songId).getName());


        }
        System.out.println(drawnSongsNames.toString());
        return drawnSongsNames;
    }


}
