package com.skowrondariusz.przy100.api;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.service.QuizService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("quiz")
public class QuizController {


    QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/start")
    public Quiz startQuiz() {
        Quiz newQuiz = quizService.startNewQuiz();
        return newQuiz;
    }


}
