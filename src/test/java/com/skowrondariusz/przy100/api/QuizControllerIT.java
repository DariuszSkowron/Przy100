package com.skowrondariusz.przy100.api;


import com.skowrondariusz.przy100.Przy100Application;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.QuizService;
import com.skowrondariusz.przy100.service.ResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.skowrondariusz.przy100.api.QuizControllerTest.asJsonString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = Przy100Application.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class QuizControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuizService quizService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ResultService resultService;

    @Test
    public void shouldStartNewQuiz() throws Exception {

//        given(quizService.startNewQuiz(20)).willReturn(newQuiz);

        mockMvc.perform(get("/quiz/start"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.questionList", hasSize(20)));
    }


    @Test
    public void shouldGetUserResult() throws Exception {
        Quiz newQuiz = new Quiz(new Date() );


        mockMvc.perform(post("/quiz/userResult")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newQuiz)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.numberOfCorrectAnswers", is(0)))
                .andExpect(jsonPath("$.totalScore", is(0.0)));
    }


    @Test
    public void shouldSubmitUserResult() throws Exception {
        Result testResult = new Result(22d, 10, "test", 1230d);


        mockMvc.perform(post("/quiz/result")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testResult)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalScore", is(testResult.getTotalScore())))
                .andExpect(jsonPath("$.numberOfCorrectAnswers", is(testResult.getNumberOfCorrectAnswers())))
                .andExpect(jsonPath("$.nickname", is(testResult.getNickname())))
                .andExpect(jsonPath("$.timeSpent", is(testResult.getTimeSpent())));
    }

    @Test
    public void shouldNotSubmitUserResult() throws Exception {


        mockMvc.perform(post("/quiz/result")
                .content(""))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void shouldGetHighScoresList() throws Exception {
        List<Result> resultList = Arrays.asList(
                new Result(22d, 10, "test1", 1230d),
                new Result(22d, 10, "test2", 12312d)
        );
        resultRepository.save(resultList.get(0));
        resultRepository.save(resultList.get(1));

        mockMvc.perform(get("/quiz/highscores"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nickname", is(resultList.get(0).getNickname())))
                .andExpect(jsonPath("$[0].totalScore", is(resultList.get(0).getTotalScore())))
                .andExpect(jsonPath("$[1].nickname", is(resultList.get(1).getNickname())))
                .andExpect(jsonPath("$[1].totalScore", is(resultList.get(1).getTotalScore())));
    }

    @Test
    public void shouldCheckIfScoreIsAbleToBeSubmitted() throws Exception{
        Result testResult = new Result(22d, 10, "test", 1230d);
        
        mockMvc.perform(post("/quiz/isHighScore")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testResult)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));
    }




}
