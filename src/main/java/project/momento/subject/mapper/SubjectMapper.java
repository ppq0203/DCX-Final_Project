package project.momento.subject.mapper;

import org.apache.ibatis.annotations.Mapper;

import project.momento.subject.dto.SubjectDto;

@Mapper
public interface SubjectMapper {

	void selectSubject(SubjectDto subjectDto);
	
	
	
}