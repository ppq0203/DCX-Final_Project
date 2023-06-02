package project.momento.subject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import project.momento.page.Criteria;
import project.momento.subject.dto.SubjectDto;

@Mapper
public interface SubjectMapper {

	SubjectDto selectSubject(SubjectDto subjectDto);

	List<SubjectDto> selectSubjectList(Criteria cri);
}