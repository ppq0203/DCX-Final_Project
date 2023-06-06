package project.momento.qna.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import project.momento.qna.dto.QnaDto;

@Repository
@Mapper
public interface QnaMapper {

	void insertQna(QnaDto qnaDto);

}	
	
	
