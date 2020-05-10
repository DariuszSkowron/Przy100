package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question getQuestionById (long id);
    long count();
}
