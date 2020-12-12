package com.skowrondariusz.przy100.api;

import com.skowrondariusz.przy100.model.Quiz;
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

import java.util.Date;

import static org.mockito.BDDMockito.given;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.client.match.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
        import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
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

}
