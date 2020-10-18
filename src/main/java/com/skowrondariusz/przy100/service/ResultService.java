package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private ResultRepository resultRepository;
    private QuestionService questionService;
    private QuizService quizService;

    public ResultService(ResultRepository resultRepository, QuestionService questionService, QuizService quizService) {
        this.resultRepository = resultRepository;
        this.questionService = questionService;
        this.quizService = quizService;
    }

    public double totalScore(Result result){

       double totalScore = result.getNumberOfCorrectAnswers() * 50 / result.getTimeSpent();

       return totalScore;
    }


    public Result getUserResult(Quiz userQuiz){
        var userResult = new Result();
        userResult.setNumberOfCorrectAnswers(quizService.correctAnswersCount(userQuiz));
        userResult.setTimeSpent(5);
        userResult.setTotalScore(1444);
        return userResult;
    }



}
