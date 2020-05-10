package com.skowrondariusz.przy100.utility;

import com.skowrondariusz.przy100.model.Answer;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.AnswerRepository;
import com.skowrondariusz.przy100.service.QuestionService;
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

    public DatabaseInitializer(AnswerRepository answerRepository, QuestionService questionService) {
        this.answerRepository = answerRepository;
        this.questionService = questionService;
    }

    @Override
    public void run(String... args) {
        var correctAnswer1 = new Answer("Correct 0");
        var correctAnswer2 = new Answer("Correct 1");
        answerRepository.save(correctAnswer1);
        answerRepository.save(correctAnswer2);

        for (int i = 0; i < 6; i++) {
            var name = new Answer("Wrong" + i);
            answerRepository.save(name);
        }
        List<Answer> list1 = Arrays.asList(answerRepository.getById(3), answerRepository.getById(4), answerRepository.getById(5));
        List<Answer> list2 = Arrays.asList(answerRepository.getById(6), answerRepository.getById(7), answerRepository.getById(8));

        var question1 = new Question("question 1 test", correctAnswer1,list1);
        var question2 = new Question("question 2 test", correctAnswer2,list2);
        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);
        System.out.println("DATABASE INITIALISED");
    }
}
