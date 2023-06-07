package project.momento.subject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.subject.dto.SubjectDto;
import project.momento.subject.mapper.SubjectMapper;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectMapper subjectMapper;

	public SubjectDto getSubject(int pkSubjectSeq) {
		return subjectMapper.getSubject(pkSubjectSeq);
	}

}
