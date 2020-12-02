package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResultService {

    private ResultRepository resultRepository;
    private QuizService quizService;

    public ResultService(ResultRepository resultRepository, QuizService quizService) {
        this.resultRepository = resultRepository;
        this.quizService = quizService;
    }

    public Result getUserResult(Quiz userQuiz){
        var userResult = new Result();
        userResult.setNumberOfCorrectAnswers(quizService.numberOfUserCorrectAnswers(userQuiz));
        var currentDate = new Date();
        var timeSpent =Math.round((((double) currentDate.getTime() - (double) userQuiz.getStartTime().getTime())/1000) *100.0) / 100.0;
        userResult.setTimeSpent(timeSpent);
        userResult.setTotalScore(quizService.userPoints(userQuiz));
        return userResult;
    }

    public boolean checkIfAbleToSubmitScore(Result userResult){
        return ((resultRepository.count() >= 2 && checkLastSubmittedScore() < userResult.getTotalScore()) || resultRepository.count() < 2);
    }

    public void deleteResult(long id){
        this.resultRepository.deleteById(id);
    }

    double checkLastSubmittedScore(){

        if (resultRepository.count() == 0){
            return 0;
        }
        return resultRepository.lowestScore();
    }

    public Result submitResult(Result result){
//        if (checkIfAbleToSubmitScore(result)){
            if (resultRepository.count() >= 2) {
//                resultRepository.deleteById(resultRepository.findFirstByOrderByTotalScoreAsc().getId());
//                Result resultToUpdate = resultRepository.getOne(resultRepository.findFirstByOrderByTotalScoreAsc().getId());
//                resultToUpdate.setTotalScore(result.getTotalScore());
//                resultToUpdate.setTimeSpent(result.getTimeSpent());
//                resultToUpdate.setNumberOfCorrectAnswers(result.getNumberOfCorrectAnswers());
//                resultToUpdate.setNickname(result.getNickname());
//                System.out.println("test2");
//                return resultRepository.save(resultToUpdate);
                updateResult(resultRepository.findFirstByOrderByTotalScoreAsc().getId(), result);
            }

//        }
        System.out.println("test");
        return resultRepository.save(result);

//        else{
//            System.out.println("Something went wrong, score submitted cant be saved in the list");
//        }
    }

    public Result updateResult(Long updatedResultId, Result result){
        Result resultToUpdate = resultRepository.getOne(updatedResultId);
        resultToUpdate.setTotalScore(result.getTotalScore());
        resultToUpdate.setTimeSpent(result.getTimeSpent());
        resultToUpdate.setNumberOfCorrectAnswers(result.getNumberOfCorrectAnswers());
        resultToUpdate.setNickname(result.getNickname());
        System.out.println("test2");
        resultRepository.save(resultToUpdate);
        return resultToUpdate;
    }

}
