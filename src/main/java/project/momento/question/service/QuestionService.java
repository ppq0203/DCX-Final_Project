package project.momento.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.question.mapper.QuestionMapper;
import project.momento.question.dto.QuestionDto;

@Service
public class QuestionService {
	
	@Autowired
	private static QuestionMapper questionMapper;

	public static void insertQuestion(QuestionDto qtDto) {
		// TODO Auto-generated method stub
		questionMapper.insertQuestion(qtDto);
	}

	public List<QuestionDto> selectQuestion(int pkQuestionSeq) {
		// TODO Auto-generated method stub
		return questionMapper.selectQuestion(pkQuestionSeq);
	}

	public List<QuestionDto> selectQuestionList() {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
