package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionService {



    private QuestionRepository questionRepository;


    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    List<Question> collectQuestionsForQuiz(int quizSize){
        List<Question> questionList = new ArrayList<>();
        int[] drawnQuestionsNumbers = drawQuestions(quizSize);
        for (int i = 0; i < quizSize; i++) {
            questionList.add(questionRepository.getQuestionById(drawnQuestionsNumbers[i]));
        }
        return questionList;
    }

    int[] drawQuestions(int quizSize){
        return new Random().ints(1,Math.toIntExact(questionRepository.count()) +1)
                .distinct()
                .limit(quizSize)
                .toArray();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Question saveQuestion (Question question){
       return questionRepository.save(question);
    }

    public Question getQuestionById(int id){
       return questionRepository.getQuestionById(id);
    }

    public String getCorrectAnswerForQuestion(long questionId){
        return questionRepository.getQuestionById(questionId).getCorrectAnswer();
    }

    long count(){
        return questionRepository.count();
    }

    public String deleteQuestions(){
        questionRepository.deleteAll();
        return "Database cleared out";
    }




}
