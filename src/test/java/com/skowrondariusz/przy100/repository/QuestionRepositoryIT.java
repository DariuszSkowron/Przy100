package com.skowrondariusz.przy100.repository;


import com.skowrondariusz.przy100.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private  QuestionRepository questionRepository;


    @Test
    public void whenFindById_thenReturnQuestion(){

        Question firstQuestion = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        entityManager.persist(firstQuestion);
        entityManager.flush();


        Question foundQuestion = questionRepository.getQuestionById(firstQuestion.getId());

        assertThat(foundQuestion.getId())
                .isEqualTo(firstQuestion.getId());

    }

}
