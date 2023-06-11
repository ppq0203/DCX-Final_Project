package project.momento.ai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.ai.mapper.AiMapper;
import project.momento.education.dto.EducationDto;
import project.momento.education.mapper.EducationMapper;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.question.dto.QuestionDto;

@Service
public class AiService {
	
	@Autowired
	private AiMapper aiMapper;

	public List<QuestionDto> selectQuestionResultList(QuestionDto questionDto) {
		// TODO Auto-generated method stub
		return aiMapper.selectQuestionResultList(questionDto);
	}

	public List<QuestionDto> selectQuestionList(Criteria cri) {
		// TODO Auto-generated method stub
		return aiMapper.selectQuestionList(cri);
	}

	public int selectQuestionListCount() {
		// TODO Auto-generated method stub
		return aiMapper.selectQuestionListCount();
	}

	public QuestionDto selectQuestion(QuestionDto questionDto) {
		// TODO Auto-generated method stub
		return aiMapper.selectQuestion(questionDto);
	}

	public void insertAiQuestion(QuestionDto questionDto) {
		// TODO Auto-generated method stub
		aiMapper.insertAiQuestion(questionDto);
	}

	public void updateAiQuestion(QuestionDto questionDto) {
		// TODO Auto-generated method stub
		aiMapper.updateAiQuestion(questionDto);
	}

	public void deleteAiQuestion(QuestionDto questionDto) {
		// TODO Auto-generated method stub
		aiMapper.deleteAiQuestion(questionDto);
	}

	
}
