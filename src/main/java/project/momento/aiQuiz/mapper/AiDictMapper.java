package project.momento.aiQuiz.mapper;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import project.momento.aiQuiz.dto.AiDictDto;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.qna.dto.QnaDto;
import project.momento.question.dto.QuestionDto;
import project.momento.subject.dto.SubjectDto;

@Mapper
public interface AiDictMapper {

	void insertAiDict(AiDictDto aidictDto);

	void deleteAiDict(AiDictDto aidictDto);

	void updateAiDict(AiDictDto aidictDto);

	AiDictDto getDictList(int pkDictionarySeq);

	int getDictCountForUser(@Param("pkDictionarySeq") int pkDictionarySeq, @Param("pkUserSeq") int pkUserSeq);

	List<AiDictDto> selectDictList(LoginDto loginDto);
	
	List<AiDictDto> selectDictResultList(AiDictDto aidictDto);

	int selectDictListCount(LoginDto loginDto);

	int selectCorrectionDictListCount(LoginDto loginDto);

	List<AiDictDto> selectCorrectionDictList(LoginDto loginDto);

	void createCorrectionDict(AiDictDto aiDictDto);

}