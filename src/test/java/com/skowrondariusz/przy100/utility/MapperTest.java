package com.skowrondariusz.przy100.utility;


import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.model.Question;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;


public class MapperTest {


    private Mapper mapper = new Mapper();

    @Test
    public void shouldConvertQuestionToQuestionDTO(){
        Question question = new Question("question 1 test", "aq1", Arrays.asList("q1", "q2", "a3"));
        question.setId(1);
        QuestionDto questionDto = mapper.convertToQuestionDto(question);

        assertThat(questionDto.getAnswersList().contains("aq1"));



    }

}
