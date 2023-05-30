package project.momento.subject.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectMapper {

	void selectSubject(String subStartDt, String subEndDt, int pkSubjectSeq);
	
	
	
}