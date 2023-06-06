package project.momento.qna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.qna.dto.QnaDto;
import project.momento.qna.mapper.QnaMapper;

@Service
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;

	public void insertQna(QnaDto qnaDto) {
		// TODO Auto-generated method stub
		qnaMapper.insertQna(qnaDto);
	}

}
