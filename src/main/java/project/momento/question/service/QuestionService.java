package project.momento.question.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.question.mapper.QuestionMapper;
import project.momento.question.dto.QuestionDto;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionMapper questionMapper;
	
	public List<QuestionDto> selectQuestionList() {
		
		return questionMapper.selectQuestionList();
	}
}
