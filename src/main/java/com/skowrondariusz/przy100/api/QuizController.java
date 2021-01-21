package com.skowrondariusz.przy100.api;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import com.skowrondariusz.przy100.repository.SongRepository;
import com.skowrondariusz.przy100.service.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.skowrondariusz.przy100.service.SpotifyService.getArtistsAlbums_Sync;


@RestController
@RequestMapping("/quiz")
public class QuizController {


    private QuizService quizService;
    private QuestionService questionService;
    private ResultRepository resultRepository;
    private ResultService resultService;
    private SpotifyService spotifyService;
    private SongDrawingService songDrawingService;


    public QuizController(QuizService quizService, QuestionService questionService, ResultRepository resultRepository, ResultService resultService, SpotifyService spotifyService, SongDrawingService songDrawingService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.resultRepository = resultRepository;
        this.resultService = resultService;
        this.spotifyService = spotifyService;
        this.songDrawingService = songDrawingService;
    }

    @GetMapping("/start")
    public Quiz startQuiz() {
        return quizService.startNewQuiz(20);
    }

//    @PostMapping("/correctAnswers")
//    public String[] result(@RequestBody int[] answerIdList) {
//        var result = new String[answerIdList.length];
//        for (int i = 0; i < answerIdList.length; i++) {
//            result[i] = questionService.getCorrectAnswerForQuestion(answerIdList[i]);
//        }
//        return result;
//    }
    //

    @PostMapping("/userResult")
    public  @ResponseBody Result userResult(@RequestBody Quiz userQuiz) {
        return resultService.getUserResult(userQuiz);
    }

    @PostMapping("/result")
    public Result postResult(@RequestBody Result result) {
        this.resultService.submitResult(result);
        return result;
    }

    @GetMapping("/highscores")
    public List<Result> highscores() {
        var topResults = this.resultRepository.findAll();
        return new ArrayList<>(topResults);
    }


    @PostMapping("/isHighScore")
    public boolean scoreAbleToSubmit(@RequestBody Result result){
        return resultService.checkIfAbleToSubmitScore(result);
    }

    @GetMapping("/test")
    public List<Result> weakest(){
        return resultService.sorting();
    }

    @GetMapping("/databaseGenerate")
    public void generateDatabase(){
        getArtistsAlbums_Sync();
        spotifyService.test();
        songDrawingService.musicQuestionGenerate();
        System.out.println("DATABASE INITIALISED");
        spotifyService.totalCharacters();
    }


    @GetMapping("/clearDatabase")
    public void clearDatabase(){
        questionService.deleteQuestions();
    }



}
