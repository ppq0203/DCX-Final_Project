package project.momento.subject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.subject.dto.SubjectDto;
import project.momento.subject.mapper.SubjectMapper;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectMapper subjectMapper;

	public void selectSubject(String subStartDt, String subEndDt, int pkSubjectSeq) {
		// TODO Auto-generated method stub
		
		subjectMapper.selectSubject(subStartDt, subEndDt, pkSubjectSeq);
	}
}
