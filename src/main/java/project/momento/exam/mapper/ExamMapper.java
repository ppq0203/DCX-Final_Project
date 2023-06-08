package project.momento.exam.mapper;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.momento.chart.dto.ChartDto;
import project.momento.exam.dto.ExamDto;
import project.momento.page.Criteria;


@Mapper
public interface ExamMapper {

	void insertExam(ExamDto examDto);

	List<ExamDto> getExamList(int pkEducationSeq);

	void insertExamDetail(ExamDto examDto);

	List<ExamDto> selectExamDetailList(ExamDto examDto);

	void deleteExamDetail(ExamDto examDto);

	void deleteExam(ExamDto examDto);

}