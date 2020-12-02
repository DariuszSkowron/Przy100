package com.skowrondariusz.przy100.service;


import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {


    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

    @Before
    public void setUp(){

        Result result1 = new Result(20d,5,"test1", 555d);
        result1.setId(1);
        Result result2 = new Result(31d,10,"test2", 111d);
        result2.setId(2);

        List<Result> allResults = Arrays.asList(result1,result2);

        when(resultRepository.count()).thenReturn(2L);
        when(resultRepository.findFirstByOrderByTotalScoreAsc()).thenReturn(result2);
        when(resultRepository.lowestScore()).thenReturn(1122d);
        when(resultRepository.findAll()).thenReturn(allResults);
        when(resultRepository.getOne(1L)).thenReturn(result1);
        when(resultRepository.getOne(2L)).thenReturn(result2);
        when(resultRepository.findAll()).thenReturn(allResults);
//        when(resultRepository.save(Mockito.any(Result.class))).thenCallRealMethod(returnsFirstArg());
//        when(resultRepository.findById(2L)).thenReturn(java.util.Optional.of(result2)).thenReturn(null);
//        when(resultRepository.deleteById(2L)).then(allResults.remove(2));
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
    public void shouldSaveResultWhenRepositoryIsNotFull(){
        Result resultTest = new Result(31d,10,"test2", 130d);
        resultTest.setId(3);
        System.out.println(resultTest.toString());
        System.out.println(resultRepository.findAll().toString());
        when(resultRepository.count()).thenReturn(1L);
        when(resultRepository.save(Mockito.any(Result.class))).then(returnsFirstArg());
        Result test = resultService.submitResult(resultTest);
        assertThat(test.getNumberOfCorrectAnswers()).isEqualTo(10);
        System.out.println(resultRepository.findAll().toString());
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



}
