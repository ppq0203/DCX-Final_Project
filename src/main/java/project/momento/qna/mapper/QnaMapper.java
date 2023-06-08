package project.momento.qna.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import project.momento.page.Criteria;
import project.momento.qna.dto.QnaDto;
import project.momento.subject.dto.SubjectDto;

@Repository
@Mapper
public interface QnaMapper {

	void insertQna(QnaDto qnaDto);

	List<QnaDto> getQnaListForMng(SubjectDto subjectDto);
	
	List<QnaDto> getQnaListForUser(SubjectDto subjectDto);

	int getQnaCountForMng(int pkSubjectSeq);

	int getQnaCountForUser(@Param("pkSubjectSeq") int pkSubjectSeq, @Param("pkUserSeq") int pkUserSeq);

	QnaDto selectAsk(String pkAskSeq);

}	
	
	
