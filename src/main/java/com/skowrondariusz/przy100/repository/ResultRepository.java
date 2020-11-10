package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

//    Result findResultByTotalScoreIsLessThan (long userScore);
//
//    Result getAllByTotalScore();

    @Query(value = "SELECT min(totalScore) FROM Result")
    double lowestScore();

//    @Query(value = "SELECT r.id FROM Result AS r  ORDER BY r.totalScore DESC")
//    long userId();

    Result findFirstByOrderByTotalScoreAsc();

    long count();


//    WHERE r.totalScore
}
