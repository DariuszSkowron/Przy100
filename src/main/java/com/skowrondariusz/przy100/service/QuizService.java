package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.utility.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private QuestionRepository questionRepository;
    private QuestionService questionService;
    private Mapper mapper;

    public QuizService(QuestionRepository questionRepository, QuestionService questionService, Mapper mapper) {
        this.questionRepository = questionRepository;
        this.questionService = questionService;
        this.mapper = mapper;
    }

    public Quiz startNewQuiz(){
        Quiz quiz = new Quiz();
//        questionService.collectQuestionsForQuiz(5).stream()
//                .map(question -> this.mapper.convertToQuestionDto(question))
//                .collect(Collectors.toList());
//        quiz.setQuestionList(questionService.collectQuestionsForQuiz(5));
        quiz.setQuestionList(        questionService.collectQuestionsForQuiz(5).stream()
                .map(question -> this.mapper.convertToQuestionDto(question))
                .collect(Collectors.toList()));
        Date x = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        x = c.getTime();
        quiz.setStartTime(x);
        return quiz;
    }

}
