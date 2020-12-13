package com.skowrondariusz.przy100.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.QuizService;
import com.skowrondariusz.przy100.service.ResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Date;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.client.match.MockMvcResultMatchers.jsonPath;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(QuizController.class)
public class QuizControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuizService quizService;
    @MockBean
    private QuestionService questionService;
    @MockBean
    private ResultRepository resultRepository;
    @MockBean
    private ResultService resultService;


    @Test
    public void shouldStartNewQuiz() throws Exception{
        Quiz newQuiz = new Quiz(new Date());

        given(quizService.startNewQuiz(20)).willReturn(newQuiz);

        mockMvc.perform(get("/quiz/start"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.startTime", is(newQuiz.getStartTime().getTime())));
    }

    @Test
    public void shouldSubmitResult() throws Exception{
        Quiz newQuiz = new Quiz(new Date());
        Result testResult = new Result(22d, 10, "test");

//        given(resultService.getUserResult(newQuiz)).willReturn(testResult);
//        doReturn(testResult).when(resultService).getUserResult(newQuiz);
        when(resultService.getUserResult(any())).thenReturn(testResult);

        mockMvc.perform(post("/quiz/userResult")
                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(newQuiz)))

                .content(asJsonString(newQuiz)))
                .andExpect(status().isOk())

//                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andDo(print())

                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$.timeSpent", is(testResult.getTimeSpent())));
//            .andExpect(content().json("kupa"));





//        given(resultService.getUserResult(newQuiz)).willReturn(testResult);
        doReturn(testResult).when(resultService).getUserResult(newQuiz);

        mockMvc.perform(post("/quiz/userResult")
                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(newQuiz)))
                .content(asJsonString(testResult)))
                .andExpect(status().isOk())
                .andDo(print());
    }



    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
