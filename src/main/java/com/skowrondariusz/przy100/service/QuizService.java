package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class QuizService {

    private QuestionRepository questionRepository;
    private QuestionService questionService;

    public QuizService(QuestionRepository questionRepository, QuestionService questionService) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
    }

    public Quiz startNewQuiz(){
        Quiz quiz = new Quiz();
        quiz.setQuestionList(questionService.collectQuestionsForQuiz(5));
        Date x = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        x = c.getTime();
        quiz.setStartTime(x);
        return quiz;
    }

}
