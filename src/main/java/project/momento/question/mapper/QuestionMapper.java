package project.momento.question.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import project.momento.question.dto.QuestionDto;

@Mapper
public interface QuestionMapper {
	List<QuestionDto> selectQuestionList();


}
