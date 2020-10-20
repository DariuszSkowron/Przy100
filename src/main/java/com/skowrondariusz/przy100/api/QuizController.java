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
        return quizService.startNewQuiz();
    }

    @PostMapping("/correctAnswers")
    public String[] result(@RequestBody int[] answerIdList) {
        var result = new String[answerIdList.length];
        for (int i = 0; i < answerIdList.length; i++) {
            result[i] = questionService.getCorrectAnswerForQuestion(answerIdList[i]);
            System.out.println("pp");
        }
        return result;
    }

    @PostMapping("/userResult")
    public Result userResult(@RequestBody Quiz userQuiz) {
        return resultService.getUserResult(userQuiz);
    }

    @PostMapping("/result")
    public Result postResult(@RequestBody Result result) {
        result.setTotalScore(this.resultService.totalScore(result));
        this.resultRepository.save(result);
        return result;

    }


    @GetMapping("/highscores")
    public List<Result> highscores() {
        var topResults = this.resultRepository.findAll();
        return new ArrayList<>(topResults);
    }

}
