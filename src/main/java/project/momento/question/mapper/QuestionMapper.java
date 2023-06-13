package project.momento.question.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import project.momento.page.Criteria;
import project.momento.question.dto.QuestionDto;

@Mapper
public interface QuestionMapper {

	List<QuestionDto> selectQuestion(QuestionDto qtDto);
	QuestionDto selectQuestionSeq(int pkQuestionSeq);
	
	List<QuestionDto> selectQuestionResultList(QuestionDto questionDto);

	List<QuestionDto> selectQuestionList(Criteria cri);

	int selectQuestionListCount();
}
