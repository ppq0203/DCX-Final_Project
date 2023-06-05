package project.momento.question.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto2;

@Mapper
public interface QuestionMapper {
	List<QuestionDto> selectQuestionList();
	List<TestcaseDto2> selectTestcaseList(int qestionSeq);
}
