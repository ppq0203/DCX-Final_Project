package project.momento.education.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.education.dto.EducationDto;
import project.momento.page.Criteria;

@Mapper
public interface EducationMapper {

	List<EducationDto> selectEducation(EducationDto subjectDto);

	List<EducationDto> selectEducationList(Criteria cri);

	int selectEducationCount(Criteria cri);
	
	List<Object> getEducationList(int pkUserSeq);

	List<EducationDto> selectSubjectList(int pkEducationSeq);

	List<EducationDto> selectEducationStudList(int pkEducationSeq);

}