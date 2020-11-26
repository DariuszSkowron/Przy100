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

        Result result1 = new Result(20d,5,"test1");
        result1.setId(1);
        result1.setTotalScore(555);
        Result result2 = new Result(31d,10,"test2");
        result2.setId(2);
        result2.setTotalScore(111);

        List<Result> allResults = Arrays.asList(result1,result2);

        when(resultRepository.count()).thenReturn(5L);
        when(resultRepository.findFirstByOrderByTotalScoreAsc()).thenReturn(result2);
        when(resultRepository.lowestScore()).thenReturn(2d);
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
    public void shouldReturnZeroWhenRepositoryIsEmpty(){
        when(resultRepository.count()).thenReturn(0L);

        double result = resultService.checkLastSubmittedScore();

        assertThat(result).isEqualTo(0);

    }

    @Test
    public void shouldSubmitResult(){
        Result resultTest = new Result(31d,10,"test2");
        resultTest.setId(3);
        resultTest.setTotalScore(130);
        when(resultRepository.save(Mockito.any(Result.class))).then(returnsFirstArg());
        resultService.submitResult(resultTest);
        Result test = resultRepository.save(resultTest);
        assertThat(test.getId()).isEqualTo(3);
    }
}
