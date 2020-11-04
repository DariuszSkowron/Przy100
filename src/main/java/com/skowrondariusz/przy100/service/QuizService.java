package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.utility.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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

    public Quiz startNewQuiz(int numberOfQuestions){
        Quiz quiz = new Quiz();
        quiz.setQuestionList(        questionService.collectQuestionsForQuiz(numberOfQuestions).stream()
                .map(question -> this.mapper.convertToQuestionDto(question))
                .collect(Collectors.toList()));
        Date x = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        x = c.getTime();
        quiz.setStartTime(x);
        return quiz;
    }

    int correctAnswersCount(Quiz userQuiz){
        var result = 0;
        var questionList = userQuiz.getQuestionList().stream()
                .map(questionDto -> this.mapper.convertToQuestionEntity(questionDto))
                .collect(Collectors.toList());

        for (int i = 0; i < userQuiz.getUserAnswers().size(); i++){
            try {
                if (userQuiz.getUserAnswers().get(i).equals(questionService.getCorrectAnswerForQuestion(questionList.get(i).getId()))) {
                    result++;
                }
            }
            catch (NullPointerException e){
                {
                    System.out.print("Caught NullPointerException");
                }
            }
        }
        return result;
    }

}
