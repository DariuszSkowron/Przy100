package com.skowrondariusz.przy100.service;


import com.skowrondariusz.przy100.dto.UserAnswerDto;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {


    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

//    @Spy
//    @InjectMocks
    @Mock
    private QuizService quizService;

    @Before
    public void setUp(){

        Result result1 = new Result(20d,5,"test1", 555d);
        result1.setId(1);
        Result result2 = new Result(31d,10,"test2", 111d);
        result2.setId(2);

        List<Result> allResults = Arrays.asList(result1,result2);

        when(resultRepository.count()).thenReturn(2L);
        when(resultRepository.lowestScore()).thenReturn(1122d);
        when(resultRepository.findAll()).thenReturn(allResults);
        when(resultRepository.getOne(1L)).thenReturn(result1);
        when(resultRepository.getOne(2L)).thenReturn(result2);
        when(resultRepository.findAll()).thenReturn(allResults);
    }

    @Test
    public void shouldReturnLowestSubmittedScore(){
        assertThat(resultRepository.lowestScore()).isEqualTo(1122d);
    }

    @Test
    public void shouldReturnZeroWhenRepositoryIsEmptyAndLowestScoreWhenItsNot(){
        when(resultRepository.count()).thenReturn(0L);

        double result = resultService.checkLastSubmittedScore();
        assertThat(result).isEqualTo(0);

        when(resultRepository.count()).thenReturn(3L);
        double resultWithScore = resultService.checkLastSubmittedScore();
        assertThat(resultWithScore).isEqualTo(1122d);

    }

    @Test
    public void shouldSubmitResult(){
        Result resultTest = new Result(31d,10,"test2", 130d);
        resultTest.setId(3);
        Result overwrittenTest = new Result(12d, 5,"test1", 110d);
        overwrittenTest.setId(4);
        when(resultRepository.getOne(4L)).thenReturn(overwrittenTest);
        when(resultRepository.count()).thenReturn(2L);
        when(resultRepository.findFirstByOrderByTotalScoreAsc()).thenReturn(overwrittenTest);
        when(resultRepository.lowestScore()).thenReturn(overwrittenTest.getTotalScore());
        when(resultRepository.save(Mockito.any(Result.class))).then(returnsFirstArg());
        resultService.submitResult(resultTest);
        assertThat(overwrittenTest.getTotalScore()).isEqualTo(130);




    }


    @Test
    public void shouldSaveResult(){
        Result resultTest = new Result(31d,10,"test2", 130d);
        resultTest.setId(1);
        when (resultRepository.save(Mockito.any(Result.class))).then(returnsFirstArg());

        Result savedResult = resultRepository.save(resultTest);

        assertThat(savedResult.getId()).isEqualTo(1);

    }


    @Test
    public void shouldUpdateResult(){
        Result resultTest = new Result(31d,10,"test2", 130d);
        resultTest.setId(1);
        Result resultUpdated = new Result(31d,10,"test2", 555d);
        resultTest.setId(2);
        when(resultRepository.getOne(2L)).thenReturn(resultUpdated);
        when(resultRepository.save(Mockito.any(Result.class))).then(returnsFirstArg());
        resultService.updateResult(2L, resultTest);

        assertThat(resultUpdated.getTotalScore()).isEqualTo(130d);
    }

    @Test
    public void shouldCheckIfScoreIsAbleToSubmit(){
        Result resultTest = new Result(31d, 10, "test2", 130d);
        assertThat(resultService.checkIfAbleToSubmitScore(resultTest)).isEqualTo(false);
        resultTest.setTotalScore(1123d);
        assertThat(resultService.checkIfAbleToSubmitScore(resultTest)).isEqualTo(true);
    }

    @Test
    public void shouldDeleteScore(){
        Result testedResult = resultRepository.getOne(1L);
        resultService.deleteResult(1L);
        verify(resultRepository, times(1)).deleteById(eq(testedResult.getId()));
    }


    @Test
    public void shouldGenerateResultFromQuiz(){
        Quiz quiz = new Quiz(java.util.Calendar.getInstance().getTime());
        when(quizService.numberOfUserCorrectAnswers(quiz)).thenReturn(25);
        when(quizService.userPoints(quiz)).thenReturn(150d);
        Result xxx = resultService.getUserResult(quiz);
        assertThat(resultService.getUserResult(quiz).getTotalScore()).isEqualTo(150d);

    }


}
