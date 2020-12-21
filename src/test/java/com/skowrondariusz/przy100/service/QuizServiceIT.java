package com.skowrondariusz.przy100.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

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

}
