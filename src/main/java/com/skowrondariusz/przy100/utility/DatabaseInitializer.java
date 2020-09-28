package com.skowrondariusz.przy100.utility;

import com.skowrondariusz.przy100.controller.SpotifyAlbumClient;
import com.skowrondariusz.przy100.model.Answer;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.AnswerRepository;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.SpotifyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ConditionalOnProperty(name = "database.startup", havingValue = "true")
public class DatabaseInitializer implements CommandLineRunner {


    private AnswerRepository answerRepository;
    private QuestionService questionService;
    private SpotifyAlbumClient spotifyAlbumClient;
    private SpotifyService spotifyService;

//    public DatabaseInitializer(AnswerRepository answerRepository, QuestionService questionService) {
//        this.answerRepository = answerRepository;
//        this.questionService = questionService;
//    }


    public DatabaseInitializer(AnswerRepository answerRepository, QuestionService questionService, SpotifyAlbumClient spotifyAlbumClient, SpotifyService spotifyService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
        this.spotifyAlbumClient = spotifyAlbumClient;
        this.spotifyService = spotifyService;
    }

    @Override
    public void run(String... args) {
        var question1 = new Question("question 1 test");
        var question2 = new Question("question 2 test");
        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);

        var correctAnswer1 = new Answer("Correct 0", question1);
        var correctAnswer2 = new Answer("Correct 1", question2);
        answerRepository.save(correctAnswer1);
        answerRepository.save(correctAnswer2);
        questionService.setCorrectAnswer(1, correctAnswer1);
        questionService.setCorrectAnswer(2, correctAnswer2);

        for (int i = 0; i < 3; i++) {
            var name = new Answer("Wrong" + i, question1);
            answerRepository.save(name);
            questionService.saveWrongQuestion(question1, name);
        }

        for (int i = 4; i < 7; i++) {
            var name = new Answer("Wrong" + i, question2);
            answerRepository.save(name);
            questionService.saveWrongQuestion(question2, name);
        }
//        List<Answer> list1 = Arrays.asList(answerRepository.getById(3), answerRepository.getById(4), answerRepository.getById(5));
//        List<Answer> list2 = Arrays.asList(answerRepository.getById(6), answerRepository.getById(7), answerRepository.getById(8));


//        question1.setCorrectAnswerId(answerRepository.getById(1));
//        question1.setListOfAnswers(list1);
//        questionService.saveQuestion(question1);
//
//        question1.setCorrectAnswerId(answerRepository.getById(2));
//        question1.setListOfAnswers(list2);
//        questionService.saveQuestion(question2);


        System.out.println(spotifyService.getToken());


        System.out.println("DATABASE INITIALISED");
    }
}
