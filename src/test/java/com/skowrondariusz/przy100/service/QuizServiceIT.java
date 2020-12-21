package com.skowrondariusz.przy100.service;


import com.skowrondariusz.przy100.dto.UserAnswerDto;
import com.skowrondariusz.przy100.model.Quiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-testing.properties")
public class QuizServiceIT {



    @Autowired
    QuizService quizService;



    @Test
    public void shouldStartNewQuiz(){
        assertThat(quizService.startNewQuiz(5).getStartTime()).isNotNull();
    }

    @Test
    public void shouldReturnNumberOfCorrectAnswersInUserQuiz(){
        Quiz userQuiz = quizService.startNewQuiz(2);
        UserAnswerDto userAnswerDto1 = new UserAnswerDto(Long.parseLong(userQuiz.getQuestionList().get(0).getId()), new Date(),userQuiz.getQuestionList().get(0).getCorrectAnswer());
        UserAnswerDto userAnswerDto2 = new UserAnswerDto(Long.parseLong(userQuiz.getQuestionList().get(1).getId()), new Date(),userQuiz.getQuestionList().get(1).getCorrectAnswer());

        var userAnswers = Arrays.asList(userAnswerDto1, userAnswerDto2);

        userQuiz.setUserAnswers(userAnswers);

        assertThat(quizService.numberOfUserCorrectAnswers(userQuiz)).isEqualTo(2);


    }

    

}
