package project.momento.correction.mapper;

import java.util.List;

import project.momento.page.Criteria;
import project.momento.question.dto.QuestionDto;

public interface CorrectionMapper {

	List<QuestionDto> selectQuestionResultList(QuestionDto questionDto);

	List<QuestionDto> selectQuestionList(Criteria cri);

	int selectQuestionListCount();
}
