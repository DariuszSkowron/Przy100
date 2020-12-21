package com.skowrondariusz.przy100.service;


import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
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
public class ResultServiceIT {


    @Autowired
    ResultService resultService;

    @Autowired
    ResultRepository resultRepository;



    @Test
    public void shouldReturnLowestSubmittedScore(){
        Result result1 = new Result(20d,5,"test1", 555d);
        resultRepository.save(result1);
        Result result2 = new Result(31d,10,"test2", 111d);
        resultRepository.save(result2);
        assertThat(resultService.checkLastSubmittedScore()).isEqualTo(111d);
    }

    @Test
    public void shouldReturnZeroWhenRepositoryIsEmptyAndLowestScoreWhenItsNot(){
        assertThat(resultService.checkLastSubmittedScore()).isEqualTo(0d);
    }

    @Test
    public void shouldSubmitResult(){
        Result result1 = new Result(20d,5,"test1", 555d);

        resultService.submitResult(result1);
        assertThat(resultService.checkLastSubmittedScore()).isEqualTo(555d);

        Result result2 = new Result(31d,10,"test2", 111d);
        resultService.submitResult(result2);

        assertThat(resultService.checkLastSubmittedScore()).isEqualTo(111d);
        assertThat(resultRepository.count()).isEqualTo(2);

        Result result3 = new Result(31d,10,"test2", 225d);

        resultService.submitResult(result3);
        assertThat(resultService.checkLastSubmittedScore()).isEqualTo(225d);
        assertThat(resultRepository.count()).isEqualTo(2);
    }
}
