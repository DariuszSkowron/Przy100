package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Result;
import org.springframework.stereotype.Service;

@Service
public class ResultService {


    public double totalScore(Result result){

       double totalScore = result.getNumberOfCorrectAnswers() * 50 / result.getTimeSpent();

       return totalScore;
    }
}
