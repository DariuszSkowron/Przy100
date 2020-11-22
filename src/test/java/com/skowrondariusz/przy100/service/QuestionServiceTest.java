package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
//@TestPropertySource("classpath:application.properties")
public class QuestionServiceTest {



//    @Mock
//    private QuestionRepository questionRepository;


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
