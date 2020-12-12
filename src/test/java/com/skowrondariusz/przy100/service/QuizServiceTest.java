package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.dto.UserAnswerDto;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Quiz;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuizServiceTest {


    @Mock
    QuestionService questionService;

    @InjectMocks
    QuizService quizService;

    private List<UserAnswerDto> userAnswerList = new ArrayList<>();


    @Before
            public void setUp(){
    UserAnswerDto userAnswer1 = new UserAnswerDto(1, new Date(), "test1");
    UserAnswerDto userAnswer2 = new UserAnswerDto(2, new Date(), "test2");
    when(questionService.getCorrectAnswerForQuestion(1)).thenReturn("test1");
    when(questionService.getCorrectAnswerForQuestion(2)).thenReturn("test2");

        userAnswerList.add(userAnswer1);
        userAnswerList.add(userAnswer2);
    }

    @Test
    public void shouldStartNewQuiz(){
        List<Question> questionDtoList = new ArrayList<>();

        when(questionService.collectQuestionsForQuiz(5)).thenReturn(questionDtoList);

        assertThat(quizService.startNewQuiz(5).getStartTime()).isNotNull();

    }


    @Test
    public void shouldReturnNumberOfCorrectAnswersInUserQuiz() {
        Quiz userQuiz = new Quiz();
        userQuiz.setUserAnswers(userAnswerList);
        System.out.println(userQuiz.getUserAnswers().toString());
        assertThat(quizService.numberOfUserCorrectAnswers(userQuiz)).isEqualTo(2);
    }

    @Test
    public void shouldCountPoints(){
        Quiz userQuiz = new Quiz(new Date());
        userQuiz.setUserAnswers(userAnswerList);
        System.out.println(quizService.userPoints(userQuiz));
        assertThat(quizService.userPoints(userQuiz)).isNotNull();
    }
}
