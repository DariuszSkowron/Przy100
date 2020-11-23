package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


//@ExtendWith(MockitoExtension.class)
//@SpringBootTest

@RunWith(MockitoJUnitRunner.class)
//@DataJpaTest
//@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:application.properties")

public class QuestionServiceTestMockito {


    @Mock
    private QuestionRepository questionRepository;


    @InjectMocks
    private QuestionService questionService;


//@Before
//public void setUp() throws Exception{
//    questionService = new QuestionService(questionRepository);
//}



//    @Autowired
//   private QuestionRepository questionRepository;
//



    // @Autowired
//    private QuestionService questionService;
//
//

    @Test
    public void savedQuestionHasId(){
        Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        when(questionRepository.save(Mockito.any(Question.class))).then(returnsFirstArg());
        Question savedQuestion = questionService.saveQuestion(question1);
        assertThat(savedQuestion.getDescription()).isEqualTo("question 1 test");
    }

    @Test
    public void shouldCollectQuestionsForQuiz(){
        Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        Question question2 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        questionService.saveQuestion(question1);
        questionService.saveQuestion(question2);


        List<Question> questionList = questionService.collectQuestionsForQuiz(2);

        assertThat(questionList).hasSize(2).extracting(Question::getId).contains(question1.getId(), question2.getId());
    }




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
    public void shouldGetCorrectAnswerForQuestion(){
        Question question1 = new Question("question 1 test","aq1", Arrays.asList("q1", "q2", "a3") );
        questionService.saveQuestion(question1);

        assertThat(questionService.getCorrectAnswerForQuestion(1).contains("aq1"));
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
