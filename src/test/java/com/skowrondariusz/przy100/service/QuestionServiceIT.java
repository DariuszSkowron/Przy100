package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@RunWith(SpringRunner.class)
@SpringBootTest

//@RunWith(MockitoJUnitRunner.class)
//@DataJpaTest
//@RunWith(SpringRunner.class)
@TestPropertySource("classpath:application-service.properties")
//@ActiveProfiles("testing")
public class QuestionServiceIT {


    @Autowired
    private QuestionService questionService;


    @Autowired
   private QuestionRepository questionRepository;


    private Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
    private Question question2 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
    private Question question3 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
    private Question question4 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );


    @Before
    public  void setUp(){
        questionRepository.deleteAll();
        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);
        questionService.saveQuestion(question3);
        questionService.saveQuestion(question4);
    }



    @Test
    public void shouldGetCorrectAnswerForQuestion(){



        String answer = questionService.getCorrectAnswerForQuestion(question1.getId());


        assertThat(answer.contains(question1.getCorrectAnswer()));
    }


    @Test
    public void shouldCollectQuestionsForQuiz(){



        List<Question> questionList = questionService.collectQuestionsForQuiz(4);

        assertThat(questionList).hasSize(4).extracting(Question::getId).contains(question1.getId(), question2.getId(),question3.getId(),question4.getId());
    }




    @Test
    public void shouldDrawQuestions(){

        assertThat(questionService.drawQuestions(4)).contains(1,2,3,4);
    }




    @Test
    public void shouldCountQuestions(){


        assertThat(questionService.count()).isEqualTo(4);
    }

}
