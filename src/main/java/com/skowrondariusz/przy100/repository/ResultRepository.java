package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    @Query(value = "SELECT min(totalScore) FROM Result")
    double lowestScore();

//    @Query("SELECT x FROM Result x where min(totalScore) ORDER BY numberOfCorrectAnswers DESC")
//
//            Result weakestResult();
//@Query("SELECT * FROM Result x where min(totalScore) AND max(timeSpent)")
//Result weakestResult();
//
//Result findFirstByTimeSpentAscNAndNumberOfCorrectAnswersDesc();

    Result findFirstByOrderByTotalScoreAsc();

    long count();

}
