package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

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

        return (result.getNumberOfCorrectAnswers() * 500) / result.getTimeSpent();
    }

    public Result getUserResult(Quiz userQuiz){
        var userResult = new Result();
        userResult.setNumberOfCorrectAnswers(quizService.correctAnswersCount(userQuiz));
        var currentDate = new Date();
        userResult.setTimeSpent((currentDate.getTime() - userQuiz.getStartTime().getTime())/1000);
        userResult.setTotalScore(totalScore(userResult));
        return userResult;
    }

    public boolean checkIfAbleToSubmitScore(Result userResult){
        return (resultRepository.count() > 2 && checkLastSubmittedScore() < userResult.getTotalScore()) || resultRepository.count() <= 2;
    }

    public void deleteResult(long id){
        this.resultRepository.deleteById(id);
    }

    public int checkLastSubmittedScore(){
        return resultRepository.lowestScore();
    }

    public void submitResult(Result result){
        if (checkIfAbleToSubmitScore(result)){
            if (resultRepository.count() >= 2) {
                resultRepository.deleteById(resultRepository.findFirstByOrderByTotalScoreAsc().getId());
            }
            resultRepository.save(result);
        }
        else{
            System.out.println("Something went wrong, score submitted cant be saved in the list");
        }
    }

}
