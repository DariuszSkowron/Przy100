package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@RunWith(SpringRunner.class)
@SpringBootTest

//@RunWith(MockitoJUnitRunner.class)
//@DataJpaTest
//@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:application.properties")

public class QuestionServiceTest {


    @Autowired
    private QuestionService questionService;


//    @Autowired
//   private QuestionRepository questionRepository;
//



    // @Autowired
//    private QuestionService questionService;
//
//





    @Test
    public void shouldDrawQuestions(){
        Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        Question question2 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        Question question3 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        Question question4 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);
        questionService.saveQuestion(question3);
        questionService.saveQuestion(question4);

        assertThat(questionService.drawQuestions(4)).contains(1,2,3,4);


    }



    @Test
    public void shouldCountQuestions(){
        Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3"));
        Question question2 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3"));


//        when(questionRepository.save(any(Question.class))).thenReturn(new Question());
////        given(questionService.save(question1)).willAnswer(invocation -> invocation.getArgument(0));
////        entityManager.persist(question1);
////        entityManager.flush();
////        questionService.saveQuestion(question1);
//
//
        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);


        assertThat(questionService.count()).isEqualTo(2);
    }

}
