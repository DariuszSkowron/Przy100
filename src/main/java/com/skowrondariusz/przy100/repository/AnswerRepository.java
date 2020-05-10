package com.skowrondariusz.przy100.repository;

import com.skowrondariusz.przy100.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    Answer getById(long id);
}
