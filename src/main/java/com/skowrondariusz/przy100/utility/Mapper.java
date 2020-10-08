package com.skowrondariusz.przy100.utility;

import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.service.QuestionService;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class Mapper {


    private final QuestionService questionService;
    private ModelMapper modelMapper = new ModelMapper();

    public Mapper(QuestionService questionService) {
        this.questionService = questionService;
    }


    public QuestionDto convertToQuestionDto(Question entity){
        var viewModel = modelMapper.map(entity, QuestionDto.class);
        var answerList = entity.getListOfWrongAnswers();
        answerList.add(entity.getCorrectAnswer());
        viewModel.setAnswersList(answerList);
        return viewModel;

    }
}