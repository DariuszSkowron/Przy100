package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {


    @Mock
    QuestionService questionService;

    @InjectMocks
    QuizService quizService;




    @Test
    public void shouldStartNewQuiz(){
        List<Question> questionDtoList = new ArrayList<>();

        when(questionService.collectQuestionsForQuiz(5)).thenReturn(questionDtoList);

        assertThat(quizService.startNewQuiz(5).getStartTime()).isNotNull();

    }
}
