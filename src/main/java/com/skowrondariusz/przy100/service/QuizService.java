package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Quiz;
import com.skowrondariusz.przy100.utility.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private QuestionService questionService;
    private Mapper mapper;

    public QuizService(QuestionService questionService, Mapper mapper) {
        this.questionService = questionService;
        this.mapper = mapper;
    }

    @Transactional
    public Quiz startNewQuiz(int numberOfQuestions){
        Quiz quiz = new Quiz();
        quiz.setQuestionList(questionService.collectQuestionsForQuiz(numberOfQuestions).stream()
                .map(question -> this.mapper.convertToQuestionDto(question))
                .collect(Collectors.toList()));
        Date x = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar c = Calendar.getInstance();
        x = c.getTime();
        quiz.setStartTime(x);
        return quiz;
    }

    int numberOfUserCorrectAnswers(Quiz userQuiz) {
        var result = 0;

        if (userQuiz.getUserAnswers() == null){
            return 0;
        }

        for (int i = 0; i < userQuiz.getUserAnswers().size(); i++) {
            try {
                if (userQuiz.getUserAnswers().get(i).getAnswer().equals(questionService.getCorrectAnswerForQuestion(userQuiz.getUserAnswers().get(i).getQuestionId()))) {
                    result ++;
                }
            } catch (NullPointerException e) {
                {
                    System.out.print("Caught NullPointerException");
                }
            }

        }
        return result;
    }


    double userPoints(Quiz userQuiz){
        double result = 0d;;
        var timerFlag = userQuiz.getStartTime().getTime() / 1000;


        if (userQuiz.getUserAnswers() == null){
            return 0;
        }

        for (int i = 0; i < userQuiz.getUserAnswers().size(); i++){
            try {
                if (userQuiz.getUserAnswers().get(i).getAnswer().equals(questionService.getCorrectAnswerForQuestion(userQuiz.getUserAnswers().get(i).getQuestionId()))){
                    double timeTest = ((double) userQuiz.getUserAnswers().get(i).getAnswerTime().getTime() / 1000) - (double) timerFlag;
                    result = result + (100 /  (((double) userQuiz.getUserAnswers().get(i).getAnswerTime().getTime() / 1000) - (double) timerFlag));
                    result = result + 100 / timeTest;
                    timerFlag = (userQuiz.getUserAnswers().get(i).getAnswerTime().getTime() / 1000);

                }
                else{
                    timerFlag = (userQuiz.getUserAnswers().get(i).getAnswerTime().getTime() / 1000);
                }
            }
            catch (NullPointerException e){
                {
                    System.out.print("Caught NullPointerException");
                }
            }
        }
        return (double)Math.round(result * 100) / 100;
    }

}
