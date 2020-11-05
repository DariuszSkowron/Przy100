package com.skowrondariusz.przy100.utility;

import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class Mapper {


    private ModelMapper modelMapper = new ModelMapper();

    public QuestionDto convertToQuestionDto(Question entity) {
        var viewModel = modelMapper.map(entity, QuestionDto.class);
        var answerList = entity.getListOfWrongAnswers();
        answerList.add(entity.getCorrectAnswer());
        Collections.shuffle(answerList);
        viewModel.setAnswersList(answerList);
        return viewModel;
    }

    public Question convertToQuestionEntity(QuestionDto viewModel) {
        return modelMapper.map(viewModel, Question.class);
    }
}
