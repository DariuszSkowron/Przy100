package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {




    @MockBean
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

//
//    @Before
//    public void setUp(){
//        Mockito.when(questionRepository.count()).thenReturn(20L);
//    }


    @Test
    public void shouldDrawQuestions(){
        Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        Question question2 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        Question question3 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        Question question4 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
    }



    @Test
    public void shouldCountQuestions(){
//        Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3"));
////        when(questionRepository.save(any(Question.class))).thenReturn(new Question());
////        given(questionService.save(question1)).willAnswer(invocation -> invocation.getArgument(0));
////        entityManager.persist(question1);
////        entityManager.flush();
////        questionService.saveQuestion(question1);
//
//
//        questionRepository.save(question1);

        Mockito.when(questionRepository.count()).thenReturn(20L);
        assertThat(questionService.count()).isEqualTo(20);
    }

}
