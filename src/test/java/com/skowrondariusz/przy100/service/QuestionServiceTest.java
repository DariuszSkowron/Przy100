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

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.when;



@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceTest {


    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Before
    public void setUp() {
        Question question1 = new Question("question 1 test", "aq1", Arrays.asList("q1", "q2", "a3"));
        Question question2 = new Question("question 1 test", "aq1", Arrays.asList("q1", "q2", "a3"));
        Question question3 = new Question("question 1 test", "aq1", Arrays.asList("q1", "q2", "a3"));
        Question question4 = new Question("question 1 test", "aq1", Arrays.asList("q1", "q2", "a3"));
        question1.setId(1);
        question2.setId(2);
        question3.setId(3);
        question4.setId(4);

        when(questionRepository.count()).thenReturn(4L);
        when(questionRepository.getQuestionById(1)).thenReturn(question1);
        when(questionRepository.getQuestionById(2)).thenReturn(question2);
        when(questionRepository.getQuestionById(3)).thenReturn(question3);
        when(questionRepository.getQuestionById(4)).thenReturn(question4);


    }


    @Test
    public void shouldSaveQuestionInRepository() {
        Question question1 = new Question("question 1 test", "aq1", Arrays.asList("q1", "q2", "a3"));
        question1.setId(1);
        when(questionRepository.save(Mockito.any(Question.class))).then(returnsFirstArg());
        Question savedQuestion = questionService.saveQuestion(question1);
        assertThat(savedQuestion.getId()).isEqualTo(1);
    }

    @Test
    public void shouldCollectQuestionsForQuiz() {


        List<Question> questionList = questionService.collectQuestionsForQuiz(4);
        assertThat(questionList).hasSize(4).extracting(Question::getId).contains(1L, 2L, 3L, 4L);
    }


    @Test
    public void shouldDrawQuestions() {
        assertThat(questionService.drawQuestions(4)).contains(1, 2, 3, 4);
    }

    @Test
    public void shouldGetCorrectAnswerForQuestion() {
        assertThat(questionService.getCorrectAnswerForQuestion(1).contains("aq1"));
    }


    @Test
    public void shouldCountQuestions() {
        assertThat(questionRepository.count()).isEqualTo(4);
    }

    @Test
    public void shouldGetQuestionById(){
        assertEquals(questionRepository.getQuestionById(1).getCorrectAnswer(), "aq1");
    }

}
