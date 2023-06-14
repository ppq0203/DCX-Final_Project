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
	
	public List<AnswerDto> selectAnswer(int pkUserSeq){
		return AnswerMapper.selectAnswer(pkUserSeq);
	}

	public AnswerDto selectAnswerType(AnswerDto asDto) {
		// TODO Auto-generated method stub
		return AnswerMapper.selectAnswerType(asDto);
	}

	public AnswerDto selectAnswerTime(AnswerDto asDto) {
		// TODO Auto-generated method stub
		return AnswerMapper.selectAnswerTime(asDto);
	}

	public AnswerDto selectAnswerTY(AnswerDto asDto) {
		// TODO Auto-generated method stub
		return AnswerMapper.selectAnswerTY(asDto);
	}

}