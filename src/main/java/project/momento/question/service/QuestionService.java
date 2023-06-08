package project.momento.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.question.mapper.QuestionMapper;
import project.momento.question.dto.QuestionDto;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionMapper questionMapper;

//	public void insertQuestion(QuestionDto qtDto) {
//		// TODO Auto-generated method stub
//		questionMapper.insertQuestion(qtDto);
//	}

//	public List<QuestionDto> selectQuestion(String levels, int probNum) {
//		// TODO Auto-generated method stub
////		return questionMapper.selectQuestion(levels, probNum);
//	}

	public List<QuestionDto> selectQuestionList() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<QuestionDto> selectQuestion(QuestionDto qtDto) {
		// TODO Auto-generated method stub
		return questionMapper.selectQuestion(qtDto);
	}
	

	

}
