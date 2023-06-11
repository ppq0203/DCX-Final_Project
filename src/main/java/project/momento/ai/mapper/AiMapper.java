package project.momento.ai.mapper;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.momento.chart.dto.ChartDto;
import project.momento.page.Criteria;
import project.momento.question.dto.QuestionDto;


@Mapper
public interface AiMapper {

	List<QuestionDto> selectQuestionResultList(QuestionDto questionDto);

	List<QuestionDto> selectQuestionList(Criteria cri);

	int selectQuestionListCount();


}