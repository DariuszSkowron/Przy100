package com.skowrondariusz.przy100.api;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/quiz")
public class QuizController {


    private QuizService quizService;
    private QuestionService questionService;

    public QuizController(QuizService quizService, QuestionService questionService) {
        this.quizService = quizService;
        this.questionService = questionService;
    }

    @GetMapping("/start")
    public Quiz startQuiz() {
        Quiz newQuiz = quizService.startNewQuiz();
        return newQuiz;
    }

    @PostMapping("/correctAnswers")
    public int[] result(@RequestBody int[] answerIdList) {
        int[] result = new int[answerIdList.length];
        for (int i = 0; i < answerIdList.length; i++) {
            result[i] = questionService.getCorrectAnswerId(answerIdList[i]);
            System.out.println("test");
        }
        System.out.println(Arrays.toString(result));
        return result;

    }


    @GetMapping("/count")
    public long counter(){
        return questionService.count();
    }

    @GetMapping("/count2")
    public int[] counter2(){
        return questionService.drawQuestions(2);
    }
//
//    @GetMapping("test")
//    public void test(){
//        questionService.setCorrectAnswer(1);
//    }


}
