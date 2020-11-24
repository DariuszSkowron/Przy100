package com.skowrondariusz.przy100.utility;


import com.skowrondariusz.przy100.dto.QuestionDto;
import com.skowrondariusz.przy100.model.Question;
import com.skowrondariusz.przy100.model.SongQuestion;
import org.junit.Ignore;
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

    @Ignore
    @Test
    public void shouldConvertQuestionDTOtoQuestion(){
        QuestionDto questionDto = new QuestionDto("1","test",Arrays.asList("good","bad1","bad2","bad3"),"good");
        Question question = mapper.convertToQuestionEntity(questionDto);

        assertThat(question.getCorrectAnswer().equals("good"));
    }


    @Test
    public void shouldConvertSongQuestionToQuestionDTO(){
        Question question = new SongQuestion("question 1 test", "aq1", Arrays.asList("q1", "q2", "a3"), "qqq");
        question.setId(1);
        QuestionDto questionDto = mapper.convertToQuestionDto(question);

        assertThat(questionDto.getCorrectSongUrl().contains("qqq"));
    }

    @Ignore
    @Test
    public void shouldConvertQuestionDTOtoSongQuestion(){
        QuestionDto questionDto = new QuestionDto("1","test",Arrays.asList("good","bad1","bad2","bad3"),"good");
        questionDto.setCorrectSongUrl("test11");
        Question question = mapper.convertToQuestionEntity(questionDto);

        assertThat(question.getListOfWrongAnswers()).containsOnly("bad1","bad2","bad3");
    }


}
