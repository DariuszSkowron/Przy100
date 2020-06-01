package com.skowrondariusz.przy100.api;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.QuizService;
import com.skowrondariusz.przy100.service.ResultService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/quiz")
public class QuizController {


    private QuizService quizService;
    private QuestionService questionService;
    private ResultRepository resultRepository;
    private ResultService resultService;

    public QuizController(QuizService quizService, QuestionService questionService, ResultRepository resultRepository, ResultService resultService) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.resultRepository = resultRepository;
        this.resultService = resultService;
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

    @PostMapping("/result")
    public Result postResult(@RequestBody Result result){

        var project = result;

        project.setTotalScore(this.resultService.totalScore(project));
        this.resultRepository.save(project);
        return project;

    }


    @GetMapping("/highscores")
    public List<Result> highscores(){
        var topResults = this.resultRepository.findAll();
        return new ArrayList<>(topResults);
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
