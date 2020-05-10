package com.skowrondariusz.przy100.service;

import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestionService {


    private QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    List<Question> collectQuestionsForQuiz(int quizSize){
        List<Question> questionList = new ArrayList<>();
        List<Integer> drawedQuestionsNumbers = drawQuestions(quizSize);
        for (int i = 0; i < quizSize; i++) {
            questionList.add(questionRepository.getQuestionById(drawedQuestionsNumbers.get(i)));
        }
        return questionList;
    }

    private List<Integer> drawQuestions(int quizSize){
        return Stream.generate(() -> (new Random()).nextInt(Math.toIntExact(questionRepository.count())))
                .limit(quizSize)
                .collect(Collectors.toList());
    }

    public void saveQuestion (Question question){
        questionRepository.save(question);
    }


}
