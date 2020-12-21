package com.skowrondariusz.przy100.service;


import com.skowrondariusz.przy100.dto.UserAnswerDto;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-testing.properties")
public class ResultServiceIT {


    @Autowired
    ResultService resultService;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    QuizService quizService;



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

    @Test
    public void shouldSaveResult(){
        Result result1 = new Result(20d,5,"test1", 555d);

        resultService.saveResult(result1);

        assertThat(resultRepository.count()).isEqualTo(1);
    }

    @Test
    public void shouldUpdateResult(){
        Result result1 = new Result(20d,5,"test1", 555d);
        resultService.saveResult(result1);

        Result updateResult = new Result(10, 2, "updated", 123d);

        resultService.updateResult(1L, updateResult);


        assertThat(resultService.checkLastSubmittedScore()).isEqualTo(123d);
    }

    @Test
    public void shouldCheckIfScoreIsAbleToSubmit(){
        Result result1 = new Result(20d,5,"test1", 555d);
        resultRepository.save(result1);
        Result result2 = new Result(31d,10,"test2", 111d);
        resultRepository.save(result2);

        Result result3 = new Result(31d,10,"test2", 100d);
        assertThat(resultService.checkIfAbleToSubmitScore(result3)).isEqualTo(false);
        result3.setTotalScore(1123d);
        assertThat(resultService.checkIfAbleToSubmitScore(result3)).isEqualTo(true);
    }

    @Test
    public void shouldDeleteScore(){
        Result result1 = new Result(20d,5,"test1", 555d);
        resultRepository.save(result1);
        assertThat(resultRepository.count()).isEqualTo(1);
        resultService.deleteResult(1L);
        assertThat(resultRepository.count()).isEqualTo(0);
    }

    @Test
    public void shouldGenerateResultFromQuiz(){
        Quiz userQuiz = quizService.startNewQuiz(2);
        UserAnswerDto userAnswerDto1 = new UserAnswerDto(Long.parseLong(userQuiz.getQuestionList().get(0).getId()), new Date(),userQuiz.getQuestionList().get(0).getCorrectAnswer());
        UserAnswerDto userAnswerDto2 = new UserAnswerDto(Long.parseLong(userQuiz.getQuestionList().get(1).getId()), new Date(),userQuiz.getQuestionList().get(1).getCorrectAnswer());

        var userAnswers = Arrays.asList(userAnswerDto1, userAnswerDto2);

        userQuiz.setUserAnswers(userAnswers);

        Result resultFromQuiz = resultService.getUserResult(userQuiz);

        assertThat(resultFromQuiz.getTotalScore()).isNotNull();
    }


}
