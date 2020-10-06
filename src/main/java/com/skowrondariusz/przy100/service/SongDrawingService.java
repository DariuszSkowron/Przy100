package com.skowrondariusz.przy100.service;


import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Song;
import com.skowrondariusz.przy100.model.SongQuestion;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.repository.SongRepository;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SongDrawingService {


   public SongRepository songRepository;
   public QuestionService questionService;

    public SongDrawingService(SongRepository songRepository, QuestionService questionService) {
        this.songRepository = songRepository;
        this.questionService = questionService;
    }

    public void musicQuestionGenerate(){
        List<Song> songList = songRepository.findAll();
        for (Song song : songList) {
            var songQuestion = new SongQuestion("Guess the song title");
//            questionRepository.save(songQuestion);
            questionService.saveQuestion(songQuestion);
            songQuestion.setCorrectSongUrl(song.getPreviewUrl());
//            songQuestion.setListOfAnswers(randomSongsNames(String.valueOf(song.getId())));
            questionService.saveQuestion(songQuestion);
            System.out.println("essa");
        }
    }

    public List<String> randomSongsNames(String id){
        List<Song> songList = songRepository.findAll();
        int[] songIds = new Random().ints(1, Math.toIntExact(songList.size()))
                .skip(Long.parseLong(id))
                .distinct()
                .limit(3)
                .toArray();

        List<String> drawedSongsNames = new ArrayList<>();

        for (int songId : songIds) {
            drawedSongsNames.add(songRepository.findById(songId).getName());


        }
        System.out.println(drawedSongsNames.toString());
        return drawedSongsNames;

//        return songList.stream()
//                .map(Objects::toString)
//                .collect(Collectors.toList());


    }



//    public int[] drawQuestions(int quizSize){
//        return new Random().ints(1,Math.toIntExact(questionRepository.count()) +1)
//                .distinct()
//                .limit(quizSize)
//                .toArray();
//    }
}
