package com.skowrondariusz.przy100.api;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.model.Result;
import com.skowrondariusz.przy100.repository.ResultRepository;
import com.skowrondariusz.przy100.service.QuestionService;
import com.skowrondariusz.przy100.service.QuizService;
import com.skowrondariusz.przy100.service.ResultService;
import com.skowrondariusz.przy100.utility.Mapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/quiz")
public class QuizController {


    private QuizService quizService;
    private QuestionService questionService;
    private ResultRepository resultRepository;
    private ResultService resultService;
    private Mapper mapper;


    public QuizController(QuizService quizService, QuestionService questionService, ResultRepository resultRepository, ResultService resultService, Mapper mapper) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.resultRepository = resultRepository;
        this.resultService = resultService;
        this.mapper = mapper;
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
        System.out.println(Arrays.toString(result));
        return result;
    }

    @PostMapping("/result")
    public Result postResult(@RequestBody Result result){
        result.setTotalScore(this.resultService.totalScore(result));
        this.resultRepository.save(result);
        return result;

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


    @GetMapping("/test2")
    public Question test(){
        return questionService.getQuestionById(1);
    }

}
