package project.momento.answer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.answer.dto.AnswerDto;



@Mapper
public interface AnswerMapper {

	void insertAnswer(AnswerDto answerDto);

	List<AnswerDto> selectAnswer(int pkUserSeq);

	AnswerDto selectAnswerType(AnswerDto asDto);

	AnswerDto selectAnswerTime(AnswerDto asDto);

	AnswerDto selectAnswerTY(AnswerDto asDto);

}