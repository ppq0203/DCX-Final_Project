package project.momento.aiQuiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.aiQuiz.dto.AiDictDto;
import project.momento.aiQuiz.mapper.AiDictMapper;
import project.momento.education.dto.EducationDto;
import project.momento.education.mapper.EducationMapper;
import project.momento.login.dto.LoginDto;
import project.momento.page.Criteria;
import project.momento.qna.dto.QnaDto;
import project.momento.question.dto.QuestionDto;
import project.momento.subject.dto.SubjectDto;

@Service
public class AiDictService {
	
	@Autowired
	private AiDictMapper aidictMapper;

	public void insertAiDict(AiDictDto aidictDto) {
		// TODO Auto-generated method stub
		aidictMapper.insertAiDict(aidictDto);
	}

	public void deleteAiDict(AiDictDto aidictDto) {
		// TODO Auto-generated method stub
		aidictMapper.deleteAiDict(aidictDto);
	}
	
	public void updateAiDict(AiDictDto aidictDto) {
		// TODO Auto-generated method stub
		aidictMapper.updateAiDict(aidictDto);
	}
	
	public AiDictDto getDictList(int pkDictionarySeq) {
		// TODO Auto-generated method stub
		return aidictMapper.getDictList(pkDictionarySeq);
	}

	public int getDictCountForUser(int pkDictionarySeq, int pkUserSeq) {
		// TODO Auto-generated method stub
		return aidictMapper.getDictCountForUser(pkDictionarySeq, pkUserSeq);
	}

	public List<AiDictDto> selectDictList(Criteria cri) {
		// TODO Auto-generated method stub
		return aidictMapper.selectDictList(cri);
	}

	public int selectDictListCount() {
		// TODO Auto-generated method stub
		return aidictMapper.selectDictListCount();
	}


}
