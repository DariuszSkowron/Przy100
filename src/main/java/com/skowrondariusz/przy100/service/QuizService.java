package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class QuizService {

    private QuestionRepository questionRepository;
    private QuestionService questionService;
    private QuizRepository quizRepository;

    public QuizService(QuestionRepository questionRepository, QuestionService questionService, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
        this.quizRepository = quizRepository;
    }

    public Quiz startNewQuiz(){
        Quiz quiz = new Quiz();
        quiz.setQuestionList(questionService.collectQuestionsForQuiz(2));
        quiz.setStartTime(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        quizRepository.save(quiz);
        return quiz;
    }

}
