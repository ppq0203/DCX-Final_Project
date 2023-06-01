package project.momento.question.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import project.momento.question.dto.QuestionDto;
import project.momento.question.dto.TestcaseDto;

@Mapper
public interface QuestionMapper {
	List<QuestionDto> selectQuestionList();
	List<TestcaseDto> selectTestcaseList(int qestionSeq);
}
