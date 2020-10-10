package com.skowrondariusz.przy100.utility;

//import com.skowrondariusz.przy100.model.Answer;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.SongQuestion;
//import com.skowrondariusz.przy100.repository.AnswerRepository;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.SongDrawingService;
import com.skowrondariusz.przy100.service.SpotifyTest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static com.skowrondariusz.przy100.service.SpotifyTest.*;

@Component
@ConditionalOnProperty(name = "database.startup", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {


//    private AnswerRepository answerRepository;
    private QuestionService questionService;
    private SpotifyTest spotifyTest;
    private SongDrawingService songDrawingService;

//    public DatabaseInitializer(AnswerRepository answerRepository, QuestionService questionService, SpotifyTest spotifyTest) {
//        this.answerRepository = answerRepository;
//        this.questionService = questionService;
//        this.spotifyTest = spotifyTest;
//    }


//    public DatabaseInitializer(AnswerRepository answerRepository, QuestionService questionService, SpotifyTest spotifyTest, SongDrawingService songDrawingService) {
////        this.answerRepository = answerRepository;
////        this.questionService = questionService;
////        this.spotifyTest = spotifyTest;
////        this.songDrawingService = songDrawingService;
////    }


    public DatabaseInitializer(QuestionService questionService, SpotifyTest spotifyTest, SongDrawingService songDrawingService) {
        this.questionService = questionService;
        this.spotifyTest = spotifyTest;
        this.songDrawingService = songDrawingService;
    }

    @Override
    public void run(String... args) {
        var question1 = new Question("question 1 test","kupa", Arrays.asList("kupa", "stolec", "hehe") );
        var question2 = new Question("question 2 test", "kupa1", Arrays.asList("as", "keczap", "kwiik"));
        var question3 = new SongQuestion("song question test", "kupa2", Arrays.asList("cc", "asd", "testuje"), "kupa");
        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);
        questionService.saveQuestion(question3);



//        var correctAnswer1 = new Answer("Correct 0", question1);
//        var correctAnswer2 = new Answer("Correct 1", question2);
//        var correctAnswer3 = new Answer("Correct 2", question3);
//        answerRepository.save(correctAnswer1);
//        answerRepository.save(correctAnswer2);
//        answerRepository.save(correctAnswer3);
//        questionService.setCorrectAnswer(1, correctAnswer1);
//        questionService.setCorrectAnswer(2, correctAnswer2);
//        questionService.setCorrectAnswer(3, correctAnswer3);
//
//        for (int i = 0; i < 3; i++) {
//            var name = new Answer("Wrong" + i, question1);
//            answerRepository.save(name);
//            questionService.saveWrongQuestion(question1, name);
//        }
//
//        for (int i = 4; i < 7; i++) {
//            var name = new Answer("Wrong" + i, question2);
//            answerRepository.save(name);
//            questionService.saveWrongQuestion(question2, name);
//        }
//
//        for (int i = 8; i < 10; i++) {
//            var name = new Answer("Wrong" + i, question2);
//            answerRepository.save(name);
//            questionService.saveWrongQuestion(question2, name);
//        }


        getArtistsAlbums_Sync();
        spotifyTest.test();
        songDrawingService.randomSongsNames("4");
        songDrawingService.musicQuestionGenerate();






        System.out.println("DATABASE INITIALISED");
    }
}
