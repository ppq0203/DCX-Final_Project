package project.momento.answer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import project.momento.answer.dto.AnswerDto;
import project.momento.answer.mapper.AnswerMapper;


@RequiredArgsConstructor /* 단위생성자(?)*/
@Service
public class AnswerService {
	
	@Autowired
	private AnswerMapper AnswerMapper;
	
	public void insertAnswer(AnswerDto answerDto) {
		
		AnswerMapper.insertAnswer(answerDto);
	}
	
	public List<AnswerDto> waitList(AnswerDto answerDto){
		return AnswerMapper.waitList(answerDto);
	}

	
}