package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.dto.UserAnswerDto;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Quiz;
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


    @Test
    public void shouldReturnNumberOfCorrectAnswersInUserQuiz() {
        UserAnswerDto userAnswer1 = new UserAnswerDto(1, "test1");
        UserAnswerDto userAnswer2 = new UserAnswerDto(2, "test2");
        List<UserAnswerDto> userAnswerList = new ArrayList<>();
        userAnswerList.add(userAnswer1);
        userAnswerList.add(userAnswer2);
        Quiz userQuiz = new Quiz();
        userQuiz.setUserAnswers(userAnswerList);
        System.out.println(userQuiz.getUserAnswers().toString());
        when(questionService.getCorrectAnswerForQuestion(1)).thenReturn("test1");
        when(questionService.getCorrectAnswerForQuestion(2)).thenReturn("test2");

        assertThat(quizService.numberOfUserCorrectAnswers(userQuiz)).isEqualTo(2);
    }
}
