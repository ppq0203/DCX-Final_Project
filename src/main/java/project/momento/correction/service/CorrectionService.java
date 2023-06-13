package project.momento.correction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.page.Criteria;
import project.momento.question.dto.QuestionDto;
import project.momento.question.mapper.QuestionMapper;

@Service
public class CorrectionService {

	@Autowired
	private QuestionMapper questionMapper;

	public List<QuestionDto> selectQuestionResultList(QuestionDto questionDto) {
		// TODO Auto-generated method stub
		return questionMapper.selectQuestionResultList(questionDto);
	}

	public List<QuestionDto> selectQuestionList(Criteria cri) {
		// TODO Auto-generated method stub
		return questionMapper.selectQuestionList(cri);
	}

	public int selectQuestionListCount() {
		// TODO Auto-generated method stub
		return questionMapper.selectQuestionListCount();
	}
}
