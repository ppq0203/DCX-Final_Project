package project.momento.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.page.Criteria;
import project.momento.qna.dto.QnaDto;
import project.momento.qna.mapper.QnaMapper;
import project.momento.subject.dto.SubjectDto;

@Service
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;

	public void insertQna(QnaDto qnaDto) {
		// TODO Auto-generated method stub
		qnaMapper.insertQna(qnaDto);
	}

	public List<QnaDto> getQnaListForMng(SubjectDto subjectDto) {
		return qnaMapper.getQnaListForMng(subjectDto);
	}

	public int getQnaCountForMng(int pkSubjectSeq) {
		// TODO Auto-generated method stub
		return qnaMapper.getQnaCountForMng(pkSubjectSeq);
	}

	public int getQnaCountForUser(int pkSubjectSeq, int pkUserSeq) {
		// TODO Auto-generated method stub
		return qnaMapper.getQnaCountForUser(pkSubjectSeq, pkUserSeq);
	}

	public List<QnaDto> getQnaListForUser(SubjectDto subjectDto) {
		// TODO Auto-generated method stub
		return qnaMapper.getQnaListForUser(subjectDto);
	}

	public QnaDto selectAsk(String pkAskSeq) {
		// TODO Auto-generated method stub
		qnaMapper.qnaCountUp(pkAskSeq);
		return qnaMapper.selectAsk(pkAskSeq);
	}

	public List<QnaDto> getAnswerListForMng(SubjectDto subjectDto) {
		// TODO Auto-generated method stub
		return qnaMapper.getAnswerListForMng(subjectDto);
	}
	
	public List<QnaDto> getAnswerListForUser(SubjectDto subjectDto) {
		// TODO Auto-generated method stub
		return qnaMapper.getAnswerListForUser(subjectDto);
	}

	public void insertAskAns(QnaDto qnaDto) {
		// TODO Auto-generated method stub
		qnaMapper.insertAskAns(qnaDto);
	}

	public void deleteAskAns(String pkAskAnsSeq) {
		// TODO Auto-generated method stub
		qnaMapper.deleteAskAns(pkAskAnsSeq);
	}

}
