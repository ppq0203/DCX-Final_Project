package project.momento.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.momento.education.dto.EducationDto;
import project.momento.education.mapper.EducationMapper;
import project.momento.page.Criteria;

@Service
public class EducationService {
	
	@Autowired
	private EducationMapper educationMapper;

	public EducationDto selectEducation(EducationDto subjectDto) {
		// TODO Auto-generated method stub
		
		return educationMapper.selectEducation(subjectDto);
	}

	public List<EducationDto> selectEducationList(Criteria cri) {
		// TODO Auto-generated method stub
		return educationMapper.selectEducationList(cri);
	}

	public int selectEducationCount(Criteria cri) {
		// TODO Auto-generated method stub
		return educationMapper.selectEducationCount(cri);
	}

	public List<Object> getEducationList(int pkUserSeq) {
		return educationMapper.getEducationList(pkUserSeq);
	}
	
}
