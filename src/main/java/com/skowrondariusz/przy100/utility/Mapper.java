package com.skowrondariusz.przy100.utility;

import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class Mapper {


    private ModelMapper modelMapper = new ModelMapper();


    public QuestionDto convertToQuestionDto(Question entity) {
        var viewModel = modelMapper.map(entity, QuestionDto.class);
        List<String> answerList = new ArrayList<>();
        answerList.add(entity.getListOfWrongAnswers().toString());
        answerList.add(entity.getCorrectAnswer().toString());
        Collections.shuffle(answerList);
        viewModel.setAnswersList(answerList);
        return viewModel;
    }

    public Question convertToQuestionEntity(QuestionDto viewModel) {
        return modelMapper.map(viewModel, Question.class);
    }
}
