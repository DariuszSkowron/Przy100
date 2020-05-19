package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Answer;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
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
        int[] drawedQuestionsNumbers = drawQuestions(quizSize);
        for (int i = 0; i < quizSize; i++) {
            questionList.add(questionRepository.getQuestionById(drawedQuestionsNumbers[i]));
        }
        return questionList;
    }

    public int[] drawQuestions(int quizSize){
        return new Random().ints(1,Math.toIntExact(questionRepository.count()) +1)
                .distinct()
                .limit(quizSize)
                .toArray();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveQuestion (Question question){
        questionRepository.save(question);
    }

    public Question getQuestionById(int id){
       return questionRepository.getQuestionById(id);
    }


    public void saveWrongQuestion (Question question, Answer answer){
        questionRepository.getQuestionById(question.getId()).setListOfAnswers(Collections.singletonList(answer));
    }

    @Transactional
    public void setCorrectAnswer (long questionId, Answer answer){
        questionRepository.getQuestionById(questionId).setCorrectAnswerId(answer.getId());

    }

    public long count(){
        return questionRepository.count();
    }


}
