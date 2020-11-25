package com.skowrondariusz.przy100.service;


import com.skowrondariusz.przy100.repository.QuestionRepository;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {


    @Mock
    private ResultRepository resultRepository;

    @InjectMocks
    private ResultService resultService;

    @Before
    public void setUp(){

        when(resultRepository.lowestScore()).thenReturn(1122d);
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
}
