package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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

//    public boolean checkIfAbleToSubmitScore(Result userResult){
//        return ((resultRepository.count() >= 30 && checkLastSubmittedScore() < userResult.getTotalScore()) || resultRepository.count() < 30);
//    }
    public boolean checkIfAbleToSubmitScore(Result userResult){
        return ((resultRepository.count() >= 30 && checkIfUserResultIsBetterThanWeakestOne(userResult)) || resultRepository.count() < 30);
    }

    public void deleteResult(long id){
        this.resultRepository.deleteById(id);
    }

//
    boolean checkIfUserResultIsBetterThanWeakestOne(Result userResult){

        if (resultRepository.count() == 0){
            return true;
        }


        if (sorting().get(0).getNumberOfCorrectAnswers() <= userResult.getNumberOfCorrectAnswers() && sorting().get(0).getTimeSpent() > userResult.getTimeSpent() ) return true;
        return false;
    }

    public List<Result> sorting(){

        Sort sort = Sort.by(
                Sort.Order.asc("numberOfCorrectAnswers"),
                Sort.Order.desc("timeSpent"));

        List<Result> resultList = resultRepository.findAll(sort);
        return resultList;

    }


    double checkLastSubmittedScore(){

        if (resultRepository.count() == 0){
            return 0;
        }
        return resultRepository.lowestScore();
    }

    @Transactional
    public Result submitResult(Result result){
        if (checkIfAbleToSubmitScore(result)){
            if (resultRepository.count() >= 30) {
                System.out.println("Last result has been overwritten");
//                return updateResult(resultRepository.findFirstByOrderByTotalScoreAsc().getId(), result);
                return updateResult(sorting().get(0).getId(), result);
            }
            System.out.println("Result has been saved");
            return resultRepository.save(result);
        }
        else{
            System.out.println("Something went wrong, submitted score can't be saved in the list");
            return null;
        }
    }


    public Result saveResult(Result result){
        return resultRepository.save(result);
    }


    @Transactional
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
